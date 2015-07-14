package com.workfusion.project.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MySqlPropertyManager {

	static Logger LOG = Logger.getLogger(MySqlPropertyManager.class);

	private final String PATH = "db.properties";
	private final String PREFIX = this.getClass().getResource("/").getPath();

	private Properties properties = new Properties();
	private BufferedReader reader = null;
	private File file = new File(PREFIX + PATH);

	private static class Holder {
		private static final MySqlPropertyManager INSTANCE = new MySqlPropertyManager();
	}

	public static MySqlPropertyManager getInstance() {
		return Holder.INSTANCE;
	}

	private MySqlPropertyManager() {
		try {
			reader = new BufferedReader(new FileReader(file));
			properties.load(reader);
		} catch (FileNotFoundException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}

}