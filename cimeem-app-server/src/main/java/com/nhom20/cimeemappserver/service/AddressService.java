package com.nhom20.cimeemappserver.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.nhom20.cimeemappserver.entity.Address;
import com.nhom20.cimeemappserver.entity.CustomerPassword;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Users;


public interface AddressService {
	public Address getPassById(String id);
}
