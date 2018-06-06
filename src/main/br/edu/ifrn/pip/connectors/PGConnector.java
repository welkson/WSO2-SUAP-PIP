package br.edu.ifrn.pip.connectors;

import java.sql.SQLException;

import br.edu.ifrn.pip.util.PostgresConnection;

public class PGConnector extends AbstractConnector {

	@Override
	public String recuperarValorDeAtributo(String buscaPor) {
		String atributoId = super.getAtributoId();
		String resultado = "";
						
		try {
			resultado = PostgresConnection.getInstance().buscaCentralservicosDonoticket(buscaPor);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

}
