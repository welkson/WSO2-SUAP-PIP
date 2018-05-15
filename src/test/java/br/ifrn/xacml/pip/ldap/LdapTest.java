package br.ifrn.xacml.pip.ldap;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LdapTest {
	private static String UID_FORMAT_STRING = "uid=%1s,%2s";
	private static String VDS_SERVER = "ldap://ldap.forumsys.com:389";
	private static final String PEOPLE_OU = "cn=read-only-admin,dc=example,dc=com";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println("Connecting ldap...");

		String userName = "cn=read-only-admin,dc=example,dc=com";
		String newPassword = "password";
		String provider = "ldap://ldap.forumsys.com";

		//String userName = "1956951@ifrn.local";
		//String newPassword = "xxx";
		//String provider = "ldap://10.22.0.10:389";
		
		Hashtable authEnv = new Hashtable(11);
		
		authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		authEnv.put(Context.PROVIDER_URL, provider);
		authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		authEnv.put(Context.SECURITY_PRINCIPAL, userName);
		authEnv.put(Context.SECURITY_CREDENTIALS, newPassword);
		
		try {
			DirContext authContext = new InitialDirContext(authEnv);
			System.out.println("Autenticado!");
			
		} catch (AuthenticationException authEx) {
			System.out.println("Erro na autenticação! ");
			authEx.printStackTrace();
			
		} catch (NamingException namEx) {
			System.out.println("Problemas na conexão! ");
		}
	}
}
