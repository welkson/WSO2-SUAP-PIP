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

	public Connector createConnector(String umTipoDeConnector) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		if (inputStream != null) {
			Properties properties = new Properties();
			try {
				properties.load(inputStream);

			} catch (IOException exception) {
				exception.printStackTrace();
			}
			String classeConcretaConnector = properties.getProperty(umTipoDeConnector);
			if (classeConcretaConnector == null || classeConcretaConnector.trim().isEmpty()) {
				throw new IllegalArgumentException("Connector desconhecido.");
			}
			try {
				return (Connector) Class.forName(classeConcretaConnector).newInstance();		//reflexão
				
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
