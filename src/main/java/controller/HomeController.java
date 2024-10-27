package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import model.Model;

public class HomeController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private MenuItem viewProfile; // Corresponds to the Menu item "viewProfile" in HomeView.fxml
	@FXML
	private MenuItem updateProfile; // // Corresponds to the Menu item "updateProfile" in HomeView.fxml
	@FXML
	private MenuItem logOut;
	@FXML
	private MenuItem viewCart;
	@FXML
	private MenuItem viewCheckout;
	@FXML
	private MenuItem addCart;
	@FXML
	private MenuItem viewallbooks;
	@FXML
	private MenuItem viewOrders;
	@FXML
	private TextArea dashboard;
	@FXML
	private Button logOutButton;
	@FXML
	private TableColumn<Book, String> homeTableCol;
	@FXML
	private TableView<Book> homeTableView;
	private ObservableList <Book> dataBooks;
	
	
	public HomeController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	
		
	}
	
	@FXML
	public void initialize() {
		
        homeTableCol.setCellValueFactory(
	        new PropertyValueFactory<Book, String>("booktitle")
	        );
	        
	        
	    dataBooks = FXCollections.observableArrayList();
	       
	    
	       
	     
	
	
	
	    updateProfile.setOnAction(event -> {
		    try {
			    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditProfileView.fxml"));
			    EditProfileController EditProfileController =  new EditProfileController(stage, model);
			    loader.setController(EditProfileController);
			    VBox root = loader.load();
			
			    EditProfileController.showStage(root);
			
						
			    stage.close();
		    }catch (IOException e) {	
			
		 }});
	    logOut.setOnAction(event -> {
		    stage.close();
		    parentStage.show();
	    });
	    logOutButton.setOnAction(event -> {
		    stage.close();
		    parentStage.show();
	    });
	
	    viewCart.setOnAction(event -> {
	  	    try {
			   FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CartView.fxml"));
			   CartController CartController =  new CartController(stage, model);
			   loader.setController(CartController);
			   VBox root = loader.load();
			
			   CartController.showStage(root);
			
			
			
			   stage.close();
		    } catch (IOException e) {
			
			
			
	    }});
	
	    addCart.setOnAction(event -> {
		    try {
			    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CartView.fxml"));
			    CartController CartController =  new CartController(stage, model);
			    loader.setController(CartController);
			    VBox root = loader.load();
			
			    CartController.showStage(root);
			
			
			
			    stage.close();
		   }catch (IOException e) {			
			
		    }});
	    viewCheckout.setOnAction(event -> {
		    try {
			    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CheckoutView.fxml"));
			    CheckoutController CheckoutController =  new CheckoutController(stage, model);
			    loader.setController(CheckoutController);
			    VBox root = loader.load();
			    CheckoutController.showStage(root);
			    stage.close();
		    } catch (IOException e) {

		    }});
	
	     viewallbooks.setOnAction(event -> {
		     try {
			    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BooksView.fxml"));
			
			    BooksController BooksController =  new BooksController(stage, model);

			    loader.setController(BooksController);
			    VBox root = loader.load();
			
			    BooksController.showStage(root);
			
			
			
			    stage.close();
		     } catch (IOException e) {
			
		    }});
	     viewOrders.setOnAction(event -> {
		     try {
			    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OrderView.fxml"));
			    OrderController OrderController =  new OrderController(stage, model);

			    loader.setController(OrderController);
			    VBox root = loader.load();
			
			    OrderController.showStage(root);
			    stage.close();
		     } catch (IOException e) {
		  	    //message.setText(e.getMessage());	
			
		    }});
	     stage.setOnShown(event -> {
		     try {
			     dataBooks.setAll(model.getBookDao().getBookList());
			     Comparator<Book> bookComparator = Comparator.comparing(Book::getsold);
			     Collections.sort(dataBooks, bookComparator);
			     dataBooks.remove(0);
			     dataBooks.remove(0);
			     dataBooks.remove(0);
			     dataBooks.remove(0);
			     Collections.reverse(dataBooks);
			
			
		
		
			     homeTableView.setItems(dataBooks);;
			
		     } catch (SQLException e) {

			    e.printStackTrace();
		
		
		     }});
	
}


	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 450);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Home");
		stage.show();
		dashboard.setText("Welcome " + model.getCurrentUser().getUsername() +" to TheReadingRoom!"
				+ "\n"
				+ "Here you can Buy a variety of books"
				+ "\n"
				+ "Thank you for choosing us! :)"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "Use the top left menu to navigate the"
				+ "\n"
				+ "application!"
				);
	
       

	
	
	}
}
