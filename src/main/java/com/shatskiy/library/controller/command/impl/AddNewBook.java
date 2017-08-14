package com.shatskiy.library.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shatskiy.library.controller.command.Command;
import com.shatskiy.library.service.BookService;
import com.shatskiy.library.service.exception.ServiceException;

public class AddNewBook implements Command {
	
	private static final Logger log = LogManager.getRootLogger();
	private BookService bookService;
	
	public AddNewBook() {
		super();
	}

	public AddNewBook(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@Override
	public String executeCommand(String request) {
		String [] parameter = request.split(" ");
		String title = parameter[1];
		String author = parameter[2];
		String genre = parameter[3];
		String year = parameter[4];
		String quantity = parameter[5];
		
		String response = null;
		
		try {
			bookService.addNewBook(title, genre, author, year, quantity);
			response = "Book successfully added";
		} catch (ServiceException e) {
			response = "Error adding book";
			log.error("fail in AddNewBook", e);
		}
		return response;
	}
}
