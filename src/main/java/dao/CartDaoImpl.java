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
import model.User;

public class CartDaoImpl implements CartDao {
	private final String TABLE_NAME = "cart";
	private ObservableList<Cart> dataCart;

	public CartDaoImpl() {
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
	public Cart createCart(String booktitle, String username, int copies, int price, int sold) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?)";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, booktitle);
			stmt.setString(3, Integer.toString(copies));
			stmt.setString(4, Integer.toString(price));
		
			

			stmt.executeUpdate();
			return new Cart(username, booktitle,copies, price,sold);
		} 
	}
	
	@Override
	public ObservableList<Cart> getCartList(String username) throws SQLException {
		
		String sql = "SELECT booktitle, username, quantity, price, rowid FROM cart WHERE username = ?";
		 dataCart = FXCollections.observableArrayList();
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			try (ResultSet rs = stmt.executeQuery()){
	            while(rs.next()) {
	                Cart ct = new Cart();
	                ct.setbooktitle(rs.getString("booktitle"));
	                ct.setusername(rs.getString("username"));
	                ct.setcopies(rs.getInt("quantity"));
	                ct.setprice(rs.getInt("price"));
	                ct.setrowid(rs.getInt("rowid"));
	                dataCart.add(ct);                
	            } 
	            return dataCart;
	            
	            
	           

	        }catch (SQLException ex) {}
			}
		return dataCart;
		
			}
	
	@Override
	public void removeCart(int rowid) throws SQLException {
		
		String sql = "DELETE FROM cart WHERE rowid = ?";
		 dataCart = FXCollections.observableArrayList();
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, Integer.toString(rowid));
			stmt.execute();
			
		}}
	
	
	
}


    
