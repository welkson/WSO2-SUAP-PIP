/**
 *
 */
package br.edu.ifrn.pip.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.edu.ifrn.pip.connectors.Connector;


public class Factory {

	private static Factory instance = null;

	private Factory() {
	}

	public static Factory getInstance() {
		if (Factory.instance == null) {				//Singleton
			Factory.instance = new Factory();
		}
		return Factory.instance;
	}

	public Connector criarConnector(String umTipoDeConnector) {
		//remove domínio e mantém apenas URI
		umTipoDeConnector = umTipoDeConnector.replace("http://ifrn.edu.br/", "");	

		//carrega configuração via properties
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wso2-pip-suap.properties");
		System.out.println(inputStream);
		if (inputStream != null) {
			Properties properties = new Properties();
			try {
				properties.load(inputStream);

			} catch (IOException exception) {
				exception.printStackTrace();
			}
			
			System.out.println("AttributeId=" + umTipoDeConnector);
			String classeConcretaConnector = properties.getProperty(umTipoDeConnector);
			if (classeConcretaConnector == null || classeConcretaConnector.trim().isEmpty()) {
				throw new IllegalArgumentException("Connector desconhecido.");
			}
			try {
				//retorna uma instância da classe via reflexão
				return (Connector) Class.forName(classeConcretaConnector).newInstance();
				
			} catch (ClassNotFoundException exception) {
				exception.printStackTrace();
			} catch (InstantiationException exception) {
				exception.printStackTrace();
			} catch (IllegalAccessException exception) {
				exception.printStackTrace();
			}
		}
		throw new IllegalArgumentException("Connector desconhecido.");
	}

}
