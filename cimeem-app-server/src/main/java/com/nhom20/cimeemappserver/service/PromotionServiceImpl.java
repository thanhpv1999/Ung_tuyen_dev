package com.nhom20.cimeemappserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.PromotionDao;
import com.nhom20.cimeemappserver.entity.Promotion;


@Service
public class PromotionServiceImpl implements PromotionService{

	@Autowired
	private PromotionDao promotionDao;

	@Override
	public List<Promotion> listPromotionByBook(String bookId) {
		// TODO Auto-generated method stub
		return promotionDao.listPromotionByBook(bookId);
	}

	@Override
	public List<Promotion> listPromotionByHeaderEpx() {
		// TODO Auto-generated method stub
		return promotionDao.listPromotionByHeaderEpx();
	}

	@Override
	public List<Promotion> listPromotionByHeader(String headerId) {
		// TODO Auto-generated method stub
		return promotionDao.listPromotionByHeader(headerId);
	}

	@Override
	public void deletePromotion(String id) {
		// TODO Auto-generated method stub
		promotionDao.deleteById(id);
	}

	
	
}
