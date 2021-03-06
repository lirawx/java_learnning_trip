package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utils.DBUtil;
import models.User;

public class UserDaoImpl implements UserDao{

	public boolean Add(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO user "
					+ "(username,password,nickname,email,avatar) "
					+ "VALUES "
					+ "(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getAvatar());
			
			int i = ps.executeUpdate();
			if(i > 0){
				System.out.println("插入成功！");
				return true;
			}else{
				System.err.println("插入失败！");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(ps !=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn !=null){
				DBUtil.closeConnection(conn);
			}
		}
		return false;
	}

	public User findUserByName(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		User user = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * "
					+ "From user "
					+ "WHERE username =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setAvatar(rs.getString("avatar"));
				user.setStatus(rs.getInt("status"));
				user.setAuthority(rs.getInt("authority"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(ps !=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn !=null){
				DBUtil.closeConnection(conn);
			}
		}
		return user;
	}

}
