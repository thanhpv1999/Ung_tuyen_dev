package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Menus;


public interface MenuService {

	public List<Menus> listMenus();
	public List<Menus> listMenuMaster(String replyId);
}
