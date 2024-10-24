package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import model.Book;

/**
 * A data access object (DAO) is a pattern that provides an abstract interface 
 * to a database or other persistence mechanism. 
 * the DAO maps application calls to the persistence layer and provides some specific data operations 
 * without exposing details of the database. 
 */
public interface BookDao {
	void setup() throws SQLException;
	Book getBook(String booktitle, String author, int copies, int price, int sold) throws SQLException;
	ObservableList<Book> getBookList() throws SQLException;

}
