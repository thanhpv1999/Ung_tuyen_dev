package com.nhom20.cimeemappserver.authen;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nhom20.cimeemappserver.entity.Users;

import java.util.ArrayList;
import java.util.Collection;




@Getter@Setter
public class UserPrincipal implements UserDetails {
    private String userId;
    private String email;
    private String password;
    private Collection authorities;
    private Users user;

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
