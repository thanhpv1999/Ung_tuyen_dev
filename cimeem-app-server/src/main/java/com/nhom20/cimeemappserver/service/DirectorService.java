package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Director;
import com.nhom20.cimeemappserver.entity.Menus;


public interface DirectorService {

	public Page<Director> lisDirector(Pageable pageable);

	public void saveDirAndBook(String actor, String movieId);

	public List<Director> listDirector();
}
