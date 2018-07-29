package cn.dw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
