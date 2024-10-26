package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderLine {

	public  SimpleIntegerProperty orderno = new SimpleIntegerProperty();
	public  SimpleStringProperty booktitle = new SimpleStringProperty();
	public  SimpleStringProperty username = new SimpleStringProperty();
    public  SimpleIntegerProperty copies = new SimpleIntegerProperty();
    public  SimpleIntegerProperty price = new SimpleIntegerProperty();
    public  SimpleIntegerProperty rowid = new SimpleIntegerProperty();

	public OrderLine() {
	}
	
	public OrderLine(SimpleIntegerProperty orderno,SimpleStringProperty booktitle, SimpleIntegerProperty copies, SimpleIntegerProperty price) { 
		this.orderno = orderno;
		this.booktitle = booktitle;
		this.copies =copies;
		this.price =price;
	
	}
	
	public OrderLine(int orderno, String booktitle,Integer copies, int price) {
		this.orderno.set(orderno); 
		this.booktitle.set(booktitle);
		this.copies.set(copies);
		this.price.set(price);
	
		
	}
	
	public OrderLine(int orderno) {
		this.orderno.set(orderno);
	}
	

	public int getorderno() {
		return orderno.get();
	}
	public String getusername() {
		return username.get();
	}

	public String getbooktitle() {
		return booktitle.get();
	}
	public SimpleIntegerProperty ordernoProperty() {
		return orderno;
	}
	public SimpleStringProperty booktitleProperty() {
		return booktitle;
	}
	public SimpleIntegerProperty copies() {
		return copies;
	}
	public SimpleIntegerProperty priceProperty() {
		return price;
	}
	public SimpleStringProperty usernameProperty() {
		return username;
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
	

	
	public void setorderno(int orderno) {
		this.orderno.set(orderno);
	}

	public void setbooktitle(String booktitle) {
		this.booktitle.set(booktitle);
	}
	public void setprice(int price) {
		this.price.set(price);
	}
	public void setusername(String username) {
		this.username.set(username);
	}
	public void setcopies(int copies) {
		this.copies.set(copies);
	}

	

	}






