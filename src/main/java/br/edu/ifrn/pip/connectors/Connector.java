package br.edu.ifrn.pip.connectors;

import br.edu.ifrn.pip.TipoAtributo;

public interface Connector {						//abstract factory

	String recuperarValorDeAtributo(TipoAtributo buscaPor);

}
