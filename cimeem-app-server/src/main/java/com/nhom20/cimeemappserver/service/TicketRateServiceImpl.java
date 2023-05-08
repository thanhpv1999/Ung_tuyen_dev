package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.TicketRateDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.TicketRate;



@Service
public class TicketRateServiceImpl implements TicketRateService{

	@Autowired
	private TicketRateDao ticketRateDao;

	@Override
	public List<TicketRate> listTicketRate(String id) {
		// TODO Auto-generated method stub
		return ticketRateDao.listTicketRate(id);
	}

	

}
