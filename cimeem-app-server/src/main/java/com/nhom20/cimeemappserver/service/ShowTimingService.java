package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.ShowTimings;


public interface ShowTimingService {

	public List<ShowTimings> listTimes();
	public List<ShowTimings> getTimeByIdRoomAndIdThre( String theatreId,  int roomId);
	public void saveTiming(ShowTimings theMovie);
	public Page<ShowTimings> getList(Pageable page);
	public void deleteshowTiming(String id);
}
