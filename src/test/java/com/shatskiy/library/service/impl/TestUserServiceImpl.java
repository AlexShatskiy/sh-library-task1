package com.shatskiy.library.service.impl;

import org.junit.Test;

import com.shatskiy.library.service.UserService;
import com.shatskiy.library.service.exception.ServiceException;

public class TestUserServiceImpl {
/*	Зарегистрировать пользователя не получиться, т.к. мы не инициализировали ConnectionPool
 	Соответственно когда берем Connection получаем NullPointerException.
*/
	@Test (expected = NullPointerException.class)
	public void signUp() throws ServiceException{
		
		UserService userService = new UserServiceImpl();
		userService.signUp("Dylan O'Brien", "12345678");
	}
}
