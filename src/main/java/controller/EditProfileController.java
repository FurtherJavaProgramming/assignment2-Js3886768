package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.User;

public class EditProfileController {
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private TextField firstname;
	@FXML
	private TextField lastname;
	@FXML
	private Button createUser;
	@FXML
	private Button close;
	@FXML
	private Label status;
	
	private Stage stage;
	private Stage parentStage;
	private Model model;
	
	public EditProfileController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

	@FXML
	public void initialize() {
		createUser.setOnAction(event -> {
		
				User user;
				try {
					user = model.getUserDao().setUser(model.getCurrentUser().getUsername(), password.getText(),firstname.getText(),lastname.getText());
					if (password.getText().isEmpty() && firstname.getText().isEmpty() && lastname.getText().isEmpty()) {
						status.setText("No input Cannot Update");
						status.setTextFill(Color.RED);
					}
					else if (user != null) {
						status.setText("Updated " + user.getUsername());
						status.setTextFill(Color.GREEN);
					} else {
						status.setText("Cannot update");
						status.setTextFill(Color.RED);
					}
				} catch (SQLException e) {
					status.setText(e.getMessage());
					status.setTextFill(Color.RED);
				}
				
	
			
		});

		close.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});
	}
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Edit Profile");
		stage.show();
	}
}
