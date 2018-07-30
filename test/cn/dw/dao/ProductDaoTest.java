package cn.dw.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.dw.model.Product;

public class ProductDaoTest {

	private static ProductDao dao = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new ProductDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetById() {
		Product product = dao.getById(3);
		System.out.println(product);
	}
	
	@Test
	public void testGetByname() {
		ArrayList<Product> productList = dao.getByName("%小米%");
		for(Product temp:productList) {
			System.out.println(temp);
		}
	}
}
