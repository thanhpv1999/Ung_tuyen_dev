package com.nhom20.cimeemappserver.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Movie;
import com.nhom20.cimeemappserver.entity.Slides;
import com.nhom20.cimeemappserver.entity.TheatreShowTimes;
import com.nhom20.cimeemappserver.service.AboutService;
import com.nhom20.cimeemappserver.service.MenuService;
import com.nhom20.cimeemappserver.service.MoviesService;
import com.nhom20.cimeemappserver.service.SlideService;
import com.nhom20.cimeemappserver.service.TheatreShowTimeService;
import com.nhom20.cimeemappserver.service.UserService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HomeRestController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private TheatreShowTimeService theatreShowTimeService;
	@Autowired
	private MoviesService moviesService;
	@Autowired
	private AboutService aboutService;
	
	@GetMapping("/listMenus")
	public List<Menus> hello() {
		return menuService.listMenus();
	}
	
	@GetMapping("/listMenusMaster/{id}")
	public List<Menus> hello(@PathVariable String id) {
		return menuService.listMenuMaster(id);
	}
	
	@GetMapping("/listSlide")
	public List<Slides> listSlide() {
		return slideService.listSlide();
	}
	
	@GetMapping("/listTheatreShowTimesNow")
	public List<Movie> listTimeNow() {
		return moviesService.getListTheatreShowTimes();
	}
	@GetMapping("/listTheatreShowTimesTomorrow")
	public List<Movie> listTimeTm() {
		
		return moviesService.getListTheatreShowTimesTomorow();
	}
	
	@GetMapping("/listMovie")
	public Page<Movie> listLive(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size) {

		Pageable pageable = PageRequest.of(page, size);

		return moviesService.getListMovies(pageable);
	}
	
	@GetMapping("/listShowMovie/{id}")
	public List<TheatreShowTimes> listMovie(@PathVariable String id) {
		
		return theatreShowTimeService.getListTheatreByMovie(id);
	}
	@GetMapping("/about")
	public List<About> about() {
		
		return aboutService.listMenus();
	}

}
