package com.shatskiy.library.controller;

import com.shatskiy.library.controller.command.Command;
import com.shatskiy.library.controller.command.CommandProvider;

public final class Controller {
	
	private final char PARAM_DELIMETR = ' ';
	private CommandProvider provider;
	
	public Controller() {
		super();
	}

	public Controller(CommandProvider provider) {
		super();
		this.provider = provider;
	}

	public String executeAction(String request){
		String commandName;
		Command command;
		
		commandName = request.substring(0, request.indexOf(PARAM_DELIMETR));
		command = provider.getCommand(commandName);
		String response = command.executeCommand(request);
		
		return response;
	}
}
