package controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Cart;
import model.Model;
import model.Order;
import model.OrderLine;


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
	@FXML
	private Button exportButton;
	@FXML
	private Button exportAllButton;
	private ObservableList<Order> dataOrder;
	private ObservableList<OrderLine> dataOrderLine;
	private BufferedReader br;

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
	        dataOrderLine = FXCollections.observableArrayList();
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
	       
	        orderTableView.setRowFactory( tv -> {
	            TableRow<Order> row = new TableRow<>();
	            row.setOnMouseClicked(event -> {
	                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
	                    Order rowData = row.getItem();
	                 
	                    try {
	            			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OrderDetailView.fxml"));
	            			
	            			OrderDetailController OrderDetailController =  new OrderDetailController(stage, model,rowData.getorderno());

	            			loader.setController(OrderDetailController);
	            			VBox root = loader.load();
	            			
	            			OrderDetailController.showStage(root);
	            			stage.close();
	            		} catch (IOException e) {
	            			//message.setText(e.getMessage());
	 
	            			
	            		}
	                }
	            });
	            return row ;
	        });
	  
	    exportButton.setOnAction(event -> {
	    	orderTableView.getSelectionModel().getSelectedItems();
	    
	    	FileChooser fil_chooser = new FileChooser();
	    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
	    	fil_chooser.getExtensionFilters().add(extFilter);
	    	File file = fil_chooser.showSaveDialog(stage);
	    	if (file != null) {
	    		try {
	    			ConvertCSVcreatefileSelected(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
	    exportAllButton.setOnAction(event -> {
	    	orderTableView.getSelectionModel().getSelectedItems();
	    
	    	FileChooser fil_chooser = new FileChooser();
	    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
	    	fil_chooser.getExtensionFilters().add(extFilter);
	    	File file = fil_chooser.showSaveDialog(stage);
	    	if (file != null) {
	    		try {
	    			ConvertCSVcreatefileAll(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
	    	
             
         
   
	
    	
    	viewProfile.setOnAction(event -> {
    		stage.close();
    		parentStage.show();
    	});
    	
    
    }
             
   
	
	
	public String convertToCSV(String[] data) {
	    return Stream.of(data)
	      .map(this::escapeSpecialCharacters)
	      .collect(Collectors.joining(","));
	}
	
	
	public void ConvertCSVcreatefileSelected(File file) throws IOException {
		List<String[]> dataLines = new ArrayList<>();
		dataLines.add(new String[] 
				  { "Orderno:","BookTitle", "Quantity", "Price per item",
					  "total price"});
		orderTableView.getSelectionModel().getSelectedItems().forEach((Order order) -> {
				try {
					dataOrderLine.setAll(model.getOrderLineDao().getOrderLineList(order.getorderno()));
					dataOrderLine.forEach((OrderLine orderline) -> {
						dataLines.add(new String[] 
								  {Integer.toString(order.getorderno()), orderline.getbooktitle(), Integer.toString(orderline.getcopies()), Integer.toString(orderline.getprice()),
									  Integer.toString((orderline.getcopies()*orderline.getprice()))});
						
					});
					
				}catch (SQLException e) {
					e.printStackTrace();
				}

		});
		try (PrintWriter pw = new PrintWriter(file)) {
	        dataLines.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    }
	}
	
	
	public void ConvertCSVcreatefileAll(File file) throws IOException {
		List<String[]> dataLines = new ArrayList<>();
		dataLines.add(new String[] 
				  { "Orderno:","BookTitle", "Quantity", "Price per item",
					  "total price"});
		dataOrder.forEach((Order order) -> {
				try {
					dataOrderLine.setAll(model.getOrderLineDao().getOrderLineList(order.getorderno()));
					dataOrderLine.forEach((OrderLine orderline) -> {
						dataLines.add(new String[] 
								  {Integer.toString(order.getorderno()), orderline.getbooktitle(), Integer.toString(orderline.getcopies()), Integer.toString(orderline.getprice()),
									  Integer.toString((orderline.getcopies()*orderline.getprice()))});
						
					});
					
				}catch (SQLException e) {
					e.printStackTrace();
				}

		});
		try (PrintWriter pw = new PrintWriter(file)) {
	        dataLines.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    }
	}

	
	public String escapeSpecialCharacters(String data) {
	    if (data == null) {
	        throw new IllegalArgumentException("Input data cannot be null");
	    }
	    String escapedData = data.replaceAll("\\R", " ");
	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
	        data = data.replace("\"", "\"\"");
	        escapedData = "\"" + data + "\"";
	    }
	    return escapedData;
	}
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 450);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Orders");
		stage.show();
	}
}
