package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {

	public  SimpleIntegerProperty orderno = new SimpleIntegerProperty();
	public  SimpleStringProperty username = new SimpleStringProperty();
    public  SimpleStringProperty date = new SimpleStringProperty();
    public  SimpleIntegerProperty price = new SimpleIntegerProperty();
    public  SimpleIntegerProperty rowid = new SimpleIntegerProperty();

	public Order() {
	}
	
	public Order(SimpleIntegerProperty orderno,SimpleStringProperty username, SimpleStringProperty date, SimpleIntegerProperty price) { 
		this.orderno = orderno;
		this.username = username;
		this.date = date;
		this.price = price;
	
	}
	
	public Order(int orderno, String username,String date, int price) {
		this.orderno.set(orderno); 
		this.username.set(username);
		this.date.set(date);
		this.price.set(price);
	
		
	}
	
	public Order(int orderno) {
		this.orderno.set(orderno);
	}
	

	public int getorderno() {
		return orderno.get();
	}

	public String getusername() {
		return username.get();
	}
	public SimpleIntegerProperty ordernoProperty() {
		return orderno;
	}
	public SimpleStringProperty usernameProperty() {
		return username;
	}
	public SimpleStringProperty dateProperty() {
		return date;
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
	
	
	public String getdate() {
		return date.get();
	}
	
	public int getprice() {
		return price.get();
	}
	

	
	public void setorderno(int orderno) {
		this.orderno.set(orderno);
	}

	public void setusername(String username) {
		this.username.set(username);
	}
	public void setprice(int price) {
		this.price.set(price);
	}
	public void setdate(String date) {
		this.date.set(date);
	}

	

	}






