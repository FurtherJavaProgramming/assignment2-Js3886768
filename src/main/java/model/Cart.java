package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cart {

	
	public  SimpleStringProperty username = new SimpleStringProperty();
    public  SimpleStringProperty booktitle = new SimpleStringProperty();
    public  SimpleIntegerProperty copies = new SimpleIntegerProperty();
    public  SimpleIntegerProperty price = new SimpleIntegerProperty();
    public  SimpleIntegerProperty sold = new SimpleIntegerProperty();
    public  SimpleIntegerProperty rowid = new SimpleIntegerProperty();

	public Cart() {
	}
	
	public Cart(SimpleStringProperty booktitle, SimpleStringProperty username, SimpleIntegerProperty copies, SimpleIntegerProperty price, SimpleIntegerProperty sold) {
		this.booktitle = booktitle;
		this.username = username;
		this.copies = copies;
		this.price =price;
		this.sold =sold;
	}
	
	public Cart(String booktitle, String username, int copies, int price,int sold) {
		this.booktitle.set(booktitle); 
		this.username.set(username);
		this.copies.set(copies);
		this.price.set(price);
		this.sold.set(sold);
		
	}
	
	public Cart(String booktitle) {
		this.booktitle.set(booktitle);
	}
	

	public String getbooktitle() {
		return booktitle.get();
	}

	public String getusername() {
		return username.get();
	}
	public SimpleStringProperty booktitleProperty() {
		return booktitle;
	}
	public SimpleStringProperty usernameProperty() {
		return username;
	}
	public SimpleIntegerProperty copiesProperty() {
		return copies;
	}
	public SimpleIntegerProperty priceProperty() {
		return price;
	}
	
	public SimpleIntegerProperty rowidProperty() {
		return rowid;
	}
	
	public int getrowid() {
		return rowid.get();
	}
	
	public void setrowid(int rowid) {
		this.rowid.set(rowid);
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

	public void setusername(String username) {
		this.username.set(username);
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






