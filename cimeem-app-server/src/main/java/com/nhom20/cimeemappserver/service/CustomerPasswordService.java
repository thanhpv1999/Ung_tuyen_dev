package com.nhom20.cimeemappserver.service;

import com.nhom20.cimeemappserver.entity.CustomerPassword;
import com.nhom20.cimeemappserver.entity.ForgotPassword;
import com.nhom20.cimeemappserver.entity.Users;

public interface CustomerPasswordService {
	public void addCodeEmail(CustomerPassword customerPassword);
	public CustomerPassword getPassById(String id);
	public CustomerPassword getPassByUserId(String id);
}
