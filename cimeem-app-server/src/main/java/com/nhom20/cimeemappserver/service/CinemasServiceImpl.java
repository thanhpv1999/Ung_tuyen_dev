package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.CinemasDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Cinemas;
import com.nhom20.cimeemappserver.entity.Menus;



@Service
public class CinemasServiceImpl implements CinemasService{

	@Autowired
	private CinemasDao cinemasDao;

	@Override
	public List<Cinemas> listCinema() {
		// TODO Auto-generated method stub
		return cinemasDao.findAll();
	}

	@Override
	public List<Cinemas> listCinemaByThearte(String id) {
		// TODO Auto-generated method stub
		return cinemasDao.listCinemaByThearte(id);
	}

	

}
