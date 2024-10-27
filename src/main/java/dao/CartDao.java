package dao;

import java.sql.SQLException;
import javafx.collections.ObservableList;
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
	Cart getCart(String booktitle, String username) throws SQLException;
	void removeCart(int rowid)throws SQLException;
	void updateQuantityCart(int rowid,int copies)throws SQLException;
	void addQuantityCart(int rowid,int copies)throws SQLException;



}
