package DataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import EntititesLayer.OrderProduct;

public class OrderProductDAO {
	protected static final Logger LOGGER = Logger.getLogger(ProductsDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO OrderProduct (idorderproduct,idorder,idproduct)"
			+ " VALUES (?,?,?)";
	public static int insert(OrderProduct o) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, o.getIdordeproduct());
			insertStatement.setInt(2, o.getIdorder());
			insertStatement.setInt(3,o.getIdproduct());
			
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
