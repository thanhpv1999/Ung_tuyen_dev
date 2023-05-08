package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.RoomDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Room;



@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomDao roomDao;

	@Override
	public void getTableRoom(int id) {
		// TODO Auto-generated method stub
		roomDao.getTableRoom(id);
	}

	@Override
	public List<Object[]> listTable() {
		// TODO Auto-generated method stub
		return roomDao.listTable();
	}

	@Override
	public void dropTable() {
		// TODO Auto-generated method stub
		roomDao.dropTable();
	}

	@Override
	public List<Room> listRoomByCinema(String name) {
		// TODO Auto-generated method stub
		return roomDao.listRoomByCinema(name);
	}
	

	
	

}
