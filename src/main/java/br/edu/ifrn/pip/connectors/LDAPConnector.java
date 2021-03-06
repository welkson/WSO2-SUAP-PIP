package br.edu.ifrn.pip.connectors;

import br.edu.ifrn.pip.TipoAtributo;
import br.edu.ifrn.pip.util.LdapConnection;

public class LDAPConnector extends AbstractConnector {

	@Override
	public String recuperarValorDeAtributo(TipoAtributo buscaPor) {
		System.out.println("Chamando conector LDAP...");
				
		return LdapConnection.buscaUsuarioDepartamento(buscaPor.getSujeito());
	}	
}
