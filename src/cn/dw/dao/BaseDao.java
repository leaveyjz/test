package cn.dw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.dw.model.Product;
import cn.dw.utils.JdbcUtils;

public class BaseDao {
	protected int executeUpdate(String sql,Object[] param) {
		JdbcUtils jdbcUtils = new JdbcUtils();
		Connection connection = null;
		PreparedStatement pre = null;
		
		try {
			connection = jdbcUtils.getConnection();
			pre = connection.prepareStatement(sql);
			for(int i = 0;i<param.length;i++) {
				pre.setObject(i+1, param[i]);
			}
			return pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}finally {
			try {
				pre.close();
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	protected ArrayList<Product> queryByName(String sql,Object[] param){
		JdbcUtils jdbcUtils = new JdbcUtils();
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		conn = jdbcUtils.getConnection();
		try {
			pre = conn.prepareStatement(sql);
			for(int i = 0;i < param.length;i++) {
				pre.setObject(i+1, param[i]);
			}
			rs = pre.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setDate(rs.getDate("Date"));
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setRemark(rs.getString("remark"));
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}finally {
			try {
				rs.close();
				pre.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	
}
