package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Promotion;




public interface PromotionService {

	public List<Promotion> listPromotionByBook( String bookId);
	public List<Promotion> listPromotionByHeader( String headerId);
	public List<Promotion> listPromotionByHeaderEpx();
	public void deletePromotion(String id);
}
