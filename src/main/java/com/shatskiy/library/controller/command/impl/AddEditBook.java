package com.shatskiy.library.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shatskiy.library.controller.command.Command;
import com.shatskiy.library.service.BookService;
import com.shatskiy.library.service.exception.ServiceException;

public class AddEditBook implements Command {
	
	private static final Logger log = LogManager.getRootLogger();
	private BookService bookService;
	
	public AddEditBook() {
		super();
	}

	public AddEditBook(BookService bookService) {
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
		String idBook = parameter[6];
		
		String response = null;
		
		try {
			bookService.addEditBook(title, genre, author, year, quantity, idBook);
			response = "Book successfully edited";
		} catch (ServiceException e) {
			response = "Error editing book";
			log.error("fail in AddEditBook", e);
		}
		return response;
	}
}
