package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import model.User;

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
	private TextArea dashboard;
	
	
	public HomeController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	
		
	}
	
	// Add your code to complete the functionality of the program
	@FXML
	public void initialize() {
	
	
	
	updateProfile.setOnAction(event -> {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditProfileView.fxml"));
			
			// Customize controller instance
			EditProfileController EditProfileController =  new EditProfileController(stage, model);

			loader.setController(EditProfileController);
			VBox root = loader.load();
			
			EditProfileController.showStage(root);
			
			
			
			stage.close();
		} catch (IOException e) {
			//message.setText(e.getMessage());
			
			
		}});
	logOut.setOnAction(event -> {
		stage.close();
		parentStage.show();
	});
	
}


	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Home");
		stage.show();
		dashboard.setText("hi " + model.getCurrentUser().getUsername());
	
	
	}
}
