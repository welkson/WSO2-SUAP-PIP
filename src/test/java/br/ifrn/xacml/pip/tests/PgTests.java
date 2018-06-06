package br.ifrn.xacml.pip.tests;

import br.edu.ifrn.pip.connectors.Connector;
import br.edu.ifrn.pip.factory.Factory;

public class PgTests {
	public static void main(String[] args) {
		Connector connector = Factory.getInstance().criarConnector("http://ifrn.edu.br/centralservicos/donoticket");

		System.out.println("Dono do Ticket: " + connector.recuperarValorDeAtributo("1"));
	}
}