package com.shatskiy.library.controller.command.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shatskiy.library.controller.command.Command;
import com.shatskiy.library.domain.Book;
import com.shatskiy.library.service.BookService;
import com.shatskiy.library.service.exception.ServiceException;

public class GetBookList implements Command {
	
	private static final Logger log = LogManager.getRootLogger();
	private BookService bookService;
	
	public GetBookList() {
		super();
	}

	public GetBookList(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@Override
	public String executeCommand(String request) {
		
		List<Book> booklist = null;
		String response = null;
		try {
			booklist = bookService.getBooklist();
			response = "";

			for(Book book: booklist){
				response = response + book.toString()+ "\n";
			}
		} catch (ServiceException e) {
			response = "Error getting list of books";
			log.error("fail in GetBookList", e);
		}
		return response;
	}
}
