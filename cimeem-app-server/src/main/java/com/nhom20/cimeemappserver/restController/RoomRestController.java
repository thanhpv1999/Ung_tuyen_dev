package com.nhom20.cimeemappserver.restController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Movie;
import com.nhom20.cimeemappserver.entity.SeatNo;
import com.nhom20.cimeemappserver.entity.Slides;
import com.nhom20.cimeemappserver.entity.TheatreShowTimes;
import com.nhom20.cimeemappserver.entity.Theatres;
import com.nhom20.cimeemappserver.service.AboutService;
import com.nhom20.cimeemappserver.service.MenuService;
import com.nhom20.cimeemappserver.service.MoviesService;
import com.nhom20.cimeemappserver.service.RoomService;
import com.nhom20.cimeemappserver.service.SeatNoService;
import com.nhom20.cimeemappserver.service.SeatTypeService;
import com.nhom20.cimeemappserver.service.SlideService;
import com.nhom20.cimeemappserver.service.TheatreService;
import com.nhom20.cimeemappserver.service.TheatreShowTimeService;
import com.nhom20.cimeemappserver.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RoomRestController {
	private static final long OTP_VALID_DURATION = 5 * 60 * 1000;
	@Autowired
	private RoomService roomService;
	@Autowired
	private SeatNoService seatNoService;
	@Autowired
	private SeatTypeService seatTypeService;

	@GetMapping("/uploadRoom/{id}")
	public void getRoom(@PathVariable int id) {
		try {
			roomService.getTableRoom(id);
		} catch (Exception e) {
			// TODO: handle exception
			roomService.dropTable();
			roomService.getTableRoom(id);
		}
	}

	@GetMapping("/getListRoom")
	public List<Map<String, Object>> getListRoom(HttpServletResponse response) throws IOException {
		com.google.gson.JsonObject job = new JsonObject();
		Gson gson = new Gson();
		List<Object[]> rooms = roomService.listTable();
		Boolean tmp = true;
		String type_="";
		List<Boolean> demo = new ArrayList<>();
		List<String> type = new ArrayList<>();
		List<Integer> seatId = new ArrayList<>();
		Integer seatId_ =0;
		List<Date> seatNos = new ArrayList<>();
		List<String> seatType = new ArrayList<>();
		List<Integer> idSeat = new ArrayList<>();
		List<Object> list = new ArrayList<>();
		for (Object[] objects : rooms) {
			seatNos = seatNoService.setTimeForSeat((String) objects[1], Integer.parseInt((String) objects[2]),
					(String) objects[0]);
		
			for (Date seatNo : seatNos) {
				long currentTimeInMillis = System.currentTimeMillis();
				long otpRequestedTimeInMillis = seatNo.getTime();
				try {
					if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis && seatNo != null) {
						tmp = true;
					}else {
						tmp = false;
					}
				

				} catch (Exception e) {
					// TODO: handle exception
					job.addProperty("message", true);
					tmp = true;
				}
				demo.add(tmp);
				
			}
			}
		for (Object[] objects1 : rooms) {
			seatType = seatTypeService.listType((String) objects1[1], Integer.parseInt((String) objects1[2]),
					(String) objects1[0]);
		for (String seatNo : seatType) {
			try {
				type_=seatNo;
			} catch (Exception e) {
				// TODO: handle exception
				type_="";
			}
			type.add(type_);
			
		}
	}
		for (Object[] objects1 : rooms) {
			idSeat = seatNoService.getSeat((String) objects1[1], Integer.parseInt((String) objects1[2]),
					(String) objects1[0]);
		for (Integer seatNo : idSeat) {
			try {
				seatId_=seatNo;
			} catch (Exception e) {
				// TODO: handle exception
				seatId_=0;
			}
			seatId.add(seatId_);
			
		}
	}
		for (int i = 0; i < rooms.size(); i++) {
			Object[] arr = rooms.get(i);
			list = new ArrayList<>(Arrays.asList(arr));
			list.add(seatId.get(i));
			list.add(demo.get(i));
			list.add(type.get(i));
			rooms.set(i, list.toArray(new Object[0]));
		}
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (Object[] sublist : rooms) {
		    Map<String, Object> map = new HashMap<>();
		    map.put("cinemaName", sublist[0]);
		    map.put("seat", sublist[1]);
		    map.put("seatNoId", sublist[2]);
		    map.put("emptySeat", sublist[3]);
		    map.put("seatId", sublist[4]);
		    map.put("selected", sublist[5]);
		    map.put("seatType", sublist[6]);
		    resultList.add(map);
		}
//		System.out.println(resultList);
		return resultList;
	}

}
