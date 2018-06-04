package br.edu.ifrn.pip.connectors;

public class LDAPConnector implements Connector {

	/* (non-Javadoc)
	 * @see br.edu.ifrn.pip.connectors.Connector#recuperarValorDeAtributo()
	 */
	@Override
	public void recuperarValorDeAtributo() {
		// conectar com o AD...
		// consultar...
		// retornar
		System.out.println("Recuperei um atributo no Active Directory!");
	}

}
