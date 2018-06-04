package br.edu.ifrn.pip.connectors;

public class PGConnector implements Connector {

	/* (non-Javadoc)
	 * @see br.edu.ifrn.pip.connectors.Connector#recuperarValorDeAtributo()
	 */
	@Override
	public void recuperarValorDeAtributo() {
		System.out.println("Recuperei um atributo no PostgreSQL!");
	}

}
