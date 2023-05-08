package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.StatusDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;



@Service
public class StatusServiceImpl implements StatusService{

	@Autowired
	private StatusDao statusDao;

	@Override
	public void confirm(String id) {
		// TODO Auto-generated method stub
		statusDao.confirm(id);
	}

	@Override
	public void cancel(String id) {
		// TODO Auto-generated method stub
		statusDao.cancel(id);
	}

	
	

}
