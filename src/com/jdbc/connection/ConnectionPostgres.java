package com.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgres {

	public static void main(String[] args) {
		Connection connection = null;
		try {

			connection = createConnectionToPostgres();
			System.out.println("Successfully connected!");
		} catch (Exception e) {
			System.out.println("Error during catch block : " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error during finally block : " + e.getMessage());
			}
		}

	}

	public static Connection createConnectionToPostgres() throws Exception {
		String url = "jdbc:postgresql://localhost:5432/contacts";
		String user = "postgres";
		String password = "estagio";

		Connection connection = DriverManager.getConnection(url, user, password);

		return connection;
	}
}
