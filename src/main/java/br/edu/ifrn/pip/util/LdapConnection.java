package br.edu.ifrn.pip.util;

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

import br.edu.ifrn.pip.SuapAttributeFinder;

public class LdapConnection {
	private static Log log = LogFactory.getLog(SuapAttributeFinder.class);
	private static LdapConnection instance;
	private DirContext connection;

	private LdapConnection() {
		log.info("Conectando ao ldap...");
		ConfigUtil config = ConfigUtil.getInstance();
		Hashtable<String, String> authEnv = new Hashtable<String, String>(11);
		authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		authEnv.put(Context.PROVIDER_URL, config.recuperarValorDeConfiguracao("ldap.servidor"));
		authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		authEnv.put(Context.SECURITY_PRINCIPAL, config.recuperarValorDeConfiguracao("ldap.usuario"));
		authEnv.put(Context.SECURITY_CREDENTIALS, config.recuperarValorDeConfiguracao("ldap.senha"));

		try {
			this.connection = new InitialDirContext(authEnv);

		} catch (AuthenticationException authEx) {
			log.error("Erro na autenticação! ");
			authEx.printStackTrace();

		} catch (NamingException namEx) {
			log.error("Problemas na conexão! " + namEx.toString());

		}
	}

	public DirContext getConnection() {
		return connection;
	}

	public static LdapConnection getInstance() {
		if (instance == null) {
			instance = new LdapConnection();
		}

		return instance;

	}

	public static String buscaUsuarioDepartamento(String stringBusca) {
		String departamento = "";

		try {
			DirContext authContext = getInstance().getConnection();
			log.info("Autenticação LDAP OK! Buscando por usuário " + stringBusca + "...");

			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

			String[] attrIDs = { "distinguishedName", "sn", "givenname", "mail", "department" };
			constraints.setReturningAttributes(attrIDs);

			NamingEnumeration<?> answer = authContext.search("OU=IFRN,DC=ifrn,DC=local",
					"sAMAccountName=" + stringBusca, constraints);
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				departamento = attrs.get("department").get().toString();
				log.info("Usuário encontrado (departamento: " + departamento + ")");

			} else {
				log.error("Usuário Inválido");
			}

		} catch (Exception exception) {
			log.error("Ocorreu um erro ao realizar uma consulta no LDAP.", exception);
		}

		return departamento;
	}
}
