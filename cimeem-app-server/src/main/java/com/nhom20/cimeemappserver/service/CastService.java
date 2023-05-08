package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Cast;
import com.nhom20.cimeemappserver.entity.Menus;


public interface CastService {

	public Page<Cast> listMenus(Pageable pageable);

	public void saveCasAndBook(String actor, String movieId);

	public List<Cast> listCast();
}
