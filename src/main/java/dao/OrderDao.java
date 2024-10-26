package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import model.Cart;
import model.Order;

/**
 * A data access object (DAO) is a pattern that provides an abstract interface 
 * to a database or other persistence mechanism. 
 * the DAO maps application calls to the persistence layer and provides some specific data operations 
 * without exposing details of the database. 
 */
public interface OrderDao {
	void setup() throws SQLException;
	Order createOrder(int orderno, String username, String date, int price) throws SQLException;
	boolean getUniqueOrderno(int orderno) throws SQLException;
	ObservableList<Order> getOrderList(String username) throws SQLException;




}
