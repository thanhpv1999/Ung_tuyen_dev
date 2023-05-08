package com.nhom20.cimeemappserver.userdetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nhom20.cimeemappserver.entity.Roles;
import com.nhom20.cimeemappserver.entity.Users;


public class MyUserDetails implements UserDetails {

	private Users user;

	public MyUserDetails(Users user) {
		this.user = user;
	}
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<Roles> roles = (Set<Roles>) user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Roles roles2 : roles) {
			authorities.add(new SimpleGrantedAuthority(roles2.getTitle()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		if (user.isOTPRequired()) {
			return user.getOneTimePassword();
		}

		return user.getCustomerPasswords().get(0).getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isActive();
	}

	public String getFullName() {
		return user.getFirstName() + " " + user.getLastName();
	}
}
