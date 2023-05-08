package com.nhom20.cimeemappserver.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.SeatNo;


public interface SeatNoService {

	public List<Date> setTimeForSeat(String idNo, int name,String idSeat);
	public void updateTimeForSeat(int idNo,int idSeat);
	public List<SeatNo> getSeatNo(int idNo,int idSeat);
	public List<Object[]> getPrice(int idNo,int idSeat);
	public List<Integer> getSeat( String idNo, int name, String idSeat);
}
