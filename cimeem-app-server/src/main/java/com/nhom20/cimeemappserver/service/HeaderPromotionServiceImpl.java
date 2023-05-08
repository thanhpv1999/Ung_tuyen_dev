package com.nhom20.cimeemappserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.HeaderPromotionDao;
import com.nhom20.cimeemappserver.entity.HeaderPromotion;


@Service
public class HeaderPromotionServiceImpl implements HeaderPromotionService{

	@Autowired
	private HeaderPromotionDao headerPromotionDao;

	@Override
	public List<HeaderPromotion> getListHeaderPromotion() {
		// TODO Auto-generated method stub
		return headerPromotionDao.findAll();
	}

	@Override
	public void updateHeaderPromotion(String gen, String gen2,String gen3, String id) {
		// TODO Auto-generated method stub
		 headerPromotionDao.updateHeaderPromotion( gen,  gen2,  gen3,id);
	}

	@Override
	public void saveHeader(HeaderPromotion theMovie) {
		// TODO Auto-generated method stub
		headerPromotionDao.save(theMovie);
	}

	@Override
	public void deleteHeader(String id) {
		// TODO Auto-generated method stub
		headerPromotionDao.deleteById(id);
	}

	
	
}
