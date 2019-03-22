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
import EntititesLayer.Products;


public class ProductsDAO {
    
	protected static final Logger LOGGER = Logger.getLogger(ProductsDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO Products (idproducts,name,price,category,stock)"
			+ " VALUES (?,?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM Products where idproducts = ?";
	private static final String deleteStatementString = "DELETE FROM Products where idproducts= ?";
	private static final String updateStatementString=("UPDATE Products SET name= ?, price= ?, category=?,stock=? WHERE idproducts=?");
	private final static String selectStatementString = "SELECT * FROM Products where idproducts=? ";
	private final static String selectAll="SELECT * FROM Products";
	
	public static Products findById(int ProductsId) {
		
		Products toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, ProductsId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			double price=rs.getDouble("price");
			String category = rs.getString("category");
			int stock=rs.getInt("stock");
			
			toReturn = new Products(ProductsId, name, price,category,stock);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductsDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
    public static int insert(Products Products) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Products.getIdproducts());
			insertStatement.setString(2, Products.getName());
			insertStatement.setDouble(3, Products.getPrice());
			insertStatement.setString(4, Products.getCategory());
			insertStatement.setInt(5, Products.getStock());
			
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductsDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	public static void delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductsDAO:delete " + e.getMessage());
		} finally {
			
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	public static int update(int id,Products Products) {
		Connection dbConnection = ConnectionFactory.getConnection();
		
		
		PreparedStatement updateStatement = null;
		int updateId = -1;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, Products.getName());
			updateStatement.setDouble(2, Products.getPrice());
			updateStatement.setString(3, Products.getCategory());
			updateStatement.setInt(4, Products.getStock());
		    updateStatement.setInt(5,id);

			updateStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductsDAO:update" + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return updateId;
	}
	public static Products select(int id) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs=null;
		Products toReturn=null;
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			selectStatement.setInt(1,id);
			//selectStatement.executeUpdate();
			rs = selectStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			double price=rs.getDouble("price");
			String category = rs.getString("category");
			int stock=rs.getInt("stock");
			
			toReturn = new Products(id, name, price,category,stock);
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductsDAO:select " + e.getMessage());
		} finally {
			
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static ArrayList<Products> selectAll() {
		
		ArrayList<Products> j=new ArrayList<Products>();
       
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs=null;
		try {
			selectStatement = dbConnection.prepareStatement(selectAll);
			 rs = selectStatement.executeQuery();
		
			 
            while(rs.next()) {
            	int id=rs.getInt("idproducts");
            	String name = rs.getString("name");
            	String category = rs.getString("category");
            	double price = rs.getDouble("price");
            	int stock = rs.getInt("stock");
            	Products toReturn = new Products(id, name, price, category, stock);
                 
            	j.add(toReturn);
            }
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:selectAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
	 return j;
	}
	public static void main(String[] args) {
		selectAll();
	}
	
    
}
