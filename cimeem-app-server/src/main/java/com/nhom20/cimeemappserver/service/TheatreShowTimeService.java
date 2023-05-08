package com.nhom20.cimeemappserver.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.nhom20.cimeemappserver.entity.Address;
import com.nhom20.cimeemappserver.entity.CustomerPassword;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Movie;
import com.nhom20.cimeemappserver.entity.ShowTimings;
import com.nhom20.cimeemappserver.entity.TheatreShowTimes;
import com.nhom20.cimeemappserver.entity.Users;


public interface TheatreShowTimeService {

	
	public List<Object[]> getListTheatreShow();
	public List<Object[]> getListTheatre(String id);
	public List<Object[]> getListTime(String id,String movieId);
	public List<TheatreShowTimes> getListTheatreByMovie(String id);
	public List<TheatreShowTimes> getListTimes();
	public List<TheatreShowTimes> getListTimeByTheatre(String date);
	public List<Object[]> getListMovieByDayandTheatre(String date,String id);
	public List<Object[]> getListRoomByDayandTheatre(String date,String theatreId,String movieId);
	public List<Object[]> getListMovieByTheatre(String theatreId);
	public List<Object[]> getListDayByTheatre(String theatreId,String movieId);
	public List<Object[]> getListRoomByTheatreAndDay(String theatreId,String movieId,String day);
	public List<TheatreShowTimes> getRoomByAllSort(String theatreId,String movieId,String day,String timeId,int roomId);
	public void saveShow(TheatreShowTimes theMovie);
	public List<TheatreShowTimes> getTheatreByMovieAndDay( String movieId,  String day);
	
}
