package jdbc3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import jdbc2.DBUtil;

/**
 * 使用ps完成对userinfo表中数据分页显示
 * 
 * @author admin
 *
 */
public class JDBCDemo4 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("请输入每页显示的条数");
			int pagesize = Integer.parseInt(sc.nextLine());
			System.out.println("请输入显示的页数");
			int page = Integer.parseInt(sc.nextLine());
			
			int  start = (page - 1)*pagesize+1;
			int end = page*pagesize;
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * "
					+ "FROM  (SELECT ROWNUM rn, t.* "
					+ "FROM (SELECT empno,ename,sal,job,deptno "
					+ "FROM emp ORDER BY sal DESC) t "
					+ "WHERE ROWNUM <=?) "
					+ "WHERE RN  >=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, end);
			ps.setInt(2, start);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				int sal = rs.getInt("sal");
				String job = rs.getString("job");
				int deptno = rs.getInt("deptno");
				System.out.println(empno+","+ename+","+sal+","+job+","+deptno);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn !=null){
				DBUtil.closeConnection(conn);
			}
		}
	}
}
