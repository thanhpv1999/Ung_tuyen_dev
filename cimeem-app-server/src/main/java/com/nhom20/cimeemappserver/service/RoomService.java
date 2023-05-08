package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Room;


public interface RoomService {

	public void getTableRoom(int id);
	public List<Object[]> listTable();
	public void dropTable();
	public List<Room> listRoomByCinema(String name);
}
