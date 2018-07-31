package cn.dw.service;

import java.util.ArrayList;

import cn.dw.dao.ProductDao;
import cn.dw.model.Product;

public class ProductService {
	ProductDao productDao = new ProductDao();
	public int insert(Product product) {
		return productDao.insert(product);
	}
	
	public ArrayList<Product> getByName(String keyword){
		return productDao.getByName(keyword);
	}
	
	public Product getById(int id){
		return productDao.getById(id);
	}
	
	public int deleteById(int keyword){
		return productDao.delete(keyword);
	}
	
	public int update(Product product) {
		return productDao.update(product);
	}
}
