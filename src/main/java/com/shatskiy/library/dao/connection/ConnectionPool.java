package com.shatskiy.library.dao.connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.shatskiy.library.dao.connection.manager.DBParameter;
import com.shatskiy.library.dao.connection.manager.DBResourceManager;
import com.shatskiy.library.dao.exception.ConnectionPoolException;
import com.shatskiy.library.dao.exception.DAOException;

public final class ConnectionPool implements InitializingBean, DisposableBean{	
	
	private static final Logger log = LogManager.getRootLogger();
	private BlockingQueue<Connection> freeConnection;
	private BlockingQueue<Connection> busyConnection;
	
	private int poolsize;
	private String driver;
	private String user;
	private String password;
	private String url;
 	
	public ConnectionPool() {
		
		DBResourceManager resourceManager = DBResourceManager.getInstance();
		this.driver = resourceManager.getValue(DBParameter.DB_DRIVER);
		this.user = resourceManager.getValue(DBParameter.DB_USER);
		this.password = resourceManager.getValue(DBParameter.DB_PASSWORD);
		this.url = resourceManager.getValue(DBParameter.DB_URL);
		
		try{
			this.poolsize = Integer.parseInt(resourceManager.getValue(DBParameter.DB_POOLSIZE));
		}catch (NumberFormatException e) {
			log.error("fail in ConnectionPool", e);
			this.poolsize = 6;
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		freeConnection = new ArrayBlockingQueue<Connection>(poolsize);
		busyConnection = new ArrayBlockingQueue<Connection>(poolsize);
		
		try{
			Class.forName(driver);
			for(int i = 0; i < poolsize; i++){
				freeConnection.add(DriverManager.getConnection(url, user, password));
			}
		}catch (ClassNotFoundException e) {
			log.error("fail in ConnectionPool", e);
			throw new ConnectionPoolException("Can't find database driver class", e);
		} catch (SQLException e) {
			log.error("fail in ConnectionPool", e);
			throw new ConnectionPoolException("SQLException in ConnectionPool", e);
		}
	}
	
	@Override
	public void destroy() throws Exception {
		List<Connection> listConnection = new ArrayList<Connection>();
		listConnection.addAll(this.busyConnection);
		listConnection.addAll(this.freeConnection);
		
		for(Connection connection: listConnection){
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
	}
	
	public Connection take() throws ConnectionPoolException{
		Connection connection = null;
		try {
			connection = freeConnection.take();
			busyConnection.put(connection);
		} catch (InterruptedException e) {
			log.error("fail in ConnectionPool", e);
			throw new ConnectionPoolException("Error connecting to the data source", e);
		}
		return connection;
	}
	
	public void free(Connection connection) throws InterruptedException, DAOException{
		
		if(connection == null){
			throw new DAOException("Connection is null");
		}
		
		Connection tempConnection = connection;
		connection = null;
		busyConnection.remove(tempConnection);
		freeConnection.put(tempConnection);
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt, ResultSet rs){
		if(con != null){
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
		if(preSt != null){
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st){
		if(con != null){
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
	}

	public void closeConnection(Connection con, PreparedStatement preSt){
		if(con != null){
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
		if(preSt != null){
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
	}
	
	public void closeConnection(Connection con, ResultSet rs){
		if(con != null){
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
	}
	
	public void closeConnection(Connection con, Statement st, PreparedStatement preSt){
		if(con != null){
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);;
			}
		}
		
		if(preSt != null){
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
	}

	public void closeConnection(Connection con, PreparedStatement preSt, ResultSet rs){
		if(con != null){
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
		if(preSt != null){
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, ResultSet rs){
		if(con != null){
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
		
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
	}	
}
