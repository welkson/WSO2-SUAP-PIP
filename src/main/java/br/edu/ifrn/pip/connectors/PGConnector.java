package br.edu.ifrn.pip.connectors;

import br.edu.ifrn.pip.AtributosConstantes;
import br.edu.ifrn.pip.TipoAtributo;
import br.edu.ifrn.pip.util.PostgresConnection;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

public class PGConnector extends AbstractConnector {

    @Override
    public String recuperarValorDeAtributo(TipoAtributo atributo) {
        if (StringUtils.isBlank(atributo.getNomeAtributo())) {
            throw new IllegalArgumentException("O parâmetro da busca não foi informado.");
        }
        String atributoId = super.getAtributoId();
        String resultado = StringUtils.EMPTY;

        try {
            if (AtributosConstantes.ATRIB_CENTRALSERV_SOLICITANTE.equalsIgnoreCase(atributoId)) {
                resultado = PostgresConnection.getInstance().buscarPorSolicitante(atributo.getRecurso());
            }
        } catch (SQLException e) {    //TODO: tratar no PostgresConnection
            e.printStackTrace();
        }

        return resultado;
    }
}
