package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.SeatTypeDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.SeatType;



@Service
public class SeatTypeServiceImpl implements SeatTypeService{

	@Autowired
	private SeatTypeDao seatTypeDao;

	@Override
	public List<SeatType> listSeatType(String id) {
		// TODO Auto-generated method stub
		return seatTypeDao.listSeatType( id);
	}

	@Override
	public List<String> listType(String idNo, int name, String idSeat) {
		// TODO Auto-generated method stub
		return seatTypeDao.listType(idNo, name, idSeat);
	}

	@Override
	public List<SeatType> listTypeOk() {
		// TODO Auto-generated method stub
		return seatTypeDao.findAll();
	}
	

}
