package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.ShowTimeDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.ShowTimings;



@Service
public class ShowTimingServiceImpl implements ShowTimingService{

	@Autowired
	private ShowTimeDao showTimeDao;

	@Override
	public List<ShowTimings> listTimes() {
		// TODO Auto-generated method stub
		return showTimeDao.findAll();
	}
	@Override
	public List<ShowTimings> getTimeByIdRoomAndIdThre(String theatreId, int roomId) {
		// TODO Auto-generated method stub
		return showTimeDao.getTimeByIdRoomAndIdThre(theatreId, roomId);
	}
	@Override
	public void saveTiming(ShowTimings theMovie) {
		// TODO Auto-generated method stub
		showTimeDao.save(theMovie);
	}
	@Override
	public Page<ShowTimings> getList(Pageable page) {
		// TODO Auto-generated method stub
		return showTimeDao.findAll(page);
	}
	@Override
	public void deleteshowTiming(String id) {
		// TODO Auto-generated method stub
		showTimeDao.deleteById(id);
	}
	
	

}
