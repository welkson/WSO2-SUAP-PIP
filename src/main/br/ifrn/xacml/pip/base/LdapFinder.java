/*
 *  Copyright (c) WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package br.ifrn.xacml.pip.base;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.wso2.carbon.identity.entitlement.pip.AbstractPIPAttributeFinder;

import br.ifrn.xacml.pip.ldap.LdapUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This is sample implementation of PIPAttributeFinder in WSO2 entitlement engine.
 */
public class LdapFinder extends AbstractPIPAttributeFinder {

    private static Log log = LogFactory.getLog(LdapFinder.class);
    
    private static final String LDAP_DEPARTMENT = "http://ifrn.edu.br/ldap/departamento";

	/**
	 * List of attribute finders supported by the this PIP attribute finder
	 */
	private Set<String> supportedAttributes = new HashSet<String>();

    @Override
	public void init(Properties properties)  throws Exception{
    		log.info("<<<<<<<<<<<<<<<<< Starting " + getModuleName() + "... >>>>>>>>>>>>>>>>>");
    		supportedAttributes.add(LDAP_DEPARTMENT);
    }

    @Override
    public String getModuleName() {
        return "LDAP PIP Finder v0.0.3";
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
