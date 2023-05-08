package com.nhom20.cimeemappserver.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazonaws.services.identitymanagement.model.User;
import com.nhom20.cimeemappserver.dao.AddressDao;
import com.nhom20.cimeemappserver.dao.LocationDao;
import com.nhom20.cimeemappserver.dao.TheatreShowTimeDao;
import com.nhom20.cimeemappserver.dao.UserDao;
import com.nhom20.cimeemappserver.entity.Address;
import com.nhom20.cimeemappserver.entity.CustomerPassword;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Movie;
import com.nhom20.cimeemappserver.entity.ShowTimings;
import com.nhom20.cimeemappserver.entity.TheatreShowTimes;
import com.nhom20.cimeemappserver.entity.Users;


@Service
public class TheatreShowTimeServiceImpl implements TheatreShowTimeService{

	@Autowired
	private TheatreShowTimeDao theatreShowTimeDao;

	

	@Override
	public List<Object[]> getListTheatreShow() {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getListTheatreShow();
	}

	@Override
	public List<Object[]> getListTheatre(String id) {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getListTheatre(id);
	}

	@Override
	public List<Object[]> getListTime(String id,String movieId) {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getListTime(id,movieId);
	}

	@Override
	public List<TheatreShowTimes> getListTheatreByMovie(String id) {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getListTheatreByMovie(id);
	}

	@Override
	public List<TheatreShowTimes> getListTimes() {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.findAll();
	}

	@Override
	public List<TheatreShowTimes> getListTimeByTheatre(String date) {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getListTimeByTheatre( date);
	}

	@Override
	public List<Object[]> getListMovieByDayandTheatre(String date, String id) {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getListMovieByDayandTheatre( date,id);
	}

	@Override
	public List<Object[]> getListRoomByDayandTheatre(String date, String theatreId, String movieId) {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getListRoomByDayandTheatre(date, theatreId, movieId);
	}

	@Override
	public List<Object[]> getListMovieByTheatre(String theatreId) {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getListMovieByTheatre(theatreId);
	}

	@Override
	public List<Object[]> getListDayByTheatre(String theatreId, String movieId) {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getListDayByTheatre(theatreId, movieId);
	}

	@Override
	public List<Object[]> getListRoomByTheatreAndDay(String theatreId, String movieId, String day) {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getListRoomByTheatreAndDay(theatreId, movieId, day);
	}

	@Override
	public List<TheatreShowTimes> getRoomByAllSort(String theatreId, String movieId, String day, String timeId, int roomId) {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getRoomByAllSort(theatreId, movieId, day, timeId, roomId);
	}

	@Override
	public void saveShow(TheatreShowTimes theMovie) {
		// TODO Auto-generated method stub
		theatreShowTimeDao.save(theMovie);
	}

	@Override
	public List<TheatreShowTimes> getTheatreByMovieAndDay(String movieId, String day) {
		// TODO Auto-generated method stub
		return theatreShowTimeDao.getTheatreByMovieAndDay(movieId, day);
	}

	



	
	

	
}
