package com.shatskiy.library.service.impl;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.shatskiy.library.dao.exception.ConnectionPoolException;
import com.shatskiy.library.service.BookService;
import com.shatskiy.library.service.exception.ServiceException;


public class TestBookServiceImpl {
	
	static ClassPathXmlApplicationContext context;
	static BookService bookService;
	
	@BeforeClass
	public static void initSource() throws ConnectionPoolException{
		context =  new ClassPathXmlApplicationContext("applicationContext.xml");
		bookService = context.getBean("bookServiceImpl", BookService.class);
	}

	@AfterClass
	public static void destroySource() throws ConnectionPoolException, IOException{
		context.close();
	}
	
	
	@Test  (expected = ServiceException.class)
	public void testAddNewBook() throws ServiceException{
		bookService.addNewBook(null, null, null, null, null);
	}

	@Test
	public void testAddEditBook(){
		try {
			bookService.addEditBook(null, "MyAuthor", "MyGenre", "2017", "10", "1");
		} catch (ServiceException e) {
			Assert.assertEquals("Incorrect data about book", e.getMessage());
		}
	}	
}