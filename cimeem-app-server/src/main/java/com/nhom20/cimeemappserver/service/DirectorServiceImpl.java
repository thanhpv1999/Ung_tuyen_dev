package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.DirectorDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Director;
import com.nhom20.cimeemappserver.entity.Menus;



@Service
public class DirectorServiceImpl implements DirectorService{

	@Autowired
	private DirectorDao directorDao;

	@Override
	public Page<Director> lisDirector(Pageable pageable) {
		// TODO Auto-generated method stub
		return directorDao.findAll(pageable);
	}

	@Override
	public void saveDirAndBook(String actor, String movieId) {
		// TODO Auto-generated method stub
		directorDao.saveDirAndBook( actor,  movieId);
	}

	@Override
	public List<Director> listDirector() {
		// TODO Auto-generated method stub
		return directorDao.findAll();
	}

	
	

}
