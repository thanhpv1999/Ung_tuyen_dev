package com.nhom20.cimeemappserver.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.SeatNoDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.SeatNo;
import com.nhom20.cimeemappserver.entity.Users;

@Service
public class SeatNoServiceImpl implements SeatNoService {

	@Autowired
	private SeatNoDao seatNoDao;

	@Override
	public List<SeatNo> getSeatNo(int idNo, int idSeat) {
		return seatNoDao.getSeatNo(idNo, idSeat);
	}

	@Override
	public List<Object[]> getPrice(int idNo, int idSeat) {
		// TODO Auto-generated method stub
		return seatNoDao.getPrice(idNo, idSeat);
	}

	@Override
	public List<Date> setTimeForSeat(String idNo, int name, String idSeat) {
		// TODO Auto-generated method stub
		return seatNoDao.setTimeForSeat(idNo, name, idSeat);
	}

	@Override
	public void updateTimeForSeat(int idNo, int idSeat) {
		// TODO Auto-generated method stub
		seatNoDao.updateTimeForSeat(idNo, idSeat);
	}

	@Override
	public List<Integer> getSeat(String idNo, int name, String idSeat) {
		// TODO Auto-generated method stub
		return seatNoDao.getSeat(idNo, name, idSeat);
	}



	

}
