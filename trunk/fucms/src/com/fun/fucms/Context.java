package com.fun.fucms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.sql.DataSource;

import com.fun.fucms.conf.Configuration;

import oracle.jdbc.pool.OracleDataSource;

public class Context {
	
	private static final SimpleDateFormat sSQLDATEFMT = new SimpleDateFormat("dd-MMM-yyyy", new Locale("en_US"));
	private Connection mConnection;
	private Date mDate;
	private Statement mStm;
	private ResultSet mResultSet;
	
	public Context() throws EvilException {
		try {
			initDatabaseConnection();
			mDate = new Date();
			Thread.sleep(10);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new EvilException(e);
		} catch (InterruptedException e) {
		}
	}
	
	public Date getDate() {
		return mDate;
	}
	public String getDateAsString() {
		return sSQLDATEFMT.format(mDate);
	}
	
	public void close() {
		/*if (mConnection != null) {
			try {
				mConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	public Connection getConnection() {
		return mConnection;
	}
	
	public ResultSet executeQuery(String sqlstm) throws SQLException {
		mStm = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	            ResultSet.CONCUR_UPDATABLE);
		mResultSet = mStm.executeQuery(sqlstm);
		return mResultSet;
	}
	
	public void closeLastQuery() {
		try {
			if (mResultSet != null) {
				mResultSet.close();
			}
			if (mStm != null) {
				mStm.close();
			}
		} catch (SQLException e) {
		}

	}
	

	
	
	private void initDatabaseConnection() throws SQLException {
		try {
			//DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			Class.forName(Configuration.getDataSourceDriver());
			mConnection = DriverManager.getConnection(Configuration.getDataSourceUrl(),
					Configuration.getDataSourceUsername(),
					Configuration.getDataSourcePassword());
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
	}

}
