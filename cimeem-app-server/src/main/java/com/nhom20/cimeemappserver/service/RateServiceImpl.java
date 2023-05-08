package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.RateDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Rate;



@Service
public class RateServiceImpl implements RateService{

	@Autowired
	private RateDao rateDao;

	@Override
	public Page<Rate> listRates(Pageable pageable) {
		// TODO Auto-generated method stub
		return rateDao.findAll(pageable);
	}

	@Override
	public List<Rate> listRates() {
		// TODO Auto-generated method stub
		return rateDao.findAll();
	}

	
	

}
