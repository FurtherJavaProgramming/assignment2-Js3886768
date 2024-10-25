package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import model.Book;
import model.Cart;

/**
 * A data access object (DAO) is a pattern that provides an abstract interface 
 * to a database or other persistence mechanism. 
 * the DAO maps application calls to the persistence layer and provides some specific data operations 
 * without exposing details of the database. 
 */
public interface CartDao {
	void setup() throws SQLException;
	Cart createCart(String booktitle, String username, int copies, int price, int sold) throws SQLException;
	ObservableList<Cart> getCartList(String username) throws SQLException;


}
