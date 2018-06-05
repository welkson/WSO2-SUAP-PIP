package br.ifrn.xacml.pip.tests;

import br.edu.ifrn.pip.connectors.Connector;
import br.edu.ifrn.pip.factory.Factory;

public class LdapTest {
	
	public static void main(String[] args) {				
		Connector connector = Factory.getInstance().criarConnector("http://ifrn.edu.br/ldap/departamento");
				
		System.out.println("Departamento: " + connector.recuperarValorDeAtributo("1956951"));		
	}
}
