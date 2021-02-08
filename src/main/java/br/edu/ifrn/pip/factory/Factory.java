/**
 *
 */
package br.edu.ifrn.pip.factory;

import br.edu.ifrn.pip.AtributosConstantes;
import br.edu.ifrn.pip.connectors.AbstractConnector;
import br.edu.ifrn.pip.connectors.Connector;
import br.edu.ifrn.pip.util.ConfigUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Factory {

    /**
     * Método fábrica, responsável por devolver uma instância da classe
     * {@link Factory}. Como esta classe implementa o design pattern singleton, este
     * método sempre entregará a mesma instância.
     *
     * @return uma instância de {@link Factory}.
     */
    public static Factory getInstance() {
        return FactoryHelper.INSTANCE;
    }

    /**
     * Método fábrica, responsável por instanciar objetos do tipo {@link Connector}.
     * A instanciação é realizada através da <i>Java Reflection API</i>, com base
     * nas configurações definidas no arquivo de configurações do PIP.
     *
     * @param umTipoDeConnector um {@link String} que representa o tipo do <i>connector</i>
     *                          desejado.
     * @return um objeto que implementa a interface {@link Connector}.
     * @throws IllegalArgumentException exceção lançada caso ocorra uma das situações abaixo:<br/>
     *                                  <ol>
     *                                  <li>se o tipo do <i>connector</i> solicitado seja inválido, isto
     *                                  é, nulo, vazio ou inexistente nas configurações do PIP;</li>
     *                                  <li>se o tipo do <i>connector</i> solicitado for válido, porém
     *                                  não haja nenhum valor associado à ele nas configurações do
     *                                  PIP.</li>
     *                                  </ol>
     */
    public Connector criarConnector(final String umTipoDeConnector) {
        if (StringUtils.isBlank(umTipoDeConnector)) {
            throw new IllegalArgumentException("O tipo de connector solicitado para criação é desconhecido.");
        }
        // remove domínio e mantém apenas URI
        final String atributoUri = umTipoDeConnector.replace(AtributosConstantes.DOMINIO_BASE, "");

        String classeConcretaConnector = ConfigUtil.getInstance().recuperarValorDeConfiguracao(atributoUri);
        if (StringUtils.isBlank(classeConcretaConnector)) {
            throw new IllegalArgumentException("Classe de connector desconhecida ou inválida.");
        }
        try {
            // retorna uma instância da classe via reflexão
            AbstractConnector conector = (AbstractConnector) Class.forName(classeConcretaConnector).newInstance();
            conector.setAtributoId(umTipoDeConnector);
            return conector;
        } catch (ClassNotFoundException exception) {
            log.error("A classe connector {} não foi encontrada.", classeConcretaConnector, exception);
        } catch (InstantiationException exception) {
            log.error("Não foi possível instanciar a classe connector {}.", classeConcretaConnector, exception);
        } catch (IllegalAccessException exception) {
            log.error("O construtor da classe connector {} não está acessível.", classeConcretaConnector, exception);
        }
        throw new IllegalArgumentException("Connector desconhecido.");
    }

    /**
     * Classe interna utilizada para auxiliar a implementação do singleton para
     * torná-lo thread-safe sem o uso de blocos synchronized.
     *
     * @author Lucas Mariano
     */
    private static class FactoryHelper {
        private static final Factory INSTANCE = new Factory();
    }

}
