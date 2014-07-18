package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static String user =  "postgres";
	private static String password =  "postgres";
	private static final String url = "jdbc:postgresql://localhost:5432/web_pizza";
	
	
	public static Connection getConnection(){
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.err.println("Falha de Conexao");
			return null;
		}
	}
	
}

