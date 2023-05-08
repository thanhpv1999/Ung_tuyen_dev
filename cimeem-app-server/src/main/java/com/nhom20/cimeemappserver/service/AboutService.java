package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;


public interface AboutService {

	public List<About> listMenus();
}
