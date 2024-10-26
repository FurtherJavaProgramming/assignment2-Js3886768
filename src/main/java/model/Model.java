package model;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.BookDao;
import dao.BookDaoImpl;
import dao.CartDao;
import dao.CartDaoImpl;
import dao.OrderDao;
import dao.OrderDaoImpl;
import dao.OrderLineDao;
import dao.OrderLineDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import javafx.collections.ObservableList;

public class Model {
	private UserDao userDao;
	private CartDao cartDao;
	private OrderDao orderDao;
	private OrderLineDao orderlineDao;
	private User currentUser; 
	private Book book;
	private Order order;
	private BookDao bookDao;
	private ArrayList <Book> dataBooks = new ArrayList<Book>() ;

	public Model() {
		userDao = new UserDaoImpl();
		bookDao = new BookDaoImpl();
		cartDao = new CartDaoImpl();
		orderDao = new OrderDaoImpl();
		orderlineDao = new OrderLineDaoImpl();
	}
	
	public void setup() throws SQLException {
		userDao.setup();
		bookDao.setup();
		cartDao.setup();
		orderDao.setup();
		orderlineDao.setup();
	}
	public UserDao getUserDao() {
		return userDao;
	}
	
	public BookDao getBookDao() {
		return bookDao;
	}
	
	public CartDao getCartDao() {
		return cartDao;
	}
	public OrderDao getOrderDao() {
		return orderDao;
	}
	
	public OrderLineDao getOrderLineDao() {
		return orderlineDao;
	}
	
	
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	public void setCurrentUser(User user) {
		currentUser = user;
	}
	
	public void setDBL( ArrayList <Book> dataBooks) {
		this.dataBooks = dataBooks;
	}
	
		
	public ArrayList <Book> getDBL() {
		return this.dataBooks;
	}
}
