package com.nhom20.cimeemappserver.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Cinemas;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Movie;
import com.nhom20.cimeemappserver.entity.Slides;
import com.nhom20.cimeemappserver.entity.TheatreShowTimes;
import com.nhom20.cimeemappserver.entity.Theatres;
import com.nhom20.cimeemappserver.service.AboutService;
import com.nhom20.cimeemappserver.service.MenuService;
import com.nhom20.cimeemappserver.service.MoviesService;
import com.nhom20.cimeemappserver.service.SlideService;
import com.nhom20.cimeemappserver.service.TheatreService;
import com.nhom20.cimeemappserver.service.TheatreShowTimeService;
import com.nhom20.cimeemappserver.service.UserService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SortRestController {

	@Autowired
	private TheatreShowTimeService theatreShowTimeService;
	@Autowired
	private TheatreService theatreService;
	
	@GetMapping("/listTheatreShowTimes")
	public List<Object[]> listTimeByTheatre() {
		return theatreShowTimeService.getListTheatreShow();
	}
	
	@GetMapping("/listTheatre/{id}")
	public List<Object[]> listTheatre(@PathVariable String id) {
		return theatreShowTimeService.getListTheatre( id);
	}
	
	@GetMapping("/listTime/{id}&{movieId}")
	public List<Object[]> listTime(@PathVariable String id,@PathVariable String movieId) {
		return theatreShowTimeService.getListTime( id,movieId);
	}

	@GetMapping("/listTimes")
	public List<TheatreShowTimes> listTime() {
		return theatreShowTimeService.getListTimes();
	}
	
	@GetMapping("/listTheatreByTime/{date}")
	public List<TheatreShowTimes> listTimeByTheatre(@PathVariable String date) {
		return theatreShowTimeService.getListTimeByTheatre(date);
	}
	
	@GetMapping("/listMovieByDayAndTheatre/{date}&{id}")
	public List<Object[]> listMovieByDayAndTheatre(@PathVariable String date,@PathVariable String id) {
		return theatreShowTimeService.getListMovieByDayandTheatre(date,id);
	}

	@GetMapping("/listRoomByDayAndTheatre/{date}&{theatreId}&{movieId}")
	public List<Object[]> listRoomByDayAndTheatre(@PathVariable String date,@PathVariable String theatreId,@PathVariable String movieId) {
		return theatreShowTimeService.getListRoomByDayandTheatre(date,theatreId,movieId);
	}
	
	@GetMapping("/listTheatre")
	public List<Theatres> listTheatre() {
		
		
		return theatreService.listTheatres();
	}
	
	@GetMapping("/listMovieByTheatre/{id}")
	public List<Object[]> listMovieByTheatre(@PathVariable String id) {
		return theatreShowTimeService.getListMovieByTheatre(id);
	}
	
	@GetMapping("/listDayByTheatre/{theatre}&{movieId}")
	public List<Object[]> listDayByTheatre(@PathVariable String theatre,@PathVariable String movieId) {
		return theatreShowTimeService.getListDayByTheatre(theatre, movieId);
	}
	
	@GetMapping("/listRoomByTheatreAndDay/{theatre}&{movieId}&{day}")
	public List<Object[]> listRoomByTheatreAndDay(@PathVariable String theatre,@PathVariable String movieId,@PathVariable String day) {
		return theatreShowTimeService.getListRoomByTheatreAndDay(theatre, movieId, day);
	}
	
	@GetMapping("/listRoomByAll/{theatre}&{movieId}&{day}&{timeId}&{roomId}")
	public List<TheatreShowTimes> listRoomByAllSort(@PathVariable String theatre,@PathVariable String movieId,@PathVariable String day,@PathVariable String timeId,@PathVariable int roomId) {
		return theatreShowTimeService.getRoomByAllSort(theatre, movieId, day, timeId, roomId);
	}
	
	@GetMapping("/listTheatreByDayAndMovie/{date}&{movieId}")
	public List<TheatreShowTimes> listTheatreByDayAndMovie(@PathVariable String date,@PathVariable String movieId) {
		return theatreShowTimeService.getTheatreByMovieAndDay(movieId,date);
	}
}
