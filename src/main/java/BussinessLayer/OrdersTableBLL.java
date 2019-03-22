package BussinessLayer;



import java.util.ArrayList;


import DataAccessLayer.OrdersTableDAO;
import EntititesLayer.OrdersTable;

public class OrdersTableBLL {
	
	
	public static void insertOrder(OrdersTable ordert) {
		
		 OrdersTableDAO.insert(ordert);
	}
	public static OrdersTable selectOrder(int id) {
		OrdersTable o=OrdersTableDAO.select(id);
		return o;
	}
	public static ArrayList<?>  selectAll() {
		ArrayList<?> c=OrdersTableDAO.selectAll();
		return c;
		
	}
	
	
	
}
