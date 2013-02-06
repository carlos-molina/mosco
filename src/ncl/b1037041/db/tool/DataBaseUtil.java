package ncl.b1037041.db.tool;

import java.sql.*;

public class DataBaseUtil {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			String url ="jdbc:mysql://127.0.0.1/bpmn2promela?user=root&password=root&useUnicode=true&characterEncoding=utf-8";  
			con= DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeStatement(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResultSet(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollbackConnection(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
