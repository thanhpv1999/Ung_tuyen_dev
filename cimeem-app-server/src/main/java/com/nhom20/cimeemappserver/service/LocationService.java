package com.nhom20.cimeemappserver.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Users;


public interface LocationService {
	public List<Location> getListLocation();

	public void saveLocAndBook(String location, String movieId);
}
