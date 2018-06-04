package br.edu.ifrn.pip.connectors;

public class PGConnector implements Connector {

	@Override
	public String recuperarValorDeAtributo(String valorAtributo) {
		return "Recuperei um atributo no PostgreSQL!";
	}

}
