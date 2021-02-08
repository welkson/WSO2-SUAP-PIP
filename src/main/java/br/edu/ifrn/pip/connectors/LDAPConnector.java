package br.edu.ifrn.pip.connectors;

import br.edu.ifrn.pip.TipoAtributo;
import br.edu.ifrn.pip.util.LdapConnection;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LDAPConnector extends AbstractConnector {

    @Override
    public String recuperarValorDeAtributo(TipoAtributo buscaPor) {
        log.info("Chamando conector LDAP...");

        return LdapConnection.buscaUsuarioDepartamento(buscaPor.getSujeito());
    }
}
