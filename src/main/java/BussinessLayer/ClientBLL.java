package BussinessLayer;

import java.util.ArrayList;
import java.util.List;

import DataAccessLayer.ClientDAO;
import EntititesLayer.Client;


public class ClientBLL {
	
	

	public Client findClientById(int id) {
		Client st = ClientDAO.findById(id);
		return st;
	}

	public static void insertClient (Client c) {
		  ClientDAO.insert(c);
	}
	public static void deleteClient(int id) {
		
		ClientDAO.delete(id);
	}
	public static int updateClient(int id,Client client) {

		return ClientDAO.update(id,client);
	}
	public static Client  selectClient(int id) {
		Client c=ClientDAO.select(id);
		return c;
		
	}
	public static ArrayList<?>  selectAll() {
		ArrayList<?> c=ClientDAO.selectAll();
		return c;
		
	}
   
	
}
