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
import model.User;

public class OrderDaoImpl implements OrderDao {
	private final String TABLE_NAME = "'order'";
	private ObservableList<Order> dataCart;

	public OrderDaoImpl() {
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
	public Order createOrder(int orderno, String username, String date, int price)throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?)";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, Integer.toString(orderno));
			stmt.setString(2, username);
			stmt.setString(3, date);
			stmt.setString(4, Integer.toString(price));
		
			

			stmt.executeUpdate();
			return new Order(orderno, username,date, price);
		} 
	}
	@Override
	public boolean getUniqueOrderno(int orderno) throws SQLException{
		String sql = "SELECT * FROM 'order' WHERE orderno = ?";
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, Integer.toString(orderno));		
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return true;
				}else {
					return false;}
				}
				
				}
			
	}
	@Override
	public ObservableList<Order> getOrderList(String username) throws SQLException {
		
		String sql = "SELECT orderno, date, price, rowid FROM 'order' WHERE username ='?'ORDER BY 'date' DESC ";
		 dataCart = FXCollections.observableArrayList();
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			try (ResultSet rs = stmt.executeQuery()){
	            while(rs.next()) {
	                Order or = new Order();
	                or.setorderno(rs.getInt("orderno"));
	                or.setdate(rs.getString("date"));   
	                or.setprice(rs.getInt("price"));
	                or.setrowid(rs.getInt("rowid"));
	                dataCart.add(or);                
	            } 
	            return dataCart;
	            
	           
	        }catch (SQLException ex) {}
			}
		return dataCart;
		
			}
	
	
	
	
	
}


    
