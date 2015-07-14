package com.workfusion.project.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.workfusion.project.dao.MySqlParameter;
import com.workfusion.project.dao.MySqlPropertyManager;

public final class MySqlConnectionManager {

	static Logger LOG = Logger.getLogger(MySqlConnectionManager.class);

	private String driverName;
	private String url;
	private String user;
	private String password;
	private Connection mySqlConnection;

	private MySqlConnectionManager() {
		MySqlPropertyManager propertyManager = MySqlPropertyManager
				.getInstance();
		this.driverName = propertyManager.getValue(MySqlParameter.DB_DRIVER);
		this.url = propertyManager.getValue(MySqlParameter.DB_URL);
		this.user = propertyManager.getValue(MySqlParameter.DB_USER);
		this.password = propertyManager.getValue(MySqlParameter.DB_PASSWORD);
		this.mySqlConnection = createConnection();
	}

	private static class Holder {
		private static final MySqlConnectionManager INSTANCE = new MySqlConnectionManager();
	}

	public static MySqlConnectionManager getInstance() {
		return Holder.INSTANCE;
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			LOG.error("Connection to MySQL DB not created", e);
		}
		return connection;

	}

	public Connection getMySqlConnection() {
		return mySqlConnection;
	}

	public void closeConnection() {
		try {
			mySqlConnection.close();
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

}
