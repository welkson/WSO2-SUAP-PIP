package br.edu.ifrn.pip;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.wso2.carbon.identity.entitlement.pip.AbstractPIPAttributeFinder;
import org.wso2.carbon.identity.entitlement.internal.EntitlementServiceComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.edu.ifrn.pip.connectors.Connector;
import br.edu.ifrn.pip.factory.Factory;

public class SuapAttributeFinder extends AbstractPIPAttributeFinder {

    private static Log log = LogFactory.getLog(SuapAttributeFinder.class);
    
    private static final String LDAP_DEPARTMENT = "http://ifrn.edu.br/ldap/departamento";
    private static final String PG_TICKETOWNER = "http://ifrn.edu.br/pg/ticket_owner";

	private Set<String> supportedAttributes = new HashSet<String>();

    @Override
	public void init(Properties properties)  throws Exception{
    		log.info("<<<<<<<<<<<<<<<<< Iniciando PIP " + getModuleName() + "... >>>>>>>>>>>>>>>>>");
    		log.info(">>>> Registrando atributos [" + LDAP_DEPARTMENT + "," + PG_TICKETOWNER + "]...");
    		supportedAttributes.add(LDAP_DEPARTMENT);
    		supportedAttributes.add(PG_TICKETOWNER);    		
    }

    @Override
    public String getModuleName() {
        return "WSO2-SUAP-PIP v0.0.5";
    }

    @Override
    public Set<String> getAttributeValues(String subjectId, String resourceId, String actionId,
                                          String environmentId, String attributeId, String issuer) throws Exception{
	    	log.info(">>>>>>>>>>>>> subjectId: " + subjectId);
	    	log.info(">>>>>>>>>>>>> resourceId: " + resourceId);
	    	log.info(">>>>>>>>>>>>> actionId: " + actionId);
	    	log.info(">>>>>>>>>>>>> environmentId: " + environmentId);
	    	log.info(">>>>>>>>>>>>> attributeId: " + attributeId);
	    	log.info(">>>>>>>>>>>>> issuer: " + issuer);
	
	    	Set<String> values = new HashSet<String>();
		Connector connector = Factory.getInstance().criarConnector(attributeId);
		values.add(connector.recuperarValorDeAtributo(subjectId));
    		
        return values;
	}
            
    @Override
	public Set<String> getSupportedAttributes() {
		return supportedAttributes;
	}
}
