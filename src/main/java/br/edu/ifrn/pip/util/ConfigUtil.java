/**
 *
 */
package br.edu.ifrn.pip.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.edu.ifrn.pip.SuapAttributeFinder;

/**
 * Classe utilitária responsável por fornecer um ponto de acesso às
 * configurações do PIP.
 *
 * @author Lucas Mariano
 *
 */
public class ConfigUtil {

	private static Log log = LogFactory.getLog(SuapAttributeFinder.class);

	/**
	 * Nome do arquivo de configurações utilizado. Este arquivo deve estar
	 * localizado dentro do projeto.
	 */
	private static final String ARQUIVO_CONFIGURACOES_PIP = "wso2-pip-suap.properties";

	/**
	 * Classe interna utilizada para auxiliar a implementação do singleton para
	 * torná-lo thread-safe sem o uso de blocos synchronized.
	 *
	 * @author Lucas Mariano
	 *
	 */
	private static class ConfigUtilHelper {
		private static final ConfigUtil INSTANCE = new ConfigUtil();
	}

	/**
	 * {@link Properties} responsável por armazenar as configurações do PIP.
	 */
	private static final Properties CONFIG_PROPERTIES = new Properties();

	/**
	 * Construtor privado utilizado para carregar as configurações do arquivo uma
	 * única vez.
	 */
	private ConfigUtil() {
		log.info("Carregando o arquivo de configurações do PIP.");
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(ARQUIVO_CONFIGURACOES_PIP);
		if (inputStream != null) {
			try {
				CONFIG_PROPERTIES.load(inputStream);
			} catch (IOException exception) {
				log.error("Ocorreu um erro ao carregar o arquivo de configurações.", exception);
			}
		} else {
			log.error("O arquivo de configurações '" + ARQUIVO_CONFIGURACOES_PIP + "' não foi encontrado.");
		}
	}

	/**
	 * Método fábrica, responsável por devolver uma instância da classe
	 * {@link ConfigUtil}. Como esta classe implementa o design pattern singleton,
	 * este método sempre entregará a mesma instância.
	 *
	 * @return uma instância de {@link ConfigUtil}.
	 */
	public static ConfigUtil getInstance() {
		return ConfigUtilHelper.INSTANCE;
	}

	/**
	 * Método reponsável por recuperar o valor de uma determinada configuração.
	 *
	 * @param umIdentificadorDeConfiguracao
	 *            um {@link String} que representa uma chave de configuração
	 *            definida no arquivo de configuração.
	 * @return um {@link String} que representa o valor associado à chave informada,
	 *         no arquivo de configurações. Caso a chave informada não exista,
	 * @throws IllegalArgumentException
	 *             caso a configuração solicitada não exista ou ela não possua
	 *             nenhum valor.
	 */
	public String recuperarValorDeConfiguracao(final String umIdentificadorDeConfiguracao) {
		if (StringUtils.isBlank(umIdentificadorDeConfiguracao)
				|| !CONFIG_PROPERTIES.containsKey(umIdentificadorDeConfiguracao)) {
			throw new IllegalArgumentException("Identificador de configuração inválido.");
		}
		String valorConfiguracao = CONFIG_PROPERTIES.getProperty(umIdentificadorDeConfiguracao);
		return StringUtils.deleteWhitespace(valorConfiguracao);
	}

}
