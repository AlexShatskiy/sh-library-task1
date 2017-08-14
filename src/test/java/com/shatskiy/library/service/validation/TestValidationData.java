package com.shatskiy.library.service.validation;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class TestValidationData {

	/*
	 * YearStr must be number
	 */
	@Test
	public void testValidBook(){
		boolean result = ValidationData.validBook("BookTitle", "MyGenre", "MyAuthor", "yearStr", "10");
		assertFalse(result);
	}
	
	@Test
	public void testValidUser(){
		boolean result = ValidationData.validUser(null, null);
		assertFalse(result);
	}
}
