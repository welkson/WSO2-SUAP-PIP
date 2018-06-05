package br.edu.ifrn.pip.connectors;

import java.lang.reflect.Method;

import br.edu.ifrn.pip.util.LdapConnection;

public class LDAPConnector implements Connector {

	@Override
	public String recuperarValorDeAtributo(String buscaPor) {
		System.out.println("Chamando conector LDAP...");
				
		return LdapConnection.buscaUsuarioDepartamento(buscaPor);
	}
	
	//TODO: chamar método dinamicamente via reflexão
	//Method metodoBusca = LdapUtil.class.getMethod("busca" + "Usuario" + "Departamento", String.class);
 	//return metodoBusca.invoke(LdapUtil.getInstance(), metodoBusca); <--- problema tá no LdapUtil.getInstance() - não existe
	//ref: https://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
}
