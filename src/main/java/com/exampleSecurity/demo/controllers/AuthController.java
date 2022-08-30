package com.exampleSecurity.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import com.exampleSecurity.demo.entities.UserModel;
import com.exampleSecurity.demo.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

	@Autowired
	private IUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/register")
	public Map<String, String> register(@RequestBody UserModel userModel) {
		Map<String, String> data = new HashMap<>();
		try{
			var auth = SecurityContextHolder.getContext().getAuthentication();
			String username = userModel.getUsername().trim();

			if(userService.findByUsername(username) != null){
				data.put("mensaje", "username ya esta en uso");
			}else{
				UserModel resultUser = new UserModel();

				resultUser = userService.save(userModel);


				logger.info("Result id:	{}", resultUser.getId());
				logger.info("Result username:	{}", resultUser.getUsername());
				logger.info("Result password:	{}", resultUser.getPassword());
				logger.info("Result role:	{}", resultUser.getRole());

				data.put("mensaje", "registro exitoso");
			}

			logger.info("Datos del Usuario:	{}", auth.getPrincipal());
			logger.info("Datos de los permisos:	{}", auth.getAuthorities());
			logger.info("Esta autenticado:	{}", auth.isAuthenticated());

			return data;


		}catch(Exception ex){
			logger.error("Register error", ex);
		}

		return null;
	}
	
	@GetMapping("/user")
	public Map<String, String> getMensajePublico() {
		Map<String, String> data = new HashMap<>();
		try {
			var auth = SecurityContextHolder.getContext().getAuthentication();

			logger.info("Datos del Usuario:	{}", auth.getPrincipal());
			logger.info("Datos de los permisos:	{}", auth.getAuthorities());
			logger.info("Esta autenticado:	{}", auth.isAuthenticated());

			data.put("mensaje", "respuesta user USER");
		}catch(Exception ex){
			logger.error("/user error", ex);
		}
		return data;
		
	}
	
	@GetMapping("/admin")
	public Map<String, String> getMensajeAdmin() {
		Map<String, String> data = new HashMap<>();
		try {
			var auth = SecurityContextHolder.getContext().getAuthentication();

			logger.info("Datos del Usuario:	{}", auth.getPrincipal());
			logger.info("Datos de los permisos:	{}", auth.getAuthorities());
			logger.info("Esta autenticado:	{}", auth.isAuthenticated());

			data.put("mensaje", "respuesta user ADMIN");
		}catch(Exception ex){
			logger.error("/admin error", ex);
		}
		return data;
		
	}

}
