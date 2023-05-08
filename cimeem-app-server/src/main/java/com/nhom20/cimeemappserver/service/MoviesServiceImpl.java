package com.nhom20.cimeemappserver.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.nhom20.cimeemappserver.dao.MovieDao;
import com.nhom20.cimeemappserver.dao.TheatreShowTimeDao;
import com.nhom20.cimeemappserver.dao.UserDao;
import com.nhom20.cimeemappserver.entity.Address;
import com.nhom20.cimeemappserver.entity.CustomerPassword;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Movie;
import com.nhom20.cimeemappserver.entity.TheatreShowTimes;
import com.nhom20.cimeemappserver.entity.Users;


@Service
public class MoviesServiceImpl implements MoviesService{

	@Autowired
	private MovieDao movieDao;
	@Override
	public List<Movie> getListTheatreShowTimes() {
		// TODO Auto-generated method stub
		return movieDao.getListTheatreShowTimes();
	}

	@Override
	public List<Movie> getListTheatreShowTimesTomorow() {
		// TODO Auto-generated method stub
		return movieDao.getListTheatreShowTimesTomorow();
	}
	@Override
	public Page<Movie> getListMovies(Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.findAll(pageable);
	}

	

	@Override
	public Page<Movie> listMovieOrderByView(Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMovieOrderByView(pageable);
	}

	@Override
	public Page<Movie> listByName(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listByName(name, pageable);
	}


	@Override
	public Page<Movie> listMovieOrderByVotesAvg(Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMovieOrderByVotesAvg(pageable);
	}



	@Override
	public Page<Movie> listMoveByGenId(String genId, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMoveByGenId(genId, pageable);
	}

	@Override
	public Page<Movie> listMoveByGenId1(String genId, String nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMoveByGenId1(genId, nam, pageable);
	}

	@Override
	public Page<Movie> listMoveByGenId2(String genId, String nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMoveByGenId2(genId, nam, pageable);
	}



	@Override
	public Page<Movie> listMoveByGenId3(String genId, String nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMoveByGenId3(genId, nam, pageable);
	}



	@Override
	public Page<Movie> listByNam(String nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listByNam(nam, pageable);
	}



	@Override
	public Page<Movie> getListTheatreShowTimes(Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.getListTheatreShowTimes(pageable);
	}



	@Override
	public Page<Movie> getListTheatreShowTimesTomorow(Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.getListTheatreShowTimesTomorow(pageable);
	}



	@Override
	public Page<Movie> listMoveByNamAndNow(String nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMoveByNamAndNow(nam, pageable);
	}



	@Override
	public Page<Movie> listMoveByNamAndNotNow(String nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMoveByNamAndNotNow(nam, pageable);
	}



	@Override
	public Page<Movie> listMoveByNamAndNowAndHot(String nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMoveByNamAndNowAndHot(nam, pageable);
	}



	@Override
	public Page<Movie> listMoveByNamAndNowAndVote(String nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMoveByNamAndNowAndVote(nam, pageable);
	}



	@Override
	public Page<Movie> listMoveByNamAndNotNowAndHot(String nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMoveByNamAndNotNowAndHot(nam, pageable);
	}



	@Override
	public Page<Movie> listMoveByNamAndNotNowAndVote(String nam, Pageable pageable) {
		// TODO Auto-generated method stub
		return movieDao.listMoveByNamAndNotNowAndVote(nam, pageable);
	}



	@Override
	public Movie getMovieById(String id) {
		// TODO Auto-generated method stub
		Optional<Movie> result = movieDao.findById(id);
		Movie books = null;
		if (result.isPresent()) {
			books = result.get();
		} else {
			throw new RuntimeException("Did not find product id - " + id);
		}
		return books;
	}



	@Override
	public void saveMovie(Movie theMovie) {
		// TODO Auto-generated method stub
		movieDao.save(theMovie);
	}



	@Override
	public void deleteMovie(String id) {
		// TODO Auto-generated method stub
		movieDao.deleteById(id);
	}


	
}
