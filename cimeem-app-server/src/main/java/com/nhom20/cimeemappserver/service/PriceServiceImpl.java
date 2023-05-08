package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.PriceDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Price;



@Service
public class PriceServiceImpl implements PriceService{

	@Autowired
	private PriceDao priceDao;

	@Override
	public List<Price> listPrice() {
		// TODO Auto-generated method stub
		return priceDao.findAll();
	}

	@Override
	public double priceByBookId(String id) {
		// TODO Auto-generated method stub
		try {
			return priceDao.priceByBookId(id);
		} catch (Exception e) {
			// TODO: handle exception
			return priceDao.priceByBookIdEx(id);
		}
	}

	@Override
	public void save(Price price) {
		// TODO Auto-generated method stub
		priceDao.save(price);
	}

	@Override
	public List<Price> listPriceByProductId(String productId) {
		// TODO Auto-generated method stub
		return priceDao.listPriceByProductId( productId);
	}

	@Override
	public List<Price> listPriceByType(int productId) {
		// TODO Auto-generated method stub
		return priceDao.listPriceByType(productId);
	}

	
	

}
