package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.ArrayList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Book;
import model.Model;
import model.Order;


public class OrderController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private MenuItem viewProfile; // Corresponds to the Menu item "viewProfile" in HomeView.fxml
	@FXML
	private MenuItem updateProfile; // // Corresponds to the Menu item "updateProfile" in HomeView.fxml
	@FXML
	private TableColumn<Order, Integer> ordernocol;
	@FXML
	private TableColumn<Order, String> datecol;
	@FXML
	private TableColumn<Order, Integer> pricecol;
	@FXML
	private TableView<Order> bookView;
	
	private ObservableList<Order> dataCart;
	
	public OrderController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	
	
	// Add your code to complete the functionality of the program
	
	@FXML
	public void initialize(){
		
    	
    	
    		
    		
    		

    		ordernocol.setCellValueFactory(
	                new PropertyValueFactory<Order, Integer>("orderno")
	        );
	        datecol.setCellValueFactory(
	                new PropertyValueFactory<Order, String>("date")
	        );
	        pricecol.setCellValueFactory(
	                new PropertyValueFactory<Order, Integer>("price")
	        );
	        
	        
	        dataCart = FXCollections.observableArrayList();
	       
	    
	       
	        try {
	        	dataCart.addAll(model.getOrderDao().getOrderList(model.getCurrentUser().getUsername()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	        bookView.setItems(dataCart);
	     
	        
	        
	       
	        
	
    	
    	viewProfile.setOnAction(event -> {
    		stage.close();
    		parentStage.show();
    	});
    	
    
    }
	
	
	
	 
	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("books");
		stage.show();
	}
}
