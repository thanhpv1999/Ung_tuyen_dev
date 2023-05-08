package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.SeatType;


public interface SeatTypeService {

	public List<SeatType> listSeatType(String id);
	public List<String> listType( String idNo, int name, String idSeat);
	public List<SeatType> listTypeOk();
}
