package br.edu.ifrn.pip;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.entitlement.pip.AbstractPIPAttributeFinder;

import br.edu.ifrn.pip.connectors.Connector;
import br.edu.ifrn.pip.factory.Factory;
import br.edu.ifrn.pip.AtributosConstantes;

public class SuapAttributeFinder extends AbstractPIPAttributeFinder {

	private static Log log = LogFactory.getLog(SuapAttributeFinder.class);

	private Set<String> supportedAttributes = new HashSet<String>();

	@Override
	public void init(Properties properties) throws Exception {
		log.info("<<<<<<<<<<<<<<<<< Iniciando PIP [" + getModuleName() + "] (build 1.3)... >>>>>>>>>>>>>>>>>");

		log.info(">>>> Registrando atributos...");
		supportedAttributes.add(AtributosConstantes.ATRIB_USUARIO_DEPARTAMENTO);
		supportedAttributes.add(AtributosConstantes.ATRIB_CENTRALSERV_SOLICITANTE);
		supportedAttributes.add(AtributosConstantes.ATRIB_CENTRALSERV_ATENDENTE);
		//imprimirAmbienteExecucao();
	}

	@Override
	public String getModuleName() {
		return "WSO2-SUAP-PIP";
	}

	private void imprimirAmbienteExecucao() {
		SuapAttributeFinder.log.info("------------ PROPRIEDADES ------------");
		Properties properties = System.getProperties();
		for (Object key : properties.keySet()) {
			SuapAttributeFinder.log.info(key + "=" + properties.getProperty(key.toString()));
		}
		SuapAttributeFinder.log.info("\n------------ ENVIRONMENT ------------");
		Map<String, String> mapa = System.getenv();
		for (Entry<String, String> entrada : mapa.entrySet()) {
			SuapAttributeFinder.log.info(entrada.getKey() + "=" + entrada.getValue());
		}
	}

	@Override
	public Set<String> getAttributeValues(String subjectId, String resourceId, String actionId, String environmentId,
			String attributeId, String issuer) throws Exception {
		// popula valores do atributo em um objeto
		TipoAtributo atributo = new TipoAtributo();
		atributo.setNomeAtributo(attributeId);
		atributo.setSujeito(subjectId);
		atributo.setAcao(actionId);
		atributo.setRecurso(resourceId);
		atributo.setAmbiente(environmentId);
		atributo.setEmissor(issuer);

		log.info(">>>>>>>>>>>>> subjectId: " + atributo.getSujeito());
		log.info(">>>>>>>>>>>>> resourceId: " + atributo.getRecurso());
		log.info(">>>>>>>>>>>>> actionId: " + atributo.getAcao());
		log.info(">>>>>>>>>>>>> environmentId: " + atributo.getAmbiente());
		log.info(">>>>>>>>>>>>> attributeId: " + atributo.getNomeAtributo());
		log.info(">>>>>>>>>>>>> issuer: " + atributo.getEmissor());

		Set<String> values = new HashSet<String>();
		Connector connector = Factory.getInstance().criarConnector(attributeId);
		values.add(connector.recuperarValorDeAtributo(atributo)); // TODO: criar objeto e popular todo os 4 atriutos
																	// (subjectId, resource, etc.)

		return values;
	}

	@Override
	public Set<String> getSupportedAttributes() {
		return supportedAttributes;
	}
}
