package model.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	/* Singleton design pattern */
	private static ConnectionFactory factory = new ConnectionFactory();
	
	static final String DB_URL = "jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g";
	static String USER = "";
	static String PASS = "";
	
	private ConnectionFactory() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection newConnectio(Account accout) {
		USER = accout.getUsername();
		PASS = accout.getPassword();
		return factory.createConnection();
	}
}
