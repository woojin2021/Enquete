package database;

import java.sql.*;

public class DBAccess {
	private Connection _conn;
	private DatabaseType type;
	private String server;
	private String port;
	private String database;
	private String user;
	private String password;
	
	public enum DatabaseType {
		Oracle, MariaDB
	}
	
	public DBAccess() {
		// TODO 설정파일에서 취득
		type = DatabaseType.Oracle;
		server = "localhost";
		port = "1521";
		database = "orcl";
		user = "Enquete_Master";
		password = "Survey_2022";
	}


	public Connection getConnection() {
		try {
			if (_conn == null || _conn.isClosed()) {
				Class.forName(getJDBCDriverClassName());
				_conn = DriverManager.getConnection(getConnectionString());
			}
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC: Driver class is not found.");
			e.printStackTrace();
//				throw e;
		} catch (SQLException e) {
			System.err.println("JDBC: DB connection is failed.");
			e.printStackTrace();
//				throw e;
		}
		return _conn;
	}
	
	private String getJDBCDriverClassName() {
		String className = null;
		if (type == DatabaseType.Oracle) {
			className = "oracle.jdbc.driver.OracleDriver";
		}
		if (type == DatabaseType.MariaDB) {
			className = "org.mariadb.jdbc.Driver";
		}
//		System.out.println("DEBUG::className:" + className);
		return className;
	}
	
	private String getConnectionString() {
		String connStr = null;
		
		if (type == DatabaseType.Oracle) {
			//jdbc:oracle:driver_type:[username/password]@database_specifier
			connStr = "jdbc:oracle:thin:" + user + "/" + password + "@" + server + ":" + port + ":" + database;
		}
		if (type == DatabaseType.MariaDB) {
			//jdbc:mariadb:[replication:|loadbalance:|sequential:]//<hostDescription>[,<hostDescription>...]/[database][?<key1>=<value1>[&<key2>=<value2>]] 
			//<host>[:<portnumber>]  or address=(host=<host>)[(port=<portnumber>)][(type=(master|slave))]
			database = "JSPBookDB";
			user = "root";
			password = "my-secret-pw";
			port = "3306";
			connStr = "jdbc:mariadb://" + server+ ":" + port + "/" + database
					+ "?user=" + user + "&password=" + password;
		}
//		System.out.println("DEBUG::connStr:" + connStr);
		return connStr;
	}
	
	public void debugPreparedStatement(String sql, Object ... args) {
		int idx = 0;
		int preparedParamCount = 0;
		do {
			idx = sql.indexOf("?", idx);
			if (idx > -1) {
				preparedParamCount++;
			}
		} while (idx > -1);
		if (preparedParamCount != args.length) {
			System.err.println("parameter count is not matched.");
			return;
		}
		idx = 0;
		for (int i = 0; i < args.length; i++) {
			String param = "[" + (i + 1) + ":" + args[i].getClass().getName() + ":" + args[i].toString();
			sql.replaceFirst("?", param);
		}
		System.out.println("SQL Statement: " + sql);
	}
	
}
