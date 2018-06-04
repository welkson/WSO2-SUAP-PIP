package br.edu.ifrn.pip;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.wso2.carbon.identity.entitlement.pip.AbstractPIPAttributeFinder;

import br.edu.ifrn.pip.util.LdapUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SuapAttributeFinder extends AbstractPIPAttributeFinder {

    private static Log log = LogFactory.getLog(SuapAttributeFinder.class);
    
    private static final String LDAP_DEPARTMENT = "http://ifrn.edu.br/ldap/departamento";
    private static final String PG_TICKETOWNER = "http://ifrn.edu.br/pg/ticket_owner";

	private Set<String> supportedAttributes = new HashSet<String>();

    @Override
	public void init(Properties properties)  throws Exception{
    		log.info("<<<<<<<<<<<<<<<<< Iniciando PIP " + getModuleName() + "... >>>>>>>>>>>>>>>>>");
    		supportedAttributes.add(LDAP_DEPARTMENT);
    		supportedAttributes.add(PG_TICKETOWNER);
    		
    		log.info("------------------------> Conteúdo do properties: " + properties.toString());
    }

    @Override
    public String getModuleName() {
        return "WSO2-SUAP-PIP v0.0.4";
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
        if(LDAP_DEPARTMENT.equals(attributeId)){
            values.add(findGroup(subjectId));
        }
        return values;
	}
            
    @Override
	public Set<String> getSupportedAttributes() {
		return supportedAttributes;
	}
    
    private String findGroup(String userName){
    		return  LdapUtil.findDepartmentByUser(userName);
    }
}
