package br.ifrn.xacml.pip.tests;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PgTests {
	public static void main(String[] args) {
		System.out.println("Testando conexão com PostgreSQL...");
		
		try {
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC do Postgres não encontrado no classpath.");
			e.printStackTrace();
			return;
		}

		System.out.println("PostgreSQL JDBC Driver Registrado!");
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/suapdev", "postgres",
					"");

		} catch (SQLException e) {
			System.out.println("Falha de conexão. Verifique erros a seguir.");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			Statement st;
			try {
				st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT nome AS nome FROM pessoa LIMIT 1");

				while ( rs.next() )
			      {
			        System.out.println(rs.getString (1));
			      }
			      rs.close();
			      st.close();				

			} catch (SQLException e) {
			      System.err.println(e.getMessage());
			}
			
					
		} else {
			System.out.println("Falha ao conectar com o banco!");
		}
	}		
}