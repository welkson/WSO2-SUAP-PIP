package br.edu.ifrn.pip.connectors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.ifrn.pip.AtributosConstantes;
import br.edu.ifrn.pip.connectors.Connector;
import br.edu.ifrn.pip.factory.Factory;

public class LdapTest {
	
	public static void main(String[] args) {
		//String url = "http://ifrn.edu.br/centralservicos/chamado/1234/";
		//String v = url.substring(url.indexOf("/") + 5);
		//System.out.println(v);
		
			String url = "http://ifrn.edu.br/centralservicos/chamado/4/";
			String regex = "\\/chamado\\/(.*?)(\\/|$)";

		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(url);
		    matcher.find();
		    System.out.println(matcher.group(1));
		
		//Connector connector = Factory.getInstance().criarConnector(AtributosConstantes.ATRIB_USUARIO_DEPARTAMENTO);
				
		//System.out.println("Departamento: " + connector.recuperarValorDeAtributo("1956951"));
	}
}
