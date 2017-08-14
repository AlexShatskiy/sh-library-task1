package com.shatskiy.library.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shatskiy.library.controller.command.Command;
import com.shatskiy.library.service.UserService;
import com.shatskiy.library.service.exception.ServiceException;

public class SignUp implements Command {
	
	private static final Logger log = LogManager.getRootLogger();
	private UserService userService;
	
	public SignUp() {
		super();
	}

	public SignUp(UserService userService) {
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
			userService.signUp(login, password);
			response = "User was registrated " + login;
		} catch (ServiceException e) { 
			response = "Sign up error";
			log.error("fail in SignUp", e);
		}	
		return response;
	}
}
