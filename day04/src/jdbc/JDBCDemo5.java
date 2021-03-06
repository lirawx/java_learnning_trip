package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo5 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Or175824");
			System.out.println("链接成功");
			Statement statement = conn.createStatement();
			String sql = "SELECT id,username,password,email,nickname,account "
					+ "FROM userinfo";
//			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			/**
			 * java.sql.ResultSet
			 * 表示一个数据库的查询结果
			 * ResultSet提供了便利的方法
			 * boolean next()
			 * 判断结果及是否还有下一条，若有返回true
			 * 并使得当前ResultSet的指针指向下一条并表示该条的记录
			 * 否则返回false
			 * 
			 * 同时提供了若干getXXX方法
			 * 根据字段名获取该字段对应的值
			 * 不同字段类型使用不同的方法
			 */
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String nickname = rs.getString("nickname");
				double account = rs.getDouble("account");
				
				System.out.println(id+","+username+","+password+","+email+","+nickname+","+account);
				
			}
		} catch (Exception e) {
			e.printStackTrace();		}
	}
}
