package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;

public class BookDaoImpl implements BookDao {
	private final String TABLE_NAME = "books";
	 private ObservableList<Book> dataBooks;
	 private ObservableList<String> dataTitle;

	public BookDaoImpl() {
		
	}

	@Override
	public void setup() throws SQLException {
		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();) {
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(booktitle TEXT NOT NULL,"
					+ "Author	TEXT NOT NULL,"
					+ "	copies	INTEGER,"
					+ "	price	INTEGER NOT NULL,"
					+ "	sold	INTEGER,"
					+ "	PRIMARY KEY(booktitle))";
			stmt.executeUpdate(sql);
		} 
	}


	
	@Override
	public ObservableList<Book> getBookList() throws SQLException {
		
		String sql = "SELECT booktitle,Author,copies,price,sold FROM books";
		 dataBooks = FXCollections.observableArrayList();
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			try (ResultSet rs = stmt.executeQuery()){
	            while(rs.next()) {
	                Book bk = new Book();
	                bk.setbooktitle(rs.getString("booktitle"));
	                bk.setauthor(rs.getString("Author"));
	                bk.setcopies(rs.getInt("copies"));
	                bk.setprice(rs.getInt("price"));
	                bk.setsold(rs.getInt("sold"));
	                dataBooks.add(bk);                
	            } 
	            return dataBooks;
	            
	            
	           

	        }catch (SQLException ex) {}
			}
		return dataBooks;
		
			}
	
	@Override
	public ObservableList<String> getBookTitleList() throws SQLException {
		
		String sql = "SELECT booktitle FROM books";
		 dataTitle = FXCollections.observableArrayList();
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			try (ResultSet rs = stmt.executeQuery()){
	            while(rs.next()) {
	                dataTitle.add(rs.getString("booktitle"));
	                             
	            } 
	            return dataTitle;
	            
	            
	           

	        }catch (SQLException ex) {}
			}
		return dataTitle;
		
			}
	@Override
	public Book getBook(String booktitle) throws SQLException {
		String sql = "SELECT * FROM books WHERE booktitle = ?";
		 dataTitle = FXCollections.observableArrayList();
			try (Connection connection = Database.getConnection();
					PreparedStatement stmt = connection.prepareStatement(sql);) {
				
				stmt.setString(1, booktitle);
			
		

				try (ResultSet rs = stmt.executeQuery()) {
				    Book bk = new Book();
				  
				    bk.setbooktitle(rs.getString("booktitle"));
				    bk.setauthor(rs.getString("Author"));
				    bk.setprice(rs.getInt("price"));
				    bk.setcopies(rs.getInt("copies"));
				    bk.setsold(rs.getInt("sold"));
				    return bk;
				}
				
	            
	            
	           

	        }catch (SQLException ex) {}
			return new Book();
			}
	
		
			

	@Override
	public Book getBook(String booktitle, String author, int copies, int price, int sold) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setcopies(String booktitle, int copies) throws SQLException {
		String sql = "UPDATE [books]" +
				"SET copies = ?" +
				"WHERE booktitle = ?" ;
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			
			stmt.setString(1, Integer.toString(copies));
			stmt.setString(2, booktitle);
	        stmt.executeUpdate();
		
	}}
		
	

	@Override
	public void setsold(String booktitle,int copies) throws SQLException {
		String sql = "UPDATE [books]" +
				"SET copies = copies - ?" +
				",sold = sold + ?" +
				"WHERE booktitle = ?" ;
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			
			stmt.setString(1, Integer.toString(copies));
			stmt.setString(2, Integer.toString(copies));
			stmt.setString(3, booktitle);
	        stmt.executeUpdate();
		
	}}

	@Override
	public void updateQuantityBook(String booktitle, int copies) throws SQLException {
		String sql = "UPDATE [books]" +
				"SET copies = ?" +
				"WHERE booktitle = ?" ;
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			
			stmt.setString(1, Integer.toString(copies));
			stmt.setString(2, booktitle);
	        stmt.executeUpdate();
		
	}}
		
	}


	
	



    
