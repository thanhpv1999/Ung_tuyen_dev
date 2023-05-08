package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.GenresDao;
import com.nhom20.cimeemappserver.entity.Genres;

@Service
public class GenresServiceImpl implements GenresService{

	@Autowired
	private GenresDao genresDao;
	@Override
	public List<Genres> listGenres() {
		// TODO Auto-generated method stub
		return genresDao.findAll();
	}
	@Override
	public Page<Genres> listGenresOnPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return genresDao.findAll(pageable);
	}
	@Override
	public void saveGenAndMovie(String values, String movieId) {
		// TODO Auto-generated method stub
		genresDao.saveGenAndMovie( values,  movieId);
	}

}
