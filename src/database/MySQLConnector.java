package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.DALException;

public class MySQLConnector {
	
	private Connection conn = null;
	private Statement stm = null;
	private String server = "sql-lab1.cc.dtu.dk";	// Server IP
	private int port = 3306;
	private String database = "s123074";	// Database name
	private String username = "s123074";	// Database username
	private String password = "iL2R3Txn";	// Database password
	private String url = "jdbc:mysql://" + server + ":" + port + "/" + database;
	
	

	public MySQLConnector() throws DALException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
			stm = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Connection problems");
		}

	}

	public ResultSet doQuery(String query) throws DALException {
		ResultSet rs = null;
		try {
			rs = stm.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("SQL Exception " + e.getMessage());
		}
		return rs;

	}

	public int doUpdate(String cmd) throws DALException {
		System.out.println(cmd);
		int result;
		try {
			result = stm.executeUpdate(cmd);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Cant execute your command "
					+ e.getMessage());
		}
		return result;
		
	}

	public void closeConnection() throws DALException {
		try {
			conn.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Cant close connection " + e.getMessage());
		}

	}

}


