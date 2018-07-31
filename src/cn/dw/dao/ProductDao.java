package cn.dw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.dw.model.Product;
import cn.dw.utils.JdbcUtils;

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
		ArrayList<Product> prolist = super.queryByName(sql,new Object[] {id});
		return prolist.size()!=0? prolist.get(0):null;
	}
	
	public ArrayList<Product> getByName(String name){
		String sql = "select * from product where name like ?";
		return super.queryByName(sql, new Object[] {"%" + name + "%"});
	}
}
