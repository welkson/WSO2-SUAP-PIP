package br.edu.ifrn.pip.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.edu.ifrn.pip.SuapAttributeFinder;
import br.edu.ifrn.pip.TipoAtributo;

public class PostgresConnection {
	private static Log log = LogFactory.getLog(SuapAttributeFinder.class);
	private static PostgresConnection instance;
	private Connection connection;

	//DAO
	private PostgresConnection() {
		ConfigUtil config = ConfigUtil.getInstance();
		try {
			Class.forName(config.recuperarValorDeConfiguracao("pg.driver"));
			this.connection = DriverManager.getConnection(config.recuperarValorDeConfiguracao("pg.url"),
					config.recuperarValorDeConfiguracao("pg.usuario"), config.recuperarValorDeConfiguracao("pg.senha"));
		} catch (ClassNotFoundException exception) {
			log.error(
					"A classe do driver do PostgreSQL não foi localizada. Certifique-se de que está definida corretamente no arquivo de configuração e de que do driver está no classpath.",
					exception);
		} catch (SQLException exception) {
			log.error("Falha ao conectar no PostgreSQL.", exception);
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public static PostgresConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new PostgresConnection();
		} else if (instance.getConnection().isClosed()) {
			instance = new PostgresConnection();
		}

		return instance;
	}

	public String buscarPorSolicitante(String stringBusca) {			//TODO: melhorar nome do método
		log.info("debug1 -> stringBusca: " + stringBusca);
		
		//recupera id do chamado a partir da URL (resourceId)
		String regex = "\\/chamado\\/(.*?)(\\/|$)";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(stringBusca);
	    matcher.find();
	    Integer idChamado = Integer.valueOf(matcher.group(1));
	    log.info("idChamado: " + idChamado.toString());
		
		String resultadoBusca = "";

		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement("SELECT A.username " +										//TODO: usar constante
																		   "FROM centralservicos_chamado C " +
																		   "INNER JOIN auth_user A ON A.id = C.aberto_por_id " +
																		   "WHERE C.id = ?");
			pstmt.setInt(1,idChamado);
			ResultSet rs = pstmt.executeQuery();

			while ( rs.next() )
			{
				resultadoBusca = rs.getString (1);
			}
			rs.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		log.info("<<<<<<<<<<<<<<<<< stringBusca: " + stringBusca + " / resultadoBusca: " + resultadoBusca);

		return resultadoBusca;
	}
}