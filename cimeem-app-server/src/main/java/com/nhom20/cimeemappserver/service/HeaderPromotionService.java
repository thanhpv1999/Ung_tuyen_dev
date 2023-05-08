package com.nhom20.cimeemappserver.service;

import java.util.List;

import com.nhom20.cimeemappserver.entity.HeaderPromotion;




public interface HeaderPromotionService {

	public List<HeaderPromotion> getListHeaderPromotion();
	public void updateHeaderPromotion(String gen,String gen2,String gen3, String id);
	public void saveHeader(HeaderPromotion theMovie);
	public void deleteHeader(String id);
}
