package com.nhom20.cimeemappserver.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazonaws.services.identitymanagement.model.User;
import com.nhom20.cimeemappserver.dao.AddressDao;
import com.nhom20.cimeemappserver.dao.LocationDao;
import com.nhom20.cimeemappserver.dao.MemberDao;
import com.nhom20.cimeemappserver.dao.MemberUsersDao;
import com.nhom20.cimeemappserver.dao.UserDao;
import com.nhom20.cimeemappserver.entity.Address;
import com.nhom20.cimeemappserver.entity.CustomerPassword;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Member;
import com.nhom20.cimeemappserver.entity.MemberUser;
import com.nhom20.cimeemappserver.entity.Users;


@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;

	@Override
	public void addMember(Member memberUser) {
		// TODO Auto-generated method stub
		memberDao.save(memberUser);
	}

	@Override
	public Member getMemberById(String groupId) {
		// TODO Auto-generated method stub
		Optional<Member> result = memberDao.findById(groupId);
		Member books = null;
		if (result.isPresent()) {
			books = result.get();
		} else {
			throw new RuntimeException("Did not find product id - " + groupId);
		}
		return books;
	}

	
}
