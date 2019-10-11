package br.edu.ifrn.pip.connectors;

import br.edu.ifrn.pip.TipoAtributo;

public interface Connector {

    String recuperarValorDeAtributo(TipoAtributo buscaPor);

}
