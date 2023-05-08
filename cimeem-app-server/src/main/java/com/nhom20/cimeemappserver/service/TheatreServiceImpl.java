package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.SlideDao;
import com.nhom20.cimeemappserver.dao.TheatreDao;
import com.nhom20.cimeemappserver.entity.Room;
import com.nhom20.cimeemappserver.entity.Slides;
import com.nhom20.cimeemappserver.entity.Theatres;

@Service
public class TheatreServiceImpl implements TheatreService{

	@Autowired
	private TheatreDao theatreDao;

	@Override
	public List<Theatres> listTheatres() {
		// TODO Auto-generated method stub
		return theatreDao.findAll();
	}

	
	

}
