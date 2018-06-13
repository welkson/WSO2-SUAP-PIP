package br.edu.ifrn.pip.connectors;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import br.edu.ifrn.pip.AtributosConstantes;
import br.edu.ifrn.pip.TipoAtributo;
import br.edu.ifrn.pip.util.PostgresConnection;

public class PGConnector extends AbstractConnector {

	@Override
	public String recuperarValorDeAtributo(TipoAtributo atributo) {
		if (StringUtils.isBlank(atributo.getNomeAtributo())) {
			throw new IllegalArgumentException("O parâmetro da busca não foi informado.");
		}
		String atributoId = super.getAtributoId();
		String resultado = "";

		try {
			switch (atributoId) {
			case AtributosConstantes.ATRIB_CENTRALSERV_SOLICITANTE:
				resultado = PostgresConnection.getInstance().buscarPorSolicitante(atributo.getRecurso());
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
