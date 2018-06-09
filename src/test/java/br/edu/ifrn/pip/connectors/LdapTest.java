package br.edu.ifrn.pip.connectors;

import br.edu.ifrn.pip.AtributosConstantes;
import br.edu.ifrn.pip.connectors.Connector;
import br.edu.ifrn.pip.factory.Factory;

public class LdapTest {
	
	public static void main(String[] args) {				
		Connector connector = Factory.getInstance().criarConnector(AtributosConstantes.ATRIB_USUARIO_DEPARTAMENTO);
				
		System.out.println("Departamento: " + connector.recuperarValorDeAtributo("1956951"));		
	}
}
