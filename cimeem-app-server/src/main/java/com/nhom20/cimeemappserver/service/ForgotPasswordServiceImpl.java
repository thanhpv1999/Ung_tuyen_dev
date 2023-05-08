package com.nhom20.cimeemappserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.ForgotPasswordDao;
import com.nhom20.cimeemappserver.entity.ForgotPassword;


@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService{

	@Autowired
	private ForgotPasswordDao forgotPasswordDao;
	@Override
	public void addCodeEmail(ForgotPassword forgotPassword) {
		// TODO Auto-generated method stub
		forgotPasswordDao.save(forgotPassword);
	}
	@Override
	public ForgotPassword find(String id) {
		Optional<ForgotPassword> result=forgotPasswordDao.findById(id);
		ForgotPassword theSanPham=null;
		if (result.isPresent()) {
			theSanPham=result.get();
		}else {
			throw new RuntimeException("Did not find product id - "+id);
		}
		return theSanPham;
	}
	@Override
	public void addNewMail(ForgotPassword forgotPassword) {
		// TODO Auto-generated method stub
		forgotPasswordDao.save(forgotPassword);
	}

}
