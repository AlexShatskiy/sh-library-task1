package com.shatskiy.library.controller.command.impl;

import com.shatskiy.library.controller.command.Command;

public class WrongRequest implements Command {

	public WrongRequest() {
		super();
	}

	@Override
	public String executeCommand(String request) {
		return "Wrong request!";
	}
}
