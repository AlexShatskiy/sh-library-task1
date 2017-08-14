package com.shatskiy.library.dao.connection.manager;

import java.util.ResourceBundle;

public final class DBResourceManager {
	
	private static final String FILE_NAME = "db";
	private static DBResourceManager instance = null;
	private final ResourceBundle bundle = ResourceBundle.getBundle(FILE_NAME);
	
	private DBResourceManager() {}

	public static DBResourceManager getInstance() {
		if(instance == null){
			instance = new DBResourceManager();
		}
		return instance;
	}
	
	public String getValue(String key){
		return bundle.getString(key);
	}
}
