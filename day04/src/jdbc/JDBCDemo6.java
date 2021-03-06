package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 创建表 emp 包含字段： empno NUMBER(6) ename VARCHAR2(50) sal NUMBER(10,2) job
 * VARCHAR2(50) deptno NUMBER(6) 随意插入数据20到30条
 * 
 * @author admin
 *
 */
public class JDBCDemo6 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Or175824");
			System.out.println("链接成功");
			Statement state = conn.createStatement();
			// String sql = "CREATE TABLE emp ("
			// + "empno NUMBER(6), "
			// + "ename VARCHAR2(50), "
			// + "sal NUMBER(10,2), "
			// + " job VARCHAR2(50), "
			// + "deptno NUMBER(6) "
			// + ") ";
			// System.out.println(sql);
			// state.execute(sql);
			// System.out.println("成功");

			// 创建序列
//			String sql = "CREATE SEQUENCE seq_emp_id " + "START WITH 1 " + "INCREMENT BY 1";
//			System.out.println(sql);
//			state.execute(sql);
//			System.out.println("创建序列成功");
			
			//插入数据
			String sql = "INSERT INTO EMP (EMPNO, ENAME, SAL, JOB, DEPTNO) VALUES ('24', 'jack', '500', 'CEO', '20')";
			int i = state.executeUpdate(sql);
			if(i>0){
				System.out.println("插入成功"+i+"条数据。");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
