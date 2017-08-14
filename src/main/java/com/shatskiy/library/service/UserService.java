package com.shatskiy.library.service;

import com.shatskiy.library.service.exception.ServiceException;

public interface UserService {
	
	void signIn(String login, String password) throws ServiceException;
	void signUp(String login, String password) throws ServiceException;
}
