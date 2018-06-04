package br.edu.ifrn.pip.connectors;

import br.edu.ifrn.pip.util.LdapUtil;

public class LDAPConnector implements Connector {

	@Override
	public String recuperarValorDeAtributo(String valorAtributo) {
	 	return  LdapUtil.findDepartmentByUser(valorAtributo);
	}
	
}
