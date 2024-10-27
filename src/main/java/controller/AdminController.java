package controller;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Book;
import model.Model;

public class AdminController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private MenuItem viewProfile; // Corresponds to the Menu item "viewProfile" in HomeView.fxml
	@FXML
	private MenuItem updateProfile; // // Corresponds to the Menu item "updateProfile" in HomeView.fxml
	@FXML
	private TableColumn<Book, String> titleCol;
	@FXML
	private TableColumn<Book, String> authorCol;
	@FXML
	private TableColumn<Book, Integer> priceCol;
	@FXML
	private TableColumn<Book, Integer> copiesCol;
	@FXML
	private TableColumn<Book, Integer> quantityCol;
	@FXML
	private TableView<Book> bookView;
	@FXML
	private Label adminLabel;
	@FXML
	private Label errorLabel;
	@FXML
	private Button logoutButton;
	@FXML
	private Button updateStockButton;
	@FXML
	private TextField stockTextField;
	private ObservableList <Book> dataBooks;
	
	public AdminController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	// Add your code to complete the functionality of the program
	
	@FXML
	public void initialize() {
	try {
    		
    		
    		

	        titleCol.setCellValueFactory(
	                new PropertyValueFactory<Book, String>("booktitle")
	        );
	        authorCol.setCellValueFactory(
	                new PropertyValueFactory<Book, String>("author")
	        );
	        
	        priceCol.setCellValueFactory(
	                new PropertyValueFactory<Book, Integer>("price")
	        );
	        copiesCol.setCellValueFactory(
	                new PropertyValueFactory<Book, Integer>("sold")
	        );
	        quantityCol.setCellValueFactory(
	                new PropertyValueFactory<Book, Integer>("copies")
	        );
	        
	        
	        dataBooks = FXCollections.observableArrayList();
	       
	    
	       
	        dataBooks.setAll(model.getBookDao().getBookList());
	      
	       
	        bookView.setItems(dataBooks);
	     
	        
	        
	       
	        
		}catch (SQLException ex) {   
	        }
	logoutButton.setOnAction(event -> {
		stage.close();
		parentStage.show();
	});
	updateStockButton.setOnAction(event -> {
		try {
			model.getBookDao().updateQuantityBook(bookView.getSelectionModel().getSelectedItem().getbooktitle(), Integer.parseInt(stockTextField.getText()));
			 dataBooks.setAll(model.getBookDao().getBookList());
		     bookView.setItems(dataBooks);		
		     errorLabel.setText("Stock Updated");
		     errorLabel.setTextFill(Color.GREEN);
			
			
		}catch (SQLException e) {
			errorLabel.setText(e.getMessage());
			errorLabel.setTextFill(Color.RED);
		
		}catch (NumberFormatException nfe ) {
			errorLabel.setText("ERROR input only numbers");
			errorLabel.setTextFill(Color.RED);
			
		}catch (NoSuchElementException exception ) {
			errorLabel.setText("ERROR input only numbers");
			errorLabel.setTextFill(Color.RED);
			
		}catch (Exception exception ) {
			errorLabel.setText("ERROR Select a row to update stock");
			errorLabel.setTextFill(Color.RED);
			
		
	}});
	}
	

	
	
	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 450);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Admin Home");
		stage.show();
		adminLabel.setText("Welcome " + model.getCurrentUser().getUsername());
	}
}
