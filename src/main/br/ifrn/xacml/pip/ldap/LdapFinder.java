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

package br.ifrn.xacml.pip.ldap;

import org.wso2.carbon.identity.entitlement.pip.AbstractPIPAttributeFinder;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * This is sample implementation of PIPAttributeFinder in WSO2 entitlement engine.
 */
public class LdapFinder extends AbstractPIPAttributeFinder {

    
    private static final String LDAP_GROUP = "http://ifrn.edu.br/ldap/grupo";

	/**
	 * List of attribute finders supported by the this PIP attribute finder
	 */
	private Set<String> supportedAttributes = new HashSet<String>();

    @Override
	public void init(Properties properties)  throws Exception{

        supportedAttributes.add(LDAP_GROUP);
    }

    @Override
    public String getModuleName() {
        return "LDAP PIP Finder";
    }

    @Override
    public Set<String> getAttributeValues(String subjectId, String resourceId, String actionId,
                                          String environmentId, String attributeId, String issuer) throws Exception{

        Set<String> values = new HashSet<String>();
        if(LDAP_GROUP.equals(attributeId)){
            values.add(getValue(subjectId));
        }
        return values;
	}
            
    @Override
	public Set<String> getSupportedAttributes() {
		return supportedAttributes;
	}
    
    public String getValue(String userName){
        if("bob".equalsIgnoreCase(userName)){
            return "50000";
        } else if("alice".equalsIgnoreCase(userName)){
            return "10000";
        } else if("peter".equalsIgnoreCase(userName)){
            return "30000";
        } else {
            return "0";
        }
    }
}
