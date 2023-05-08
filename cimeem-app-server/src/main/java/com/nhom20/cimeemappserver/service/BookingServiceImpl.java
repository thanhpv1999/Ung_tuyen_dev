package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.BookingDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Booking;
import com.nhom20.cimeemappserver.entity.Menus;



@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingDao bookingDao;

	@Override
	public List<Object[]> inventoryByCategory(String userId) {
		// TODO Auto-generated method stub
		return bookingDao.inventoryByCategory(userId);
	}

	@Override
	public List<Booking> listBookings() {
		// TODO Auto-generated method stub
		return bookingDao.findAll();
	}

	@Override
	public List<Object[]> revenueByCategory(String userId) {
		// TODO Auto-generated method stub
		return bookingDao.revenueByCategory(userId);
	}

	@Override
	public List<Object[]> revenueByYear(String userId) {
		// TODO Auto-generated method stub
		return bookingDao.revenueByYear(userId);
	}

	@Override
	public List<Object[]> revenueByMONTH(String userId) {
		// TODO Auto-generated method stub
		return bookingDao.revenueByMONTH(userId);
	}

	@Override
	public List<Object[]> revenueByQuater(String userId) {
		// TODO Auto-generated method stub
		return bookingDao.revenueByQuater(userId);
	}

	@Override
	public int countQuantityProduct() {
		// TODO Auto-generated method stub
		try {
			return bookingDao.countQuantityProduct();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	public int countOrder(String userId) {
		// TODO Auto-generated method stub
		try {
			return bookingDao.countOrder(userId);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	public int salesOrderDetail(String userId) {
		// TODO Auto-generated method stub
		try {
			return bookingDao.salesOrderDetail(userId);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	public Page<Booking> listBookingsOnPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return bookingDao.findAll(pageable);
	}

	

}
