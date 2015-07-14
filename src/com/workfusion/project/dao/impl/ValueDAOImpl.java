package com.workfusion.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.workfusion.project.dao.IValueDAO;
import com.workfusion.project.dao.connection.MySqlConnectionManager;
import com.workfusion.project.domain.Value;
import com.workfusion.project.helper.HelperValue;

public class ValueDAOImpl implements IValueDAO {

	static Logger LOG = Logger.getLogger(ValueDAOImpl.class);

	private MySqlConnectionManager sqlConnectionManager = MySqlConnectionManager
			.getInstance();
	private Connection connection = sqlConnectionManager.getMySqlConnection();

	private final String GET_VALUE_BY_ID = "SELECT * FROM value WHERE idValue=";
	private final String UPDATE_VALUE = "UPDATE value SET maxValue1=?, minValue=?, meanValue=?  WHERE idValue = ?";
	private final String INSERT_VALUE = "	INSERT INTO value (idValue,maxValue1,minValue,meanValue) VALUES(?,?,?,?)";

	private ValueDAOImpl() {
		Value value = getValue(1);
		if (value.getIdValue() == 1) {
			value.setMaxValue(0);
			value.setMinValue(HelperValue.getValue());
			value.setMeanValue(0);
			updateValue(value);
		} else {
			value.setIdValue(1);
			value.setMaxValue(0);
			value.setMinValue(HelperValue.getValue());
			value.setMeanValue(0);
			insertValue(value);
		}

	}

	private static class Holder {
		private static final ValueDAOImpl INSTANCE = new ValueDAOImpl();
	}

	public static ValueDAOImpl getInstance() {
		return Holder.INSTANCE;
	}

	@Override
	public Value getValue(int idValue) {
		Value value = new Value();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(GET_VALUE_BY_ID
					+ idValue);
			if (resultSet.next()) {
				value.setIdValue(resultSet.getInt(1));
				value.setMaxValue(resultSet.getLong(2));
				value.setMinValue(resultSet.getLong(3));
				value.setMeanValue(resultSet.getLong(4));
			}

		} catch (SQLException e) {
			LOG.error(e);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}

		return value;
	}

	@Override
	public int updateValue(Value value) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(UPDATE_VALUE);
			preparedStatement.setLong(1, value.getMaxValue());
			preparedStatement.setLong(2, value.getMinValue());
			preparedStatement.setLong(3, value.getMeanValue());
			preparedStatement.setInt(4, value.getIdValue());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
		return result;
	}

	@Override
	public int insertValue(Value value) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(INSERT_VALUE);
			preparedStatement.setInt(1, value.getIdValue());
			preparedStatement.setLong(2, value.getMaxValue());
			preparedStatement.setLong(3, value.getMinValue());
			preparedStatement.setLong(4, value.getMeanValue());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
		return result;
	}

}
