package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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


public class BooksController implements Initializable {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private MenuItem viewProfile; // Corresponds to the Menu item "viewProfile" in HomeView.fxml
	@FXML
	private MenuItem updateProfile; // // Corresponds to the Menu item "updateProfile" in HomeView.fxml
	@FXML
	private TableColumn<Book, String> titlecol;
	@FXML
	private TableColumn<Book, String> authorcol;
	@FXML
	private TableColumn<Book, Integer> pricecol;
	@FXML
	private TableColumn<Book, Integer> quantitycol;
	@FXML
	private TableView<Book> bookView;
	private ObservableList <Book> dataBooks;
	
	public BooksController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	
	
	// Add your code to complete the functionality of the program
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
    	
    	try {
    		
    		
    		

	        titlecol.setCellValueFactory(
	                new PropertyValueFactory<Book, String>("booktitle")
	        );
	        authorcol.setCellValueFactory(
	                new PropertyValueFactory<Book, String>("author")
	        );
	        
	        pricecol.setCellValueFactory(
	                new PropertyValueFactory<Book, Integer>("copies")
	        );
	        quantitycol.setCellValueFactory(
	                new PropertyValueFactory<Book, Integer>("price")
	        );
	        
	        
	        dataBooks = FXCollections.observableArrayList();
	       
	    
	       
	        dataBooks.addAll(model.getBookDao().getBookList());
	      
	       
	        bookView.setItems(dataBooks);
	     
	        
	        
	       
	        
		}catch (SQLException ex) {
	            
	        }
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
