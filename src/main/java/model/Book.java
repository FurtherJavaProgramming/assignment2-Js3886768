package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {

	
	public  SimpleStringProperty author = new SimpleStringProperty();
    public  SimpleStringProperty booktitle = new SimpleStringProperty();
    public  SimpleIntegerProperty copies = new SimpleIntegerProperty();
    public  SimpleIntegerProperty price = new SimpleIntegerProperty();
    public  SimpleIntegerProperty sold = new SimpleIntegerProperty();

	public Book() {
	}

	public Book(SimpleStringProperty booktitle, SimpleStringProperty author, SimpleIntegerProperty copies, SimpleIntegerProperty price, SimpleIntegerProperty sold) {
		this.booktitle = booktitle;
		this.author = author;
		this.copies = copies;
		this.price =price;
		this.sold =sold;
	}
	
	public Book(String booktitle, String author, int copies, int price,int sold) {
		this.booktitle.set(booktitle); 
		this.author.set(author);
		this.copies.set(copies);
		this.price.set(price);
		this.sold.set(sold);
		
	}
	

	public String getbooktitle() {
		return booktitle.get();
	}

	public String getauthor() {
		return author.get();
	}
	public SimpleStringProperty booktitleProperty() {
		return booktitle;
	}
	public SimpleStringProperty authorProperty() {
		return author;
	}
	public SimpleIntegerProperty copiesProperty() {
		return copies;
	}
	public SimpleIntegerProperty priceProperty() {
		return price;
	}
	
	
	public int getcopies() {
		return copies.get();
	}
	
	public int getprice() {
		return price.get();
	}
	
	public int getsold() {
		return sold.get();
	}
	
	public void setbooktitle(String booktitle) {
		this.booktitle.set(booktitle);
	}

	public void setauthor(String author) {
		this.author.set(author);
	}
	public void setprice(int price) {
		this.price.set(price);
	}
	public void setcopies(int copies) {
		this.copies.set(copies);
	}

	public void setsold(int sold) {
		this.sold.set(sold);
		
		
	}

	}





	//public void setUsername(String username) {
	//	this.username = username;
	//}

	//public void setPassword(String password) {
	//	this.password = password;
	//}

