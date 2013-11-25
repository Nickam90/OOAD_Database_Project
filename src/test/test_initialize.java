package test;

import database.MySQLConnector;
import exceptions.DALException;

public class test_initialize {

	public static void main(String[] args) {
		
		MySQLConnector conn = null;
		try {
			conn = new MySQLConnector();
		} catch (DALException e) {
			System.out.println(e);
//			e.printStackTrace();
		}
		test(conn);

	}
	
	public static void test(MySQLConnector conn){
		
		try {
			conn.doUpdate("source create_all_tables.sql") ;
		} catch (DALException e) {
			System.out.println(e);
//			e.printStackTrace();
		}
	}

}
