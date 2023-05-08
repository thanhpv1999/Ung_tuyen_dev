package com.nhom20.cimeemappserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.CustomerPasswordDao;
import com.nhom20.cimeemappserver.dao.ForgotPasswordDao;
import com.nhom20.cimeemappserver.entity.CustomerPassword;
import com.nhom20.cimeemappserver.entity.ForgotPassword;
import com.nhom20.cimeemappserver.entity.Users;


@Service
public class CustomerPasswordServiceImpl implements CustomerPasswordService{

	@Autowired
	private CustomerPasswordDao customerPasswordDao;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Override
	public void addCodeEmail(CustomerPassword customerPassword) {
		// TODO Auto-generated method stub
		String pass=customerPassword.getPassword();
		if (pass!=null) {
			customerPassword.setPassword(passwordEncoder.encode(pass));
		}
		customerPasswordDao.save(customerPassword);
	}

	@Override
	public CustomerPassword getPassById(String id) {
		// TODO Auto-generated method stub
		Optional<CustomerPassword> result=customerPasswordDao.findById(id);
		CustomerPassword books=null;
		if (result.isPresent()) {
			books=result.get();
		}else {
			throw new RuntimeException("Did not find product id - "+id);
		}
		return books;
	}
	
	@Override
	public CustomerPassword getPassByUserId(String id) {
		
		return customerPasswordDao.getPassByUserId( id);
	}
	
}
