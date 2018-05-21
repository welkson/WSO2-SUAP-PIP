package br.ifrn.xacml.pip.ldap;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.ifrn.xacml.pip.base.LdapFinder;

public class LdapUtil {
    private static Log log = LogFactory.getLog(LdapFinder.class);

    public static String findDepartmentByUser(String user) {
    		log.info("Conectando ao ldap...");

		// String userName = "cn=read-only-admin,dc=example,dc=com";
		// String newPassword = "password";
		// String provider = "ldap://ldap.forumsys.com";

		String userName = "monitor@ifrn.local";
		String newPassword = "demo";
		String provider = "ldap://10.22.0.10:389";
		String departamento = "";

		Hashtable authEnv = new Hashtable(11);
		authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		authEnv.put(Context.PROVIDER_URL, provider);
		authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		authEnv.put(Context.SECURITY_PRINCIPAL, userName);
		authEnv.put(Context.SECURITY_CREDENTIALS, newPassword);

		try {
			DirContext authContext = new InitialDirContext(authEnv);
			log.info("Autenticação LDAP OK! Buscando por usuário " + user + "...");

			try {

				SearchControls constraints = new SearchControls();
				constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

				String[] attrIDs = { "distinguishedName", "sn", "givenname", "mail", "department" };
				constraints.setReturningAttributes(attrIDs);

				NamingEnumeration answer = authContext.search("OU=IFRN,DC=ifrn,DC=local", "sAMAccountName=" + user, constraints);
				if (answer.hasMore()) {
					Attributes attrs = ((SearchResult) answer.next()).getAttributes();
					departamento = attrs.get("department").get().toString();
					log.info("Usuário encontrado (departamento: " + departamento + ")");
					
				} else {
					log.error("Usuário Inválido");
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} catch (AuthenticationException authEx) {
			log.error("Erro na autenticação! ");
			authEx.printStackTrace();

		} catch (NamingException namEx) {
			log.error("Problemas na conexão! " + namEx.toString());
		}
		
		return departamento;
	}
}
