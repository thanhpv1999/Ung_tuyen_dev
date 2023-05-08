package com.nhom20.cimeemappserver.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.nhom20.cimeemappserver.authen.UserPrincipal;
import com.nhom20.cimeemappserver.dao.UserDao;
import com.nhom20.cimeemappserver.entity.Users;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Users getUsersByGmail(String email) {
		// TODO Auto-generated method stub
		return userDao.getUsersByGmail(email);
	}

	@Override
	public void addNewUser(Users users) {
		// TODO Auto-generated method stub

		userDao.saveAndFlush(users);
	}

	@Override
	public List<Users> getUsersByGmailAndProvide(String email, String provide, String provide2) {
		// TODO Auto-generated method stub
		return userDao.getUsersByGmailAndProvide(email, provide, provide2);
	}

	@Override
	public void generateOneTimePassword(Users users) throws UnsupportedEncodingException, MessagingException {
		String OTP = RandomString.make(8);
		String encodedOTP = passwordEncoder.encode(OTP);

		users.setOneTimePassword(encodedOTP);
		users.setOtpRequestedTime(new Date());

		userDao.save(users);

		sendOTPEmail(users, OTP);

	}

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendOTPEmail(Users users, String OTP) throws UnsupportedEncodingException, MessagingException {

		MimeMessage message = (mailSender).createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("contact@shopme.com", "Book Support");
		helper.setTo(users.getEmail());

		String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";

		String content = "<p>Hello " + users.getFirstName() + "</p>"
				+ "<p>For security reason, you're required to use the following " + "One Time Password to login:</p>"
				+ "<p><b>" + OTP + "</b></p>" + "<br>" + "<p>Note: this OTP is set to expire in 5 minutes.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		(mailSender).send(message);

	}

	@Override
	public void clearOTP(Users users) {
		users.setOneTimePassword(null);
		users.setOtpRequestedTime(null);
		userDao.save(users);

	}

	@Override
	public void save(Users theUsers) {
		// TODO Auto-generated method stub
		userDao.save(theUsers);
	}

	@Override
	public void updateUser(String firstName, String lastName, String address, String email, String phone, String id) {
		// TODO Auto-generated method stub
		userDao.updateUser(firstName, lastName, email, id);
	}

	@Override
	public void updateAvaUser(String avatar, String id) {
		// TODO Auto-generated method stub
		userDao.updateAvaUser(avatar, id);
	}

	@Override
	public void updateActiveUser(String active, String email) {
		// TODO Auto-generated method stub
		userDao.updateActiveUser(active, email);
	}

	@Override
	public Users getUsersById(String id) {
		// TODO Auto-generated method stub
		Optional<Users> result = userDao.findById(id);
		Users books = null;
		if (result.isPresent()) {
			books = result.get();
		} else {
			throw new RuntimeException("Did not find product id - " + id);
		}
		return books;
	}
	 @Override
	    public UserPrincipal findByUsername(String username) {
	        Users user = userDao.getUsersByGmail(username);
	        UserPrincipal userPrincipal = new UserPrincipal();

	        if (null != user) {

	            Set<String> authorities = new HashSet<>();

	            if (null != user.getRoles())

	                user.getRoles().forEach(r -> {
	                authorities.add(r.getRoleId());
	                r.getPermissions().forEach(
	                        p -> authorities.add(p.getPermissionKey()));
	            });

	            userPrincipal.setUserId(user.getUserId());
	            userPrincipal.setEmail(user.getEmail());
	            userPrincipal.setAuthorities(authorities);

	        }

	        return userPrincipal;

	    }

	@Override
	public Page<Users> listUser(Pageable pageable) {
		// TODO Auto-generated method stub
		return userDao.findAll(pageable);
	}

	

}
