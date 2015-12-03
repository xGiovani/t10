package database;

/**
 *
 * @author Giovani
 */
public class DbUtils {
        // Constantes para conexão com PostgreSQL
	static final String JDBC_DRIVER = "org.postgresql.Driver";
        static final String DB_NAME = "gasstations";
	static final String SENHA = "452613";
	static final String USUARIO = "postgres";
	static final String URL_CONEXAO = "jdbc:postgresql://localhost/"+DB_NAME;
}
