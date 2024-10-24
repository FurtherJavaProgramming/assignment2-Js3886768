package model;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.BookDao;
import dao.BookDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import javafx.collections.ObservableList;

public class Model {
	private UserDao userDao;
	private User currentUser; 
	private Book book;
	private BookDao bookDao;
	private ArrayList <Book> dataBooks = new ArrayList<Book>() ;

	public Model() {
		userDao = new UserDaoImpl();
		bookDao = new BookDaoImpl();
	}
	
	public void setup() throws SQLException {
		userDao.setup();
		bookDao.setup();
	}
	public UserDao getUserDao() {
		return userDao;
	}
	
	public BookDao getBookDao() {
		return bookDao;
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
