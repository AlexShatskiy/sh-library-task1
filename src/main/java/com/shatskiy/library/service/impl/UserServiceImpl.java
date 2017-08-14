package com.shatskiy.library.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shatskiy.library.dao.UserDAO;
import com.shatskiy.library.dao.exception.DAOException;
import com.shatskiy.library.domain.User;
import com.shatskiy.library.service.UserService;
import com.shatskiy.library.service.exception.ServiceException;
import com.shatskiy.library.service.validation.ValidationData;

public class UserServiceImpl implements UserService {
	
	private static final Logger log = LogManager.getRootLogger();
	private UserDAO userDAO;
	
	public UserServiceImpl() {
		super();
	}

	public UserServiceImpl(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}

	@Override
	public void signIn(String login, String password) throws ServiceException {
		if(!ValidationData.validUser(login, password)){
			throw new ServiceException("Iccorrent user's login or password");
		}	
		//Attention String_paswword convert to int_password(HashCode)
		try {
			User user = userDAO.signIn(login, password.hashCode());
			if(user == null){
				throw new ServiceException("User is not found");
			}
		} catch (DAOException e) {
			log.error("fail in UserServiceImpl", e);
			throw new ServiceException("Error sign in", e);
		}
	}

	@Override
	public void signUp(String login, String password) throws ServiceException {
		if(!ValidationData.validUser(login, password)){
			throw new ServiceException("Icorrent user's login or password");
		}
		//Attention String_paswword convert to int_password(HashCode)
		try {
			userDAO.signUp(login, password.hashCode());
		} catch (DAOException e) {
			log.error("fail in UserServiceImpl", e);
			throw new ServiceException("Error sign up", e);
		}
	}
}
