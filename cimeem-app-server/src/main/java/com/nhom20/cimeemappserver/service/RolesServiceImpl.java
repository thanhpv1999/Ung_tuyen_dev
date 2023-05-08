package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.RolesDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Roles;



@Service
public class RolesServiceImpl implements RolesService{

	@Autowired
	private RolesDao rolesDao;

	@Override
	public List<Roles> listRoles() {
		// TODO Auto-generated method stub
		return rolesDao.findAll();
	}

	@Override
	public void updateRoleEmployee(String gen, String id) {
		// TODO Auto-generated method stub
		rolesDao.updateRoleEmployee(gen, id);
	}

	@Override
	public void updateRoleUser(String gen, String id) {
		// TODO Auto-generated method stub
		rolesDao.updateRoleUser(gen, id);
	}

	
	

}
