package model;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.BookDao;
import dao.BookDaoImpl;
import dao.CartDao;
import dao.CartDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import javafx.collections.ObservableList;

public class Model {
	private UserDao userDao;
	private CartDao cartDao;
	private User currentUser; 
	private Book book;
	private BookDao bookDao;
	private ArrayList <Book> dataBooks = new ArrayList<Book>() ;

	public Model() {
		userDao = new UserDaoImpl();
		bookDao = new BookDaoImpl();
		cartDao = new CartDaoImpl();
	}
	
	public void setup() throws SQLException {
		userDao.setup();
		bookDao.setup();
		cartDao.setup();
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
