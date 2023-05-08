package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Cinemas;
import com.nhom20.cimeemappserver.entity.Menus;


public interface CinemasService {

	public List<Cinemas> listCinema();
	public List<Cinemas> listCinemaByThearte(String id);
}
