package jdbc3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc2.DBUtil;

/**
 * 自动返回主键
 * 允许我们在插入一条数据同时将该条数据中资的字段返回
 * 通常返回的额都是系统生成的值
 * 例如 主键的值
 * @author admin
 *
 */
public class JDBCDemo3 {
		public static void main(String[] args) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql ="INSERT INTO dept "
						+ "(deptno, dname,loc) "
						+ "VALUES "
						+ "(seq_dept_id.NEXTVAL,?,?) ";
				/**
				 * 使用connection带两个参数的方法
				 * 生成ps，其中第二个参数是一个字符串数组，数组中每一个字符
				 * 内容应当是需要知道插入后该条记录希望返回值得字段名
				 */
				
				PreparedStatement ps = conn.prepareStatement(sql, new String[]{"deptno","dname","loc"});
				ps.setString(1, "php");
				ps.setString(2, "广州");
				int i = ps.executeUpdate();
				if(i > 0){
					System.out.println("插入成功");
					/**
					 * 获取刚插入的这个部门中deptno字段
					 */
					ResultSet rs = ps.getGeneratedKeys();
					rs.next();
					int deptno = rs.getInt(1);
					String dname = rs.getString(2);
					String loc = rs.getString(3);
					System.out.println("插入的部门id为："+deptno);
					System.out.println("插入的部门名称为："+dname);
					System.out.println("插入的部门地区为："+loc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!=null){
					DBUtil.closeConnection(conn);
				}
			}
		}
}
