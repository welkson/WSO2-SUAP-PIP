package br.edu.ifrn.pip.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PostgresConnection {
    private static PostgresConnection instance;
    private Connection connection;
    private String pgUsuario;
	private String pgSenha;
	private String pgUrl;

    private PostgresConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wso2-pip-suap.properties");		
	    		if (inputStream != null) {
	    			Properties properties = new Properties();
	    			try {
	    				properties.load(inputStream);	    				
	    				pgUsuario = properties.getProperty("pg.usuario");
	    				pgSenha = properties.getProperty("pg.senha");
	    				pgUrl = properties.getProperty("pg.url");
	
	    			} catch (IOException exception) {
	    				exception.printStackTrace();
	    			}
	    		}            
            
            this.connection = DriverManager.getConnection(pgUrl, pgUsuario, pgSenha);
        } catch (ClassNotFoundException ex) {
            System.out.println("Falha ao conectar no banco " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static PostgresConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new PostgresConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new PostgresConnection();
        }

        return instance;
    }
    
    public String buscaCentralservicosDonoticket(String stringBusca) {
    		String resultadoBusca = "";
		Statement st;
	
		try {			
			st = this.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT nome " +
					 					  "FROM pessoa " + 
					 					  "LIMIT 1");

			while ( rs.next() )
		      {
		         resultadoBusca = rs.getString (1);
		      }
		      rs.close();
		      st.close();				

		} catch (SQLException e) {
		      System.err.println(e.getMessage());
		}		
    		return resultadoBusca;
    }
}