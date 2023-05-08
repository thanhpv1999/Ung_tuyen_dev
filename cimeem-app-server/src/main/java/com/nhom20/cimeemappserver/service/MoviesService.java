package com.nhom20.cimeemappserver.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


import com.nhom20.cimeemappserver.entity.Movie;


public interface MoviesService {
	public List<Movie> getListTheatreShowTimes();
	public List<Movie> getListTheatreShowTimesTomorow();
	public Page<Movie> getListMovies(Pageable pageable);
	public Page<Movie> listMovieOrderByView(Pageable pageable);
	public Page<Movie> listMoveByGenId(String genId,Pageable pageable);
	public Page<Movie> listMoveByGenId1(String genId,String nam,Pageable pageable);
	public Page<Movie> listMoveByGenId2(String genId,String nam,Pageable pageable);
	public Page<Movie> listMoveByGenId3(String genId,String nam,Pageable pageable);
	public Page<Movie> listByName(String name,Pageable pageable);
	public Page<Movie> listMovieOrderByVotesAvg(Pageable pageable);
	public Page<Movie> listByNam(String nam,Pageable pageable);
	public Page<Movie> getListTheatreShowTimes(Pageable pageable);
	public Page<Movie> getListTheatreShowTimesTomorow(Pageable pageable);
	public Page<Movie> listMoveByNamAndNow(String nam,Pageable pageable);
	public Page<Movie> listMoveByNamAndNotNow(String nam,Pageable pageable);
	public Page<Movie> listMoveByNamAndNowAndHot(String nam, Pageable pageable);
	public Page<Movie> listMoveByNamAndNowAndVote(String nam, Pageable pageable);
	public Page<Movie> listMoveByNamAndNotNowAndHot( String nam, Pageable pageable);
	public Page<Movie> listMoveByNamAndNotNowAndVote(String nam, Pageable pageable);
	public Movie getMovieById(String id);
	public void saveMovie(Movie theMovie);
	public void deleteMovie(String id);
}
