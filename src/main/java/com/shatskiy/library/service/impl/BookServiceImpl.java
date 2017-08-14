package com.shatskiy.library.service.impl;

import java.util.IllegalFormatException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shatskiy.library.dao.BookDAO;
import com.shatskiy.library.dao.exception.DAOException;
import com.shatskiy.library.domain.Book;
import com.shatskiy.library.service.BookService;
import com.shatskiy.library.service.exception.ServiceException;
import com.shatskiy.library.service.validation.ValidationData;

public class BookServiceImpl implements BookService {
	
	private static final Logger log = LogManager.getRootLogger();
	private BookDAO bookDAO;

	public BookServiceImpl() {
		super();
	}
	
	public BookServiceImpl(BookDAO bookDAO) {
		super();
		this.bookDAO = bookDAO;
	}

	@Override
	public void addNewBook(String title, String genre, String author, String year, String quantityStr)
			throws ServiceException {
		if (!ValidationData.validBook(title, genre, author, year, quantityStr)) {
			throw new ServiceException("Incorrect data about book");
		}

		int quantity = 0;
		try {
			quantity = Integer.parseInt(quantityStr);
		} catch (IllegalFormatException e) {
			log.error("fail in BookServiceImpl", e);
			throw new ServiceException("Year format exception");
		}
		try {
			bookDAO.addNewBook(title, author, genre, year, quantity);
		} catch (DAOException e) {
			log.error("fail in BookServiceImpl", e);
			throw new ServiceException("Error adding a book to the library");
		}

	}

	@Override
	public void addEditBook(String title, String genre, String author, String year, String quantityStr,
			String idBookStr) throws ServiceException {
		if (!ValidationData.validBook(title, genre, author, year, quantityStr, idBookStr)) {
			throw new ServiceException("Incorrect data about book");
		}

		int idBook = Integer.parseInt(idBookStr);
		int quantity = Integer.parseInt(quantityStr);
		
		try {
			bookDAO.addEditBook(title, genre, author, year, quantity, idBook);
		} catch (DAOException e) {
			log.error("fail in BookServiceImpl", e);
			throw new ServiceException("Error editing book");
		}
	}

	@Override
	public List<Book> getBooklist() throws ServiceException {

		List<Book> booklist = null;

		try {
			booklist = bookDAO.getBooklist();
		} catch (DAOException e) {
			log.error("fail in BookServiceImpl", e);
			throw new ServiceException(e);
		}

		if (booklist == null) {
			throw new ServiceException("Booklist not found");
		}
		return booklist;
	}
}
