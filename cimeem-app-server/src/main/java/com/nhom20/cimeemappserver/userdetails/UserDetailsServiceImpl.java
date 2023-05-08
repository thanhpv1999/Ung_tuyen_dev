package com.nhom20.cimeemappserver.userdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nhom20.cimeemappserver.dao.UserDao;
import com.nhom20.cimeemappserver.entity.Users;



public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users users=userDao.getUsersByGmail(email);
		if (users==null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new MyUserDetails(users);
	}

}
