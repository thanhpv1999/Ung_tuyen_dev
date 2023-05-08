package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.LanguageDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Languagess;
import com.nhom20.cimeemappserver.entity.Menus;



@Service
public class LanguagesServiceImpl implements LanguageService{

	@Autowired
	private LanguageDao languageDao;

	@Override
	public Page<Languagess> listLanguagess(Pageable pageable) {
		// TODO Auto-generated method stub
		return languageDao.findAll(pageable);
	}

	
	

}
