package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.User;

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
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (username VARCHAR(10) NOT NULL,"
					+ "password VARCHAR(8) NOT NULL," + "PRIMARY KEY (username))";
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
	public Book getBook(String booktitle, String author, int copies, int price, int sold) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	} 
		
	}


	
	



    
