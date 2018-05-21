package br.ifrn.xacml.pip.tests;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import java.util.Hashtable;

public class LdapTest {
	private static String UID_FORMAT_STRING = "uid=%1s,%2s";
	private static String VDS_SERVER = "ldap://ldap.forumsys.com:389";
	private static final String PEOPLE_OU = "cn=read-only-admin,dc=example,dc=com";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println("Connecting ldap...");

//		String userName = "cn=read-only-admin,dc=example,dc=com";
//		String newPassword = "password";
//		String provider = "ldap://ldap.forumsys.com";

		String userName = "monitor@ifrn.local";
		String newPassword = "dgti.2010";
		String provider = "ldap://10.22.0.10:389";
		
		Hashtable authEnv = new Hashtable(11);
		
		authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		authEnv.put(Context.PROVIDER_URL, provider);
		authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		authEnv.put(Context.SECURITY_PRINCIPAL, userName);
		authEnv.put(Context.SECURITY_CREDENTIALS, newPassword);
		
		try {
			DirContext authContext = new InitialDirContext(authEnv);
			System.out.println("Autenticado!");

			try {
				
	            SearchControls constraints = new SearchControls();
	            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
	            
	            String[] attrIDs = { "distinguishedName",
	                    "sn",
	                    "givenname",
	                    "mail",
	                    "department"};
	            constraints.setReturningAttributes(attrIDs); 
	            
				NamingEnumeration answer = authContext.search("OU=IFRN,DC=ifrn,DC=local", "sAMAccountName=" + "1956951", constraints);
		            if (answer.hasMore()) {
		                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
		                System.out.println("distinguishedName "+ attrs.get("distinguishedName"));
		                System.out.println("givenname "+ attrs.get("givenname"));
		                System.out.println("sn "+ attrs.get("sn"));
		                System.out.println("mail "+ attrs.get("mail"));
		                System.out.println("department "+ attrs.get("department").get());
		            }
		            else{
		                throw new Exception("Invalid User");
		            }
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} catch (AuthenticationException authEx) {
			System.out.println("Erro na autenticação! ");
			authEx.printStackTrace();
			
		} catch (NamingException namEx) {
			System.out.println("Problemas na conexão! " + namEx.toString());
		}
	}
}
