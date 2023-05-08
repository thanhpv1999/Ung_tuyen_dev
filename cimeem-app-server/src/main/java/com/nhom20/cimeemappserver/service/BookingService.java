package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Booking;
import com.nhom20.cimeemappserver.entity.Menus;


public interface BookingService {

	public List<Object[]> inventoryByCategory(String userId);
	public List<Booking> listBookings();
	public List<Object[]> revenueByCategory( String userId);
	public List<Object[]> revenueByYear(String userId);
	public List<Object[]> revenueByMONTH(String userId);
	public List<Object[]> revenueByQuater(String userId);
	public int countQuantityProduct();
	public int countOrder(String userId) ;
	public int salesOrderDetail(String userId) ;
	public Page<Booking> listBookingsOnPage(Pageable pageable);
	
}
