package controller;


import java.sql.SQLException;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;
import model.OrderLine;


public class OrderDetailController {
	private Model model;
	private Stage stage;
	private int orderno;
	private Stage parentStage;
	@FXML
	private Label orderNumberLabel;
	@FXML
	private Label totalPriceLabel;
	@FXML
	private Button returnButton;
	@FXML
	private TableColumn<OrderLine, Integer> quantityCol;
	@FXML
	private TableColumn<OrderLine, Integer> priceCol;
	@FXML
	private TableColumn<OrderLine, String> booktitleCol;
	@FXML
	private TableView<OrderLine> orderDetailsTableView;
	private ObservableList<OrderLine> dataOrder;
	
	public OrderDetailController(Stage parentStage, Model model, int orderno) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
		this.orderno = orderno;
	}
	
	
	
	// Add your code to complete the functionality of the program
	
	@FXML
	public void initialize(){
		
    
    		quantityCol.setCellValueFactory(
    				new PropertyValueFactory<OrderLine,Integer>("copies")
    				
    	    );
    		priceCol.setCellValueFactory(
 	                new PropertyValueFactory<OrderLine,Integer>("price")
 	        );
 	        
	        booktitleCol.setCellValueFactory(
	                new PropertyValueFactory<OrderLine,String>("booktitle")
	        );
	      
	        
	        dataOrder = FXCollections.observableArrayList();
	        totalPriceLabel.setText("0");
	       
	    
	       
	      
	        	try {
					dataOrder.setAll(model.getOrderLineDao().getOrderLineList(orderno));
					orderDetailsTableView.setItems(dataOrder);
					orderNumberLabel.setText(Integer.toString(orderno));
					dataOrder.forEach((OrderLine orderline) ->{
						totalPriceLabel.setText(Integer.toString((orderline.getprice()*orderline.getcopies())+Integer.parseInt(totalPriceLabel.getText())));
		            });
				
					
			        
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	
	        	
	       returnButton.setOnAction(event -> {
	        	stage.close();
	        	parentStage.show();
	        });
	       
	        
	       
	        	
	        
	    
	        	
	        	
	        
			
	    
	     
	        
	        
	       
	       
	
    	
   
    	
    
    }
	
	
	
	 
	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 450);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Order Details");
		stage.show();
	}
}
