package com.shatskiy.library.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shatskiy.library.controller.command.Command;
import com.shatskiy.library.service.UserService;
import com.shatskiy.library.service.exception.ServiceException;

public class SignIn implements Command {
	
	private static final Logger log = LogManager.getRootLogger();
	private UserService userService;
	
	public SignIn() {
		super();
	}

	public SignIn(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public String executeCommand(String request) {
		String [] parameter = request.split(" ");
		String login = parameter[1];
		String password = parameter[2];
		
		String response = null;
		
		try {
			userService.signIn(login, password);
			response = "Welcome " + login;
		} catch (ServiceException e) { 
			response = "Sign in error";
			log.error("fail in SignIn", e);
		}	
		return response;
	}
}
