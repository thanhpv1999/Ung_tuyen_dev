package com.nhom20.cimeemappserver.restController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nhom20.cimeemappserver.entity.SeatNo;
import com.nhom20.cimeemappserver.service.SeatNoService;
import com.nhom20.cimeemappserver.service.SeatTypeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SeatNoRestController {
	private static final long OTP_VALID_DURATION = 5 * 60 * 1000;
	@Autowired
	private SeatNoService seatNoService;
	@Autowired
	private SeatTypeService seatTypeService;

	@GetMapping("/setTimeForSeat/{idNo}&{idSeat}")
	public String getListSeat(@PathVariable int idNo, @PathVariable int idSeat) {
//		List<SeatNo> seatNos = new ArrayList<>();
//		seatNos = seatNoService.setTimeForSeat(idNo, idSeat);
//		for (SeatNo seatNo : seatNos) {

//			System.out.println(seatNo.setTimeForSeat());
//		}
		seatNoService.updateTimeForSeat(idNo, idSeat);
		return "ok";
	}

	
	@GetMapping("/getStatusSeat/{idNo}&{idSeat}&{name}")
	public void getStatusSeat(@PathVariable String idNo, @PathVariable int idSeat,@PathVariable String name, HttpServletResponse response)
			throws IOException {
		List<Date> seatNos = new ArrayList<>();

		com.google.gson.JsonObject job = new JsonObject();

		Gson gson = new Gson();
		seatNos = seatNoService.setTimeForSeat(idNo, idSeat, name);
		for (Date seatNo : seatNos) {
			boolean _tmp=true;
			long currentTimeInMillis = System.currentTimeMillis();
			long otpRequestedTimeInMillis = seatNo.getTime();
			
			try {
				if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis && seatNo !=null ) {
					
					_tmp= true;
				}

				_tmp= false;
				job.addProperty("message", _tmp);

				response.getWriter().write(gson.toJson(job));
			} catch (Exception e) {
				// TODO: handle exception
				job.addProperty("message",true);
				response.getWriter().write(gson.toJson(job));
			}
//			
		}
	}

	@PostMapping("/getSeatNo/{idNo}&{idSeat}")
	public List<SeatNo> getSeatNo(@PathVariable int idNo, @PathVariable int idSeat, HttpServletResponse response)
			throws IOException {

		try {
//			System.out.println(seatNoService.getSeatNo(idNo,idSeat).get(0).getSeat().getSeatId());
			List<SeatNo> seatNos=seatNoService.getSeatNo(idNo, idSeat);
			for (SeatNo seatNo : seatNos) {
				seatNo.setPrice(seatNoService.getPrice(idSeat,idNo ));
			}
			return seatNos;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}
	@GetMapping("/getOk/{idNo}&{idSeat}&{name}")
	public void getOk(@PathVariable String idNo, @PathVariable int idSeat,@PathVariable String name, HttpServletResponse response)
			throws IOException {
		List<String> seatNos = new ArrayList<>();

		com.google.gson.JsonObject job = new JsonObject();

		Gson gson = new Gson();
		seatNos = seatTypeService.listType(idNo, idSeat, name);
		for (String seatNo : seatNos) {
		try {
				job.addProperty("message", seatNo);

				response.getWriter().write(gson.toJson(job));
			} catch (Exception e) {
				// TODO: handle exception
				job.addProperty("message",true);
				response.getWriter().write(gson.toJson(job));
			}
//			
		}
	}
}
