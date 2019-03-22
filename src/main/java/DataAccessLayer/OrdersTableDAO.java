package DataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import EntititesLayer.OrdersTable;

public class OrdersTableDAO {
	
	protected static final Logger LOGGER = Logger.getLogger(OrdersTableDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO OrdersTable (shippingstatus,amount)"
			+ " VALUES (?,?)";
	private final static String selectStatementString = "SELECT * FROM OrdersTable where idorders=? ";
	private final static String selectAll="SELECT * FROM OrdersTable";
	
	 public static int insert(OrdersTable ordert) {
		 
			Connection dbConnection = ConnectionFactory.getConnection();

			PreparedStatement insertStatement = null;
			int insertedId = -1;
			try {
				insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
				insertStatement.setString(1, ordert.getShippingStatus());
				insertStatement.setInt(2, ordert.getAmount());
				
				
				insertStatement.executeUpdate();

				ResultSet rs = insertStatement.getGeneratedKeys();
				if (rs.next()) {
					insertedId = rs.getInt(1);
				}
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "OrdersTableDAO:insert " + e.getMessage());
			} finally {
				ConnectionFactory.close(insertStatement);
				ConnectionFactory.close(dbConnection);
			}
			return insertedId;
		}
	 public static OrdersTable select(int id) {

			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement selectStatement = null;
			ResultSet rs=null;
			OrdersTable toReturn=null;
			try {
				selectStatement = dbConnection.prepareStatement(selectStatementString);
				selectStatement.setInt(1,id);
				
				rs = selectStatement.executeQuery();
				rs.next();

				String shippingStatus = rs.getString("shippingStatus");
				int amount=rs.getInt("amount");
				
				toReturn = new OrdersTable(id,shippingStatus,amount);
				
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"ProductsDAO:select " + e.getMessage());
			} finally {
				
				ConnectionFactory.close(selectStatement);
				ConnectionFactory.close(dbConnection);
			}
			return toReturn;
		}
	 public static ArrayList<OrdersTable> selectAll() {
			
			ArrayList<OrdersTable> j=new ArrayList<OrdersTable>();
	       
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement selectStatement = null;
			ResultSet rs=null;
			try {
				selectStatement = dbConnection.prepareStatement(selectAll);
				 rs = selectStatement.executeQuery();
			
				 
	            while(rs.next()) {
	            	int id=rs.getInt("idorders");
	            	String shippingStatus = rs.getString("shippingStatus");
	            	int amount = rs.getInt("amount");
	            	OrdersTable toReturn = new OrdersTable(id, shippingStatus,amount);
	                 
	            	j.add(toReturn);
	            }
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"OrdersTableDAO:selectAll " + e.getMessage());
			} finally {
				ConnectionFactory.close(rs);
				ConnectionFactory.close(selectStatement);
				ConnectionFactory.close(dbConnection);
			}
		 return j;
		}
		

}
