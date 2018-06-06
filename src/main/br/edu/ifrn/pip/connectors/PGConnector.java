package br.edu.ifrn.pip.connectors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import br.edu.ifrn.pip.util.PostgresConnection;

public class PGConnector implements Connector {

	@Override
	public String recuperarValorDeAtributo(String buscaPor) {
		String resultado = "";
		try {
			Method metodoBusca = PostgresConnection.class.getMethod("buscaCentralservicosDonoticket", String.class); //TODO: recuperar o nome do método dinamicamente
			resultado = (String) metodoBusca.invoke(PostgresConnection.getInstance(), buscaPor);
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

}
