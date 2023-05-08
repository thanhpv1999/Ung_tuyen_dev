package com.nhom20.cimeemappserver.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.nhom20.cimeemappserver.authen.UserPrincipal;
import com.nhom20.cimeemappserver.entity.Employee;
import com.nhom20.cimeemappserver.entity.Users;


public interface UserService {

	public Users getUsersByGmail(String email);
	public Users getUsersById(String id);

	public void addNewUser(Users users);

	public List<Users> getUsersByGmailAndProvide(String email, String provide, String provide2);

	public void generateOneTimePassword(Users users) throws UnsupportedEncodingException, MessagingException;

	public void sendOTPEmail(Users users, String OTP) throws UnsupportedEncodingException, MessagingException;

	public void clearOTP(Users users);
	public void save(Users theUsers);
	public void updateUser(String firstName,String lastName,String address,String email,String phone,String id);
	public void updateAvaUser(String avatar,String id);
	public void updateActiveUser(String active,String email);
	 UserPrincipal findByUsername(String username);
	 
	 public Page<Users> listUser(Pageable pageable);
	
}
