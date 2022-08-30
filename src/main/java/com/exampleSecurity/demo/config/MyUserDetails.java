package com.exampleSecurity.demo.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exampleSecurity.demo.entities.UserModel;

public class MyUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserModel userModel;

	public MyUserDetails(UserModel userModel) {
		super();
		this.userModel = userModel;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		GrantedAuthority authority = new SimpleGrantedAuthority(userModel.getRole());
		return List.of(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userModel.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userModel.getUsername();
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
		return true;
	}

}
