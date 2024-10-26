package controller;


import java.sql.SQLException;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Cart;
import model.Model;
import model.Order;


public class OrderController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private MenuItem viewProfile; // Corresponds to the Menu item "viewProfile" in HomeView.fxml
	@FXML
	
	private TableColumn<Order, Integer> ordernoCol;
	@FXML
	private TableColumn<Order, Integer> priceCol;
	@FXML
	private TableColumn<Order, String> dateCol;
	
	@FXML
	private TableView<Order> orderTableView;
	private ObservableList<Order> dataOrder;
	
	public OrderController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	
	
	// Add your code to complete the functionality of the program
	
	@FXML
	public void initialize(){
		
    
    		ordernoCol.setCellValueFactory(
    				new PropertyValueFactory<Order,Integer>("orderno")
    				
    	    );
    		priceCol.setCellValueFactory(
 	                new PropertyValueFactory<Order,Integer>("price")
 	        );
 	        
	        dateCol.setCellValueFactory(
	                new PropertyValueFactory<Order,String>("date")
	        );
	      
	        
	        dataOrder = FXCollections.observableArrayList();
	        orderTableView.getSelectionModel().setSelectionMode(
	        	    SelectionMode.MULTIPLE
	        	);
	       
	    
	       
	      
	        	try {
					dataOrder.setAll(model.getOrderDao().getOrderList(model.getCurrentUser().getUsername()));
					orderTableView.setItems(dataOrder);
					
			        
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        
			
	    
	     
	        
	        
	       
	       
	
    	
    	viewProfile.setOnAction(event -> {
    		stage.close();
    		parentStage.show();
    	});
    	
    
    }
	
	
	
	 
	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 450);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("books");
		stage.show();
	}
}
