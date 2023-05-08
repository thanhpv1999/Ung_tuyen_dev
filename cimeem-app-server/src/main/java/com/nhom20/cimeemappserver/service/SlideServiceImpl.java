package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.SlideDao;
import com.nhom20.cimeemappserver.entity.Slides;

@Service
public class SlideServiceImpl implements SlideService{

	@Autowired
	private SlideDao slideDao;
	@Override
	public List<Slides> listSlide() {
		// TODO Auto-generated method stub
		return slideDao.findAll();
	}

}
