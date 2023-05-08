package com.nhom20.cimeemappserver.restController;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhom20.cimeemappserver.entity.Genres;
import com.nhom20.cimeemappserver.entity.Movie;
import com.nhom20.cimeemappserver.service.GenresService;
import com.nhom20.cimeemappserver.service.MoviesService;
import com.nhom20.cimeemappserver.service.TheatreShowTimeService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MovieRestController {

	@Autowired
	private GenresService genresService;
	@Autowired
	private MoviesService movieService;

	@GetMapping("/listGen")
	public List<Genres> findAllGenres() {
		return genresService.listGenres();
	}

	@GetMapping("/moviegen/{genId}&{nam}&{now}&{hot}")
	public Page<Movie> moviege(@PathVariable(value = "genId") String genId, @PathVariable String nam,
			@PathVariable String hot, @PathVariable String now, Pageable pageable,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size) {
		pageable = PageRequest.of(page, size);
		// the loai - nam - dang chieu
		if (now.equals("now") && !genId.isEmpty() && !nam.isEmpty()) {
			return movieService.listMoveByGenId1(genId, nam, pageable);
		}
		// the loai - nam - sap chieu
		else if (now.equals("notnow") && !genId.isEmpty() && !nam.isEmpty()) {
			return movieService.listMoveByGenId2(genId, nam, pageable);
		}
		//
		else if (now.isEmpty() && genId.isEmpty() && nam.isEmpty() && hot.isEmpty()) {
			return movieService.getListMovies(pageable);
		}
		// the loai
		else if (nam.isEmpty() && !genId.isEmpty() && now.isEmpty()) {
			return movieService.listMoveByGenId(genId, pageable);
		}
		// nam
		else if (genId.isEmpty() && !nam.isEmpty() && now.isEmpty()) {
			return movieService.listByNam(nam, pageable);
		}
		// dang chieu
		else if (now.equals("now") && genId.isEmpty() && nam.isEmpty()) {
			return movieService.getListTheatreShowTimes(pageable);
		}
		// sap chieu
		else if (now.equals("notnow") && genId.isEmpty() && nam.isEmpty()) {
			return movieService.getListTheatreShowTimesTomorow(pageable);
		}
		// xem nhieu nhat
		else if (hot.equals("hot") && now.isEmpty() && genId.isEmpty() && nam.isEmpty()) {
			return movieService.listMovieOrderByView(pageable);
		}
		// danh gia nhieu nhat
		else if (hot.equals("vote") && now.isEmpty() && genId.isEmpty() && nam.isEmpty()) {
			return movieService.listMovieOrderByVotesAvg(pageable);
		}
		// nam - dang chieu
		else if (now.equals("now") && genId.isEmpty() && !nam.isEmpty()) {
			return movieService.listMoveByNamAndNow(nam, pageable);
		}
		// nam - sap chieu
		else if (now.equals("notnow") && genId.isEmpty() && !nam.isEmpty()) {
			return movieService.listMoveByNamAndNotNow(nam, pageable);
		}
		// nam - da chieu - xem nhieu nhat
		else if (hot.equals("hot") && genId.isEmpty() && !nam.isEmpty() && now.equals("now")) {
			return movieService.listMoveByNamAndNowAndHot(nam, pageable);
		}
		// nam - da chieu - vote nhieu nhat
		else if (hot.equals("vote") && genId.isEmpty() && !nam.isEmpty() && now.equals("now")) {
			return movieService.listMoveByNamAndNowAndVote(nam, pageable);
		}
		// nam - sap chieu - vote nhieu nhat
		else if (hot.equals("vote") && genId.isEmpty() && !nam.isEmpty() && now.equals("notnow")) {
			return movieService.listMoveByNamAndNotNowAndVote(nam, pageable);
		}
		// nam - sap chieu - vote nhieu nhat
		else if (hot.equals("hot") && genId.isEmpty() && !nam.isEmpty() && now.equals("notnow")) {
			return movieService.listMoveByNamAndNotNowAndHot(nam, pageable);
		}
		// the loai - nam
		else {
			return movieService.listMoveByGenId3(genId, nam, pageable);
		}
	}

	@GetMapping("/listMvOrderByView")
	public Page<Movie> findMovieOrderByView(Pageable pageable,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size) {
		pageable = PageRequest.of(page, size);
		return movieService.listMovieOrderByView(pageable);
	}

	@GetMapping("/moviebyname/{name}")
	public Page<Movie> moviebyname(@PathVariable String name, Pageable pageable,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size) {
		pageable = PageRequest.of(page, size);
		return movieService.listByName(name, pageable);
	}

	@GetMapping("/listMvOrderByVote")
	public Page<Movie> findlistMvOrderByVote(Pageable pageable,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size) {
		pageable = PageRequest.of(page, size);
		return movieService.listMovieOrderByVotesAvg(pageable);
	}
}
