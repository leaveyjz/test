package cn.dw.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.dw.model.Product;
import cn.dw.utils.JdbcUtils;
import jdk.nashorn.internal.objects.annotations.Where;

public class ProductDao extends BaseDao{
	
	public static void main(String[] args) {
		ProductDao dao = new ProductDao();
		Product product = dao.getById(3);
		System.out.println(product);
	}
	public int insert(Product product) {
		String sql = "Insert into product (name,price,remark) values (?,?,?)";
		return super.executeUpdate(sql, new Object[] {product.getName(),product.getPrice(),product.getRemark()});
	}
	
	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return super.executeUpdate(sql, new Object[] {id});
	}
	
	public int update(Product product) {
		String sql = "Update product set name=?,price=?,remark=? where id=?";
		return super.executeUpdate(sql, new Object[] {product.getName(),product.getPrice(),product.getRemark(),product.getId()});
	}
	
	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		JdbcUtils jdbcUtils = new JdbcUtils();
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Product product = null;
		try {
			conn = jdbcUtils.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			if (rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setRemark(rs.getString("remark"));
				product.setDate(rs.getDate("date"));
			}
			return product;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}finally {
			try {
				rs.close();
				pre.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			}
		}
		
	}
	
	public ArrayList<Product> getByName(String name){
		String sql = "select * from product where name like ?";
		JdbcUtils jdbcUtils = new JdbcUtils();
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		ArrayList<Product> list = new ArrayList<Product>();
		conn = jdbcUtils.getConnection();
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, "%" + name + "" );
			rs = pre.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setRemark(rs.getString("remark"));
				product.setDate(rs.getDate("date"));
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}

	}
}
