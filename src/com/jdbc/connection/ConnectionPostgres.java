package com.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgres {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			String url = "jdbc:postgresql://localhost:5432/contacts";
			String user = "postgres";
			String password = "estagio";

			connection = DriverManager.getConnection(url, user, password);

			System.out.println("Successfully connected!");
		} catch (Exception e) {
			System.out.println("Error during catch block : " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error during finally block : " + e.getMessage());
			}
		}

	}
}
