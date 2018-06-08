package br.edu.ifrn.pip.connectors;

import java.sql.SQLException;

import br.edu.ifrn.pip.AtributosConstantes;
import br.edu.ifrn.pip.util.PostgresConnection;

public class PGConnector extends AbstractConnector {

	@Override
	public String recuperarValorDeAtributo(String buscaPor) {
		String atributoId = super.getAtributoId();
		String resultado = "";
						
		try {
			switch (atributoId) {
			case AtributosConstantes.ATRIB_CENTRALSERV_DONOTICKET:
				resultado = PostgresConnection.getInstance().buscarDonoTicket(buscaPor);
				break;

			default:
				break;
			}
			
			
		} catch (SQLException e) {	//TODO: tratar no PostgresConnection
			e.printStackTrace();
		}
		
		return resultado;
	}

}
