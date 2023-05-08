package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.CastDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Cast;
import com.nhom20.cimeemappserver.entity.Menus;



@Service
public class CastServiceImpl implements CastService{

	@Autowired
	private CastDao castDao;

	@Override
	public Page<Cast> listMenus(Pageable pageable) {
		// TODO Auto-generated method stub
		return castDao.findAll(pageable);
	}

	@Override
	public void saveCasAndBook(String actor, String movieId) {
		// TODO Auto-generated method stub
		castDao.saveCasAndBook( actor,  movieId);
	}

	@Override
	public List<Cast> listCast() {
		// TODO Auto-generated method stub
		return castDao.findAll();
	}

	
	

}
