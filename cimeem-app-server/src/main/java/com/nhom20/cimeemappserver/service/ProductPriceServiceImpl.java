package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.ProductPriceDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.ProductPrice;



@Service
public class ProductPriceServiceImpl implements ProductPriceService{

	@Autowired
	private ProductPriceDao productPriceDao;

	@Override
	public void savePriceAndMovie(String percentage, String bookId, boolean active) {
		// TODO Auto-generated method stub
		productPriceDao.savePriceAndMovie(percentage, bookId, active);
	}

	

	
	

}
