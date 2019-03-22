package BussinessLayer;

import java.util.ArrayList;
import DataAccessLayer.ProductsDAO;
import EntititesLayer.Products;

public class ProductsBLL {
	
	public Products findProductById(int id) {
		Products st = ProductsDAO.findById(id);
		return st;
	}
	public static void insertProducts(Products Products) {
		
	    ProductsDAO.insert(Products);
	}
	public static void deleteProducts(int id) {
		
		ProductsDAO.delete(id);
	}
	public static int updateProducts(int id,Products product) {
		
		return ProductsDAO.update(id,product);
	}
	public static Products  selectProduct(int id) {
		Products c=ProductsDAO.select(id);
		return c;
		
	}
	
	public static ArrayList<?> selectAll() {
		
		ArrayList<?> c=ProductsDAO.selectAll();
		return c;
		
	}
	public static void main(String[] args) {
		
	}
	
}
