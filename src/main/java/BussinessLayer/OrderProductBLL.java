package BussinessLayer;


import DataAccessLayer.OrderProductDAO;
import EntititesLayer.OrderProduct;

public class OrderProductBLL {
	
    
	public static void insertOrderProduct(OrderProduct o) {

		 OrderProductDAO.insert(o);
	}
	
}
