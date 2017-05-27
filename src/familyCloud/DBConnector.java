package familyCloud;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用JDBC连接数据库
 * 
 * @author Yukikari Samuya
 * 
 */
public class DBConnector {

	private static int QUERY_RETRIES = 10;
	private static int DEF_ISOLATION = Connection.TRANSACTION_READ_COMMITTED;

	private String dbUrl;
	private String password;
	private String username;
	private String jdbcClassName;
	private Connection dbCon;

	private static DBConnector myInstance = null;

	/**
	 * 构建数据库
	 */
	private DBConnector() throws Exception {
		connectAsDefaultLibrary();
	}

	/**
	 * 连接数据库
	 */

	private DBConnector(String inUrl, String inJdbcClassName,
			String inUserName, String inPassWord) throws Exception {
		dbUrl = inUrl;
		jdbcClassName = inJdbcClassName;
		username = inUserName;
		password = inPassWord;
		closeConnections();
		connect();
	}

	/**
	 * 创建数据库连接
	 */
	public void connectAsDefaultLibrary() throws Exception {
		dbUrl = "jdbc:mysql://localhost:3306/family_cloud";
		jdbcClassName = "com.mysql.jdbc.Driver";
		username = "root";
		password = "15202787159";
		closeConnections();
		connect();
	}

	/**
	 * 连接数据库
	 */
	private void connect() throws Exception {

		Driver driver = (Driver) Class.forName(jdbcClassName).newInstance();
		DriverManager.registerDriver(driver);

		dbCon = DriverManager.getConnection(dbUrl, username, password);
	}

	/**
	 * 关闭连接
	 */
	private void closeConnections() throws Exception {
		if (dbCon != null) {
			dbCon.close();
		}
	}

	/**
	 * 获取连接实例
	 */
	public static DBConnector Instance() throws Exception {
		if (myInstance == null) {
			myInstance = new DBConnector();
			myInstance.connectAsDefaultLibrary();
		}
		return myInstance;
	}

	/**
	 * 获取状态
	 */
	private Statement getStmt(boolean isAutoCommit, int isolationLevel)
			throws SQLException {
		dbCon.setAutoCommit(isAutoCommit);
		dbCon.setTransactionIsolation(isolationLevel);
		return dbCon.createStatement();
	}

	/**
	 * 查询数据库方法
	 */
	public ResultSet runQuery(String sqlQuery) throws Exception {

		Statement dbStatement = this.getStmt(true, DEF_ISOLATION);
		return dbStatement.executeQuery(sqlQuery);
	}

	/**
	 * 更新数据库方法
	 */
	public int runUpdate(String sqlQuery) throws Exception {

		Statement dbStatement = getStmt(true, DEF_ISOLATION);
		int rowCount = dbStatement.executeUpdate(sqlQuery);
		dbStatement.close();
		return rowCount;
	}

	/**
	 * 执行一个sql查询事务
	 */
	public ResultSet runChainedQuery(String sqlQuery) throws Exception {

		int retry = 0;
		boolean txnSuccess = false;
		Statement dbStatement = null;
		ResultSet resultSet = null;

		// Connect to the database.
		// Retry the query until complete or timeout.
		while (!txnSuccess && retry++ < QUERY_RETRIES) {
			try {
				dbStatement = getStmt(false,
						Connection.TRANSACTION_READ_COMMITTED);
				// Execute the query.
				resultSet = dbStatement.executeQuery(sqlQuery);
				// Commit the transaction.
				dbCon.commit();
				txnSuccess = true;
			} catch (SQLException se) {
				dbCon.rollback();
				dbStatement.close();
				// If deadlocked, try again after 10 msec
				if (se.getSQLState().equals("40P01")) {
					Thread.sleep(10);
				} else {
					dbCon.setTransactionIsolation(DEF_ISOLATION);
					throw se;
				}
			} catch (Exception e) {
				dbCon.rollback();
				dbStatement.close();
				dbCon.setTransactionIsolation(DEF_ISOLATION);
				throw e;
			}
		}
		return resultSet;
	}

	/**
	 * 执行一个sql更新事务
	 */
	public boolean runChainedUpdate(String[] sqlQuery) throws Exception {
		int retry = 0;
		boolean txnSuccess = false;
		Statement dbStatement = null;

		while (!txnSuccess && retry++ < QUERY_RETRIES) {
			// Begin a new transaction.
			try {
				dbStatement = getStmt(false,
						Connection.TRANSACTION_READ_COMMITTED);
				// For each sql statement, perform the update.
				for (int i = 0; i < sqlQuery.length; i++) {
					dbStatement.executeUpdate(sqlQuery[i]);
				}
				// Commit the transaction and close.
				dbCon.commit();
				dbStatement.close();
				txnSuccess = true;
			} catch (SQLException se) {
				dbCon.rollback();
				dbStatement.close();
				// If deadlocked, try again after 10 msec
				if (se.getSQLState().equals("40P01")) {
					Thread.sleep(10);
				} else {
					dbCon.setTransactionIsolation(DEF_ISOLATION);
					throw se;
				}
			} catch (Exception e) {
				dbCon.rollback();
				dbStatement.close();
				dbCon.setTransactionIsolation(DEF_ISOLATION);
				throw e;
			}
		}
		return txnSuccess;
	}
}