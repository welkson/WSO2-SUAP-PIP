package br.edu.ifrn.pip;

import br.edu.ifrn.pip.connectors.Connector;
import br.edu.ifrn.pip.factory.Factory;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.wso2.carbon.identity.entitlement.pip.AbstractPIPAttributeFinder;

@Slf4j
public class SuapAttributeFinder extends AbstractPIPAttributeFinder {

    private Set<String> supportedAttributes = new HashSet<>();

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
        log.info("------------ PROPRIEDADES ------------");
        Properties properties = System.getProperties();
        for (Object key : properties.keySet()) {
            log.info(key + "=" + properties.getProperty(key.toString()));
        }
        log.info("\n------------ ENVIRONMENT ------------");
        Map<String, String> mapa = System.getenv();
        for (Entry<String, String> entrada : mapa.entrySet()) {
            log.info(entrada.getKey() + "=" + entrada.getValue());
        }
    }

    @Override
    public Set<String> getAttributeValues(String subjectId, String resourceId, String actionId, String environmentId,
                                          String attributeId, String issuer) {
        // popula valores do atributo em um objeto
        TipoAtributo atributo = TipoAtributo.builder()
                .nomeAtributo(attributeId)
                .sujeito(subjectId)
                .acao(actionId)
                .recurso(resourceId)
                .ambiente(environmentId)
                .emissor(issuer).build();

        log.info(">>>>>>>>>>>>> subjectId: " + atributo.getSujeito());
        log.info(">>>>>>>>>>>>> resourceId: " + atributo.getRecurso());
        log.info(">>>>>>>>>>>>> actionId: " + atributo.getAcao());
        log.info(">>>>>>>>>>>>> environmentId: " + atributo.getAmbiente());
        log.info(">>>>>>>>>>>>> attributeId: " + atributo.getNomeAtributo());
        log.info(">>>>>>>>>>>>> issuer: " + atributo.getEmissor());

        Set<String> values = new HashSet<>();
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
