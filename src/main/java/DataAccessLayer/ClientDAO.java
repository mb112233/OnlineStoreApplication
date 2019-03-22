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
import EntititesLayer.Client;

public class ClientDAO {
	
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO Client (id,name,address,email,age)"
			+ " VALUES (?,?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM Client where id = ?";
	private static final String deleteStatementString = "DELETE FROM Client where id= ?";
	private static final String updateStatementString=("UPDATE Client SET name= ?, address= ?, email=?, age=? WHERE id=?");
	private final static String selectStatementString = "SELECT * FROM Client where id=? ";
	private final static String selectAll="SELECT * FROM CLIENT";
	
	 
	public static Client findById(int ClientId) {
		Client toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, ClientId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			String address = rs.getString("address");
			String email = rs.getString("email");
			int age = rs.getInt("age");
			toReturn = new Client(ClientId, name, address, email, age);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
    public static int insert(Client Client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Client.getId());
			insertStatement.setString(2, Client.getName());
			insertStatement.setString(3, Client.getAddress());
			insertStatement.setString(4, Client.getEmail());
			insertStatement.setInt(5, Client.getAge());
			
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
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
			LOGGER.log(Level.WARNING,"ClientDAO:delete " + e.getMessage());
		} finally {
			
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	public static int update(int id,Client Client) {

		Connection dbConnection = ConnectionFactory.getConnection();
		
		
		PreparedStatement updateStatement = null;
		int updateId = -1;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString); 
			updateStatement.setString(1, Client.getName());
			updateStatement.setString(2, Client.getAddress());
			updateStatement.setString(3, Client.getEmail());
			updateStatement.setInt(4, Client.getAge());
		    updateStatement.setInt(5,id);

			updateStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:update" + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return updateId;
	}
	public static Client select(int id) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		Client toReturn=null;
		ResultSet rs=null;
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			selectStatement.setInt(1, id);
			//selectStatement.executeUpdate();
			
			rs = selectStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			String address = rs.getString("address");
			String email = rs.getString("email");
			int age = rs.getInt("age");
			
			toReturn = new Client(id, name, address, email, age);
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:select " + e.getMessage());
		} finally {
			
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static ArrayList<Client> selectAll() {
	
		ArrayList<Client> j=new ArrayList<Client>();
       
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs=null;
		try {
			selectStatement = dbConnection.prepareStatement(selectAll);
			 rs = selectStatement.executeQuery();
		
			 
            while(rs.next()) {
            	int id=rs.getInt("id");
            	String name = rs.getString("name");
            	String address = rs.getString("address");
            	String email = rs.getString("email");
            	int age = rs.getInt("age");
            	Client toReturn = new Client(id, name, address, email, age);
                 
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
