package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cart;
import model.Model;

public class CheckoutController {
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
	private ImageView imageView;
	@FXML
	private Image imageVieww;
	@FXML
	private TableColumn<Cart, String> cartTitleCol;
	@FXML
	private TableColumn<Cart, Integer> cartQuantityCol;
	@FXML
	private TableColumn<Cart, Integer> cartPriceCol;
	@FXML
	private TableView<Cart> cartTableView;
	@FXML
	private Label priceTotalLabel;
	@FXML
	private Button closeButton;
	@FXML
	private Button purchaseButton;
	@FXML
	private TextField ccTextField;
	@FXML
	private TextField mmTextField;
	@FXML
	private TextField yyTextField;
	@FXML
	private TextField cvvTextField;
	
	
	private ObservableList <Cart> dataCart;
	
	public CheckoutController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	// Add your code to complete the functionality of the program
	
	@FXML
	public void initialize() {
		
	     	cartTitleCol.setCellValueFactory(
                    new PropertyValueFactory<Cart, String>("booktitle")
            );
            cartQuantityCol.setCellValueFactory(
                    new PropertyValueFactory<Cart, Integer>("copies")
            );
        
            cartPriceCol.setCellValueFactory(
                    new PropertyValueFactory<Cart, Integer>("price")
            );
    
        
            dataCart = FXCollections.observableArrayList();
        try {
       
    
       
            dataCart.setAll(model.getCartDao().getCartList(model.getCurrentUser().getUsername()));
      
            priceTotalLabel.setText("0");
            cartTableView.setItems(dataCart);
            dataCart.forEach((Cart cart) -> { 
                priceTotalLabel.setText(Integer.toString((cart.getprice()*cart.getcopies())+Integer.parseInt(priceTotalLabel.getText())));
            });
       
     
        
        
       
        
        }catch (SQLException ex) {
            
           }
        logOut.setOnAction(event -> {
		    stage.close();
		    parentStage.show();
	    });
        closeButton.setOnAction(event -> {
		    stage.close();
		    parentStage.show();
	    });
    
	
	
	}
	
    
	
		
	
	
	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 450);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Home");
		stage.show();
		
		
	}
	

}
