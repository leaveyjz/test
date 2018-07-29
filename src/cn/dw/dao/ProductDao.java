package cn.dw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import cn.dw.model.Product;
import cn.dw.utils.JdbcUtils;

public class ProductDao extends BaseDao{
	
	public static void main(String[] args) {
		ProductDao dao = new ProductDao();
		Product product = new Product();
		product.setId(1);
		product.setName("小米手机note");
		product.setPrice(3020.00);
		product.setRemark("商品备d1212");
		System.out.println(dao.update(product));
		//int result = dao.insert(product);
////		System.out.println("result:" + result);
		//int result = dao.delete(4);
//		System.out.println(result);
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
}
