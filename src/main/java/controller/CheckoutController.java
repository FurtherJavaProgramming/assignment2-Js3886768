package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
	private Label ccErrorLabel;
	@FXML
	private Label dateErrorLabel;
	@FXML
	private Label cvvErrorLabel;
	@FXML
	private Button closeButton;
	@FXML
	private Button purchaseButton;
	@FXML
	private PasswordField ccNumberTextField;
	@FXML
	private TextField mmTextField;
	@FXML
	private TextField yyTextField;
	@FXML
	private PasswordField cvvTextField;
	
	
	private ObservableList <Cart> dataCart;
	private boolean mark;
	
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
            
        
        purchaseButton.setOnAction(event -> {
        	ccErrorLabel.setText("");
        	dateErrorLabel.setText("");
        	cvvErrorLabel.setText("");
        	if(validateFields()==true) {
          	    LocalDateTime myObj = LocalDateTime.now();               
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myObj.format(myFormatObj);
                
                if (dataCart.isEmpty()==false) {
                	if (validatequantityavaliable()==true) {
                        try {
            	            int random = uniqueRandom();
				            model.getOrderDao().createOrder(random, model.getCurrentUser().getUsername(), formattedDate, Integer.parseInt(priceTotalLabel.getText()));
				            dataCart.forEach((Cart cart) -> { 
		                        try {
						            model.getOrderLineDao().createOrderLine(random, cart.getbooktitle(), cart.getcopies(),cart.getprice()) ;
						            model.getBookDao().setsold(cart.getbooktitle(), cart.getcopies());
						            model.getCartDao().removeCart(cart.getrowid());
					            } catch (SQLException e) {
						 
						        e.printStackTrace();
					        }
		                    });
				            dataCart.setAll(model.getCartDao().getCartList(model.getCurrentUser().getUsername()));
	                        priceTotalLabel.setText("0");
	                        cartTableView.setItems(dataCart);
	                        dataCart.forEach((Cart cart) -> { 
	                            priceTotalLabel.setText(Integer.toString((cart.getprice()*cart.getcopies())+Integer.parseInt(priceTotalLabel.getText())));
	                        });
	                        ccErrorLabel.setText("ORDER SUCCESSFULLY PLACED");
	                    	ccErrorLabel.setTextFill(Color.GREEN);
			              } catch (SQLException e) {
				
				             e.printStackTrace();
		   	              }
                	    }else {
                		ccErrorLabel.setText("ERROR not enough stock to complete order");
                    	ccErrorLabel.setTextFill(Color.RED);
                	}
                	}else {
                	ccErrorLabel.setText("Cart is Empty");
                	ccErrorLabel.setTextFill(Color.RED);
                }
            
        	}
        	});
        logOut.setOnAction(event -> {
		    stage.close();
		    parentStage.show();
	    });
        closeButton.setOnAction(event -> {
		    stage.close();
		    parentStage.show();
	    });
        stage.setOnShown(event -> {
        	try {
        	       
        	    
        	       
                dataCart.setAll(model.getCartDao().getCartList(model.getCurrentUser().getUsername()));
                priceTotalLabel.setText("0");
                cartTableView.setItems(dataCart);
                dataCart.forEach((Cart cart) -> { 
                    priceTotalLabel.setText(Integer.toString((cart.getprice()*cart.getcopies())+Integer.parseInt(priceTotalLabel.getText())));
                });
           
         
            
            
           
            
            }catch (SQLException ex) {
                
               }
    		
    		});
    
	
	
	}
	
    
	public boolean validateFields() {
	    if (ccNumberTextField.getText().length() == 16 && ccNumberTextField.getText().matches("[0-9]+")) {
	    	String ExpDate = mmTextField.getText().concat("/20").concat(yyTextField.getText());
	    	
		    if(validateJavaDate(ExpDate)==true) {
		    	if (cvvTextField.getText().length() == 3 && cvvTextField.getText().matches("[0-9]+")) {
		    	    return true;  	
		       }else{
		        	cvvErrorLabel.setText("invalid CVV");
		        	cvvErrorLabel.setTextFill(Color.RED);
		        	return false;
		       }
		     }
		 }else{
			 ccErrorLabel.setText("Card number MUST be a 16 digit number");
			 ccErrorLabel.setTextFill(Color.RED);
			 return false;
			 }
		return false;
		}
		
		
	
	public  boolean validateJavaDate(String strDate)
	   {
		if (strDate.trim().equals("/20"))
		{
			dateErrorLabel.setText("invalid Date");
		    dateErrorLabel.setTextFill(Color.RED);
		     return false;
		}
		else
		{
		    SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/yyyy");
		    sdfrmt.setLenient(false);
		    try
		    {
		    	 if (new SimpleDateFormat("MM/yyyy").parse(strDate).after(new Date())) {
		    		 return true;
				 }else {  
				     dateErrorLabel.setText("invalid Date");
				     dateErrorLabel.setTextFill(Color.RED);
				     return false;
				    }
				    	
		    }
		    catch (ParseException e)
		    {
		    	dateErrorLabel.setText("invalid Date");
			    dateErrorLabel.setTextFill(Color.RED);
		        return false;
		    }
	
		}
	   }
	public  boolean validatequantityavaliable() {
		mark = true;
		try {
		dataCart.forEach((Cart cart) -> { 
           
			    try {
					if(cart.getcopies()>model.getBookDao().getBook(cart.getbooktitle()).getcopies()) {
						mark = false;	
				}}catch (SQLException e) {
					e.printStackTrace();
				}
		  
        });
		return mark;
		}catch(Exception e)
	    {return mark; }
		
	}
	
		
	public int uniqueRandom() {
		Random rnd = new Random();
        int number= rnd.nextInt(999999);
        try {
			while(model.getOrderDao().getUniqueOrderno(number)==true){
				 number = rnd.nextInt(999999); 			
			}return number;
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return number;
	}
	
	
	
	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 450);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Checkout");
		stage.show();
		
		
	}
	

}
