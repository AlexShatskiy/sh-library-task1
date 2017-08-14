package com.shatskiy.library.dao;

import java.util.List;

import com.shatskiy.library.dao.exception.DAOException;
import com.shatskiy.library.domain.Book;

public interface BookDAO {
	
	void addNewBook(String title, String authro, String genre, String year, int quantity) throws DAOException;
	void addEditBook(String title, String genre, String author, String year, int quantity, int idBook) throws DAOException;
	List<Book> getBooklist() throws DAOException;
}
