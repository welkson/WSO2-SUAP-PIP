package br.edu.ifrn.pip.connectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.edu.ifrn.pip.AtributosConstantes;
import br.edu.ifrn.pip.TipoAtributo;
import br.edu.ifrn.pip.factory.Factory;

public class TestPostgres {

	@Test
	public void testAtributoSolicitante() {
		TipoAtributo atributo = new TipoAtributo();
		atributo.setNomeAtributo("solicitante");
		atributo.setAcao("GET");
		atributo.setSujeito("1956951");
		atributo.setRecurso("http://ifrn.edu.br/centralservicos/chamado/1/");
		
		Connector connector = Factory.getInstance().criarConnector(AtributosConstantes.ATRIB_CENTRALSERV_SOLICITANTE);
		String valorAtributo = connector.recuperarValorDeAtributo(atributo);
		assertEquals("O resultado não foi esperado", "01199881430", valorAtributo);
	}

//	@Test(expected=IllegalArgumentException.class)
//	public void testAtributoVazio() {
//		TipoAtributo atributo = new TipoAtributo();
//		atributo.setNomeAtributo("solicitante");
//		atributo.setAcao("GET");
//		atributo.setSujeito("1956951");
//		atributo.setRecurso("/centralservicos/chamado/1/");
//
//		Connector connector = Factory.getInstance().criarConnector(AtributosConstantes.ATRIB_CENTRALSERV_SOLICITANTE);
//		connector.recuperarValorDeAtributo("");
//		fail("Deveria ter lançado exceção IllegalArgumentException");
//	}
}
