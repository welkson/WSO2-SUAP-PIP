package br.ifrn.xacml.pip.tests;

import java.sql.SQLException;

import br.edu.ifrn.pip.util.PostgresConnection;

public class PgTests {
	public static void main(String[] args) {
		try {
			System.out.println(PostgresConnection.getInstance().buscaCentralservicosDonoticket("200"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}		
}