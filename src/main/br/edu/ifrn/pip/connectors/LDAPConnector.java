package br.edu.ifrn.pip.connectors;

import br.edu.ifrn.pip.util.LdapUtil;

public class LDAPConnector implements Connector {

	@Override
	public String recuperarValorDeAtributo(String buscaPor) {
		System.out.println("Chamando conector LDAP...");
	 	return  LdapUtil.buscaDepartamentoPorUsuario(buscaPor);
	}
	
}
