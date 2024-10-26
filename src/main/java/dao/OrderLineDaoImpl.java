package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.Cart;
import model.Order;
import model.OrderLine;
import model.User;

public class OrderLineDaoImpl implements OrderLineDao {
	private final String TABLE_NAME = "'orderlines'";
	private ObservableList<Cart> dataCart;

	public OrderLineDaoImpl() {
	}

	@Override
	public void setup() throws SQLException {
		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();) {
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (username VARCHAR(10) NOT NULL,"
				+ "password VARCHAR(8) NOT NULL," + "PRIMARY KEY (username))";
			stmt.executeUpdate(sql);
		} 
	}


	@Override
	public OrderLine createOrderLine(int orderno, String booktitle, int copies, int price)throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?)";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, Integer.toString(orderno));
			stmt.setString(2, booktitle);
			stmt.setString(3, Integer.toString(copies));
			stmt.setString(4, Integer.toString(price));
		
			

			stmt.executeUpdate();
			return new OrderLine(orderno, booktitle,copies, price);
		} 
	}

	
	
	
	
	
}


    
