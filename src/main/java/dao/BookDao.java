package dao;

import java.sql.SQLException;
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
	Book getBook(String booktitle) throws SQLException;
	ObservableList<Book> getBookList() throws SQLException;
	ObservableList<String> getBookTitleList() throws SQLException;
	void setcopies(String booktitle, int copies) throws SQLException;
	void setsold(String booktitle,int sold) throws SQLException;

}
