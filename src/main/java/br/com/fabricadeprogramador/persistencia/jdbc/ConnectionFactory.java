package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private final static String url = "jdbc:postgresql://localhost:5432/fabricaweb"; 
	private final static String user = "postgres"; 
	private final static String password = "root"; 
	
	public static Connection getConnection(){
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(url,user,password );
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
