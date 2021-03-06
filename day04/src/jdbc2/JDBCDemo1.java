package jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * java.sql.statement
 * 只适合执行静态的sql语句，
 * 动态sql语句有两个缺点：
 * 		由于它有动态信息，就需要先拼接sql，就会有可能出现sql的注入式攻击
 * 		大部分情况下，拼接sql时，语义已经定义好，拼接内容无非是一些数据
 * 			那么当大批量执行这样含有动态数据sql时，数据库每当接收statement发送的
 * 			sql语句时，这样语句中的内容就会被当做一条全新的sql语句去执行
 * 			数据库执行sql语句会首先解析整句并生成一个执行计划，那么批量执行这样内容的时候
 * 			有一些微变化的sql会为每一个sql生成全新的计划
 * 	
 * java.sql.preparestatement
 * 
 * 		preparestatement该接口是statement的子接口
 * 	设计目的：
 * 		是为了执行该动态sql语句，这样的sql称为预编译sql，
 * 		先执行占位，用 “？”代替
 *		然后将该sql发送给数据库，
 *		数据库直接生成计划，当需要执行该sql的时候，只需将问号所需要的实际值替换掉。
 *		
 *ps：
 *		由于先将sql语句发送给数据库，并生成了计划，计划（语义已经确定)，就不存在sql注入式攻击。
 *		由于计划已经生成，当执行sql时，每次只要将问号表示的实际值传入，那么数据库会重用计划。
 * @author admin
 *
 */
public class JDBCDemo1 {
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Or175824");
			//使用preparestatement
			/**
			 * 预编译sql语句，将动态部分用“？”来代替，？只能来代替值，不会对sql语句造成语义上的改变。
			 * 
			 */
			String sql = "SELECT id,username,password,email,nickname,account "
					+ "FROM userinfo "
					+ "WHERE "
					+ "username = ? "
					+ "AND "
					+ "password = ? ";
			/**
			 * 创建PrepareStatement时候就需要把预编译的sql传入
			 * 此时，ps已经将该sql发送给数据库，准备随时生成计划。
			 */
			PreparedStatement ps = conn.prepareStatement(sql);
			/**
			 * 在执行sql前，还要将?部分的实际值传给数据库
			 */
			ps.setString(1, "aaa");
			ps.setString(2, "123456");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				System.out.println("登录成功！");
			}else {
				System.err.println("登录失败！");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn !=null){
				conn.close();	
			}
		}
	}
}
