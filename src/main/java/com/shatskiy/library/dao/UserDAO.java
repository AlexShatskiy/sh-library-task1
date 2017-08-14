package com.shatskiy.library.dao;

import com.shatskiy.library.dao.exception.DAOException;
import com.shatskiy.library.domain.User;

public interface UserDAO {
	
	User signIn(String login, int password) throws DAOException;
	void signUp(String login, int password) throws DAOException;
}
