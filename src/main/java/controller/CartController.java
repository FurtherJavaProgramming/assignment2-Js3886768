package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Book;
import model.Cart;
import model.Model;


public class CartController {
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
	private ComboBox<String> choiceComboBox;
	@FXML
	private Label priceLabel;
	@FXML
	private Label priceTotalLabel;
	@FXML
	private Label stockLabel;
	@FXML
	private Label errorLabel;
	@FXML
	private Button removeButton;
	@FXML 
	private Button addToCartButton;
	@FXML 
	private Button updateQuantityButton;
	@FXML 
	private Button checkoutButton;
	@FXML 
	private TextField quantityTextField;
	@FXML
	private TextField updateQuantityTextField;
	@FXML
	private TableColumn<Cart, String> cartTitleCol;
	@FXML
	private TableColumn<Cart, Integer> cartQuantityCol;
	@FXML
	private TableColumn<Cart, Integer> cartPriceCol;
	@FXML
	private TableView<Cart> cartTableView;
	
	
	private ObservableList <Cart> dataCart;
	private ObservableList <Cart> selectedItems;
	
	public CartController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
		
	}
	
	// Add your code to complete the functionality of the program
	
	
	@FXML
	public void initialize() {
		    priceTotalLabel.setText("0");
    		
    		
    		

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
		
		
		try {
			choiceComboBox.setItems(model.getBookDao().getBookTitleList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		choiceComboBox.setOnAction(event -> {
			Book bk = new Book();
			try {
				bk = (model.getBookDao().getBook(choiceComboBox.getValue().toString()));
				priceLabel.setText(Integer.toString(bk.getprice()));
				stockLabel.setText(Integer.toString(bk.getcopies()));
			} catch (SQLException e) {
				
				
				e.printStackTrace();
			}
			
			

		});
		
      
		addToCartButton.setOnAction(event -> {
			try {
				Book bk = new Book();
				Cart ct = new Cart();
				bk = (model.getBookDao().getBook(choiceComboBox.getValue().toString()));
				ct =(model.getCartDao().getCart(bk.getbooktitle(), model.getCurrentUser().getUsername()));
			
				if (bk.getcopies() >= Integer.parseInt(quantityTextField.getText())){
					if(ct.getbooktitle().equals(bk.getbooktitle())){ 
						if (bk.getcopies() >= (Integer.parseInt(quantityTextField.getText()))+ct.getcopies()){
						    model.getCartDao().addQuantityCart(ct.getrowid(),Integer.parseInt(quantityTextField.getText()));
						    dataCart.setAll(model.getCartDao().getCartList(model.getCurrentUser().getUsername()));
				            cartTableView.setItems(dataCart);
				            priceTotalLabel.setText("0");
					        dataCart.forEach((Cart cart) -> { 
					             priceTotalLabel.setText(Integer.toString((cart.getprice()*cart.getcopies())+Integer.parseInt(priceTotalLabel.getText())));
					        });
					   }else {
						    errorLabel.setText("Error : Not enough Avaliable stock");
						    errorLabel.setTextFill(Color.RED);
					   }
						
					}else {
					
					    model.getCartDao().createCart(choiceComboBox.getValue().toString(), model.getCurrentUser().getUsername(), Integer.parseInt(quantityTextField.getText()),bk.getprice() , 0);
					    dataCart.setAll(model.getCartDao().getCartList(model.getCurrentUser().getUsername()));
			            cartTableView.setItems(dataCart);
			            priceTotalLabel.setText("0");
			            dataCart.forEach((Cart cart) -> { 
			                 priceTotalLabel.setText(Integer.toString((cart.getprice()*cart.getcopies())+Integer.parseInt(priceTotalLabel.getText())));
			            });
					    }
				}else {
					errorLabel.setText("Error : Not enough Avaliable stock");
					errorLabel.setTextFill(Color.RED);
				}
				
			} catch (SQLException e) {
				errorLabel.setText(e.getMessage());
				errorLabel.setTextFill(Color.RED);
			}
			catch (NumberFormatException nfe ) {
				errorLabel.setText("ERROR input only numbers");
				errorLabel.setTextFill(Color.RED);
				
			}catch (Exception Exception ) {
				errorLabel.setText("ERROR : check selection ");
				errorLabel.setTextFill(Color.RED);
				
			}
		});
		
		removeButton.setOnAction(event -> {
			cartTableView.getSelectionModel();
			TableViewSelectionModel<Cart> selectionModel = 
				    cartTableView.getSelectionModel();
			ObservableList<Cart> selectedItems = 
				    selectionModel.getSelectedItems();
			Cart ct = new Cart();
			ct = selectedItems.getFirst();
			try {
				model.getCartDao().removeCart(ct.getrowid());
				dataCart.setAll(model.getCartDao().getCartList(model.getCurrentUser().getUsername()));
		        cartTableView.setItems(dataCart);
		        priceTotalLabel.setText("0");
		        dataCart.forEach((Cart cart) -> { 
		             priceTotalLabel.setText(Integer.toString((cart.getprice()*cart.getcopies())+Integer.parseInt(priceTotalLabel.getText())));
		        });
			} catch (SQLException e) {
				errorLabel.setText(e.getMessage());
				errorLabel.setTextFill(Color.RED);
			}
		});
		updateQuantityButton.setOnAction(event -> {
			cartTableView.getSelectionModel();
			TableViewSelectionModel<Cart> selectionModel = 
				    cartTableView.getSelectionModel();
			ObservableList<Cart> selectedItems = 
				    selectionModel.getSelectedItems();
			try {
			    Cart ct = new Cart();
			    ct = selectedItems.getFirst();
			    Book bk = new Book();
				bk = model.getBookDao().getBook(ct.getbooktitle());
				if (bk.getcopies() >= (Integer.parseInt(updateQuantityTextField.getText()))+ct.getcopies()){
				    model.getCartDao().updateQuantityCart(ct.getrowid(),Integer.parseInt(updateQuantityTextField.getText()));
				    dataCart.setAll(model.getCartDao().getCartList(model.getCurrentUser().getUsername()));
		            cartTableView.setItems(dataCart);
		            priceTotalLabel.setText("0");
			        dataCart.forEach((Cart cart) -> { 
			             priceTotalLabel.setText(Integer.toString((cart.getprice()*cart.getcopies())+Integer.parseInt(priceTotalLabel.getText())));
			        });
			   }else {
				    errorLabel.setText("Error : Not enough Avaliable stock");
				    errorLabel.setTextFill(Color.RED);
			   }
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
				errorLabel.setText("ERROR input oly numbers");
				errorLabel.setTextFill(Color.RED);
				
			}
		});
		checkoutButton.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CheckoutView.fxml"));
				
				
				// Customize controller instance
				CheckoutController CheckoutController =  new CheckoutController(stage, model);
				//loader.setBuilderFactory(new JavaFXBuilderFactory(false));
				//loader.setResources(null)

				loader.setController(CheckoutController);
				
				VBox root = loader.load();
				
				CheckoutController.showStage(root);
				
				
				
				stage.close();
			} catch (IOException e) {
				//message.setText(e.getMessage());
				
				
			}});
	
	}
	
	
	
	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 450);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Home");
		stage.show();
		
	}
}
