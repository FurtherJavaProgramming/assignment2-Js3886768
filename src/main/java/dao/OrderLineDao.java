package dao;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import model.OrderLine;

/**
 * A data access object (DAO) is a pattern that provides an abstract interface 
 * to a database or other persistence mechanism. 
 * the DAO maps application calls to the persistence layer and provides some specific data operations 
 * without exposing details of the database. 
 */
public interface OrderLineDao {
	void setup() throws SQLException;
	OrderLine createOrderLine(int orderno, String booktitle, int copies, int price) throws SQLException;
	ObservableList<OrderLine> getOrderLineList(int orderno) throws SQLException;
	




}
