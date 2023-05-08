package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Price;


public interface PriceService {

	public List<Price> listPrice();
	public double priceByBookId(String id);
	public void save(Price price);
	public List<Price> listPriceByProductId(String productId);
	public List<Price> listPriceByType(int productId);
}
