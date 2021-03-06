package jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo4 {
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Or175824");
			//获取连接池连接
			conn = DBUtil.getConnection();
			Statement state = conn.createStatement();
			String sql = "SELECT * FROM userinfo";
			ResultSet rs = state.executeQuery(sql);
			
			ResultSetMetaData rsmd =rs.getMetaData();
			int count = rsmd.getColumnCount();
			for(int i = 1; i <=count; i++){
				/**
				 * 查看字段名
				 */
				String colName = rsmd.getColumnName(i);
				System.out.println(colName);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn!=null) {
				
				DBUtil.closeConnection(conn);
			}
		}
			
	}
}
