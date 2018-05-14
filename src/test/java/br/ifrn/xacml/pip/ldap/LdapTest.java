package br.ifrn.xacml.pip.ldap;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LdapTest {
    private static String UID_FORMAT_STRING = "uid=%1s,%2s";
    private static String VDS_SERVER = "LDAPS://localhost:389";
    private static final String PEOPLE_OU = "ou=people,dc=local,dc=com";
    
	public static void main(String[] args) {
		System.out.println("Teste2");
        Hashtable<String, String> authEnv = new Hashtable<String, String>();
        authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        authEnv.put(Context.PROVIDER_URL, VDS_SERVER);
        authEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); //default authentication
        authEnv.put(Context.SECURITY_PRINCIPAL, String.format(UID_FORMAT_STRING, "uidName", PEOPLE_OU));
        authEnv.put(Context.SECURITY_CREDENTIALS, "password");
        
        try {
            DirContext ctx = new InitialDirContext(authEnv);
            ctx.close();
            System.out.println("User Authentication Successful");
            
        } catch (Exception e) {
            System.out.println("User Authentication Failed");
            e.printStackTrace();
        }		
	}
}
