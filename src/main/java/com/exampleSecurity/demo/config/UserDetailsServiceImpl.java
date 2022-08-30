package com.exampleSecurity.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exampleSecurity.demo.controllers.AuthController;
import com.exampleSecurity.demo.entities.UserModel;
import com.exampleSecurity.demo.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserModel userModel = userRepository.findByUsername(username);
		
		if(userModel == null) {
			throw new UsernameNotFoundException("usuario no encontrado");
		}
		MyUserDetails ud = new MyUserDetails(userModel);
		logger.info("Datos del user details (roles):	{}", ud.getAuthorities());
		logger.info("Datos del user details (username):	{}", ud.getUsername());
		return ud;
		
	}

}
