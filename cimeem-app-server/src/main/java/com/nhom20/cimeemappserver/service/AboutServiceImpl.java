package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;



@Service
public class AboutServiceImpl implements AboutService{

	@Autowired
	private AboutDao aboutDao;

	@Override
	public List<About> listMenus() {
		// TODO Auto-generated method stub
		return aboutDao.findAll();
	}
	

}
