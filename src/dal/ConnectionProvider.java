package dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {

	private static Context context;
	private static DataSource dataSource;

	static {

		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getCnx() throws SQLException {

		Connection cnx = dataSource.getConnection();

		return cnx;
	}

}
