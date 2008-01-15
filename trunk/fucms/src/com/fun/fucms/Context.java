package com.fun.fucms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fun.fucms.conf.Configuration;
import com.fun.fucms.gui.MainFrame;

public class Context {
	
	private static final SimpleDateFormat sSQLDATEFMT = new SimpleDateFormat("dd-MMM-yyyy", new Locale("en_US"));
	private static Context sInstance;
	
	private Connection mConnection;
	private Date mDate;
	private Statement mStm;
	private ResultSet mResultSet;
	
	public static Context getInstance() throws EvilException {
		if (sInstance == null) {
			sInstance = new Context();
		}
		return sInstance;
	}
	
	public static String getDateAsString(Date date) {
		return sSQLDATEFMT.format(date);
	}
	
	private Context() throws EvilException {
		try {
			initDatabaseConnection();
			mDate = new Date();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new EvilException(e);
		}
	}
	
	public void start() {
		try {
			initDatabaseConnection();
			mDate = new Date();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Date getDate() {
		return mDate;
	}
	public String getDateAsString() {
		return sSQLDATEFMT.format(mDate);
	}
	
	public void close() {
		if (mConnection != null) {
			try {
				mConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection() {
		return mConnection;
	}
	
	public ResultSet executeQuery(String sqlstm) throws SQLException {
		mStm = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	            ResultSet.CONCUR_UPDATABLE);
		MainFrame.log("executing: " + sqlstm);
		mResultSet = mStm.executeQuery(sqlstm);
		return mResultSet;
	}
	
	public int executeUpdate(String sqlstm) throws SQLException {
		mStm = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	            ResultSet.CONCUR_UPDATABLE);
		MainFrame.log("executing (update): " + sqlstm);
		int rs = mStm.executeUpdate(sqlstm);
		return rs;
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
			mConnection.setAutoCommit(true);
			MainFrame.log("connected to " +Configuration.getDataSourceUrl());
		} catch (ClassNotFoundException e) {
			MainFrame.log(e.getMessage());
			throw new SQLException(e.getMessage());
		}
	}

}
