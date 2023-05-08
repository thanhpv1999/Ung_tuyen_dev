package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.entity.Menus;



@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenusDao menusDao;
	
	@Override
	public List<Menus> listMenus() {
		// TODO Auto-generated method stub
		return menusDao.findAll();
	}

	@Override
	public List<Menus> listMenuMaster(String replyId) {
		// TODO Auto-generated method stub
		return menusDao.listMenuMaster(replyId);
	}

}
