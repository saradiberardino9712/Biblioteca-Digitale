package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

import Business.Controller.controller_login;
import Business.Controller.controller_logout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class UtenteBaseController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnLogout;

	@FXML
	private Button btnModulo;

	@FXML
	private Button btnRicerca;
	
	@FXML
	private Hyperlink linkDati;
	
	@FXML
    private Label txtemailua;


	@FXML
	void initialize() {
		assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'UtenteBase.fxml'.";
		assert btnModulo != null : "fx:id=\"btnModulo\" was not injected: check your FXML file 'UtenteBase.fxml'.";
		assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'UtenteBase.fxml'.";
		assert linkDati != null : "fx:id=\"linkDati\" was not injected: check your FXML file 'UtenteBase.fxml'.";
		assert txtemailua != null : "fx:id=\"txtemailua\" was not injected: check your FXML file 'UtenteBase.fxml'.";
		txtemailua.setText(controller_login.email);
	}

	public void Logout(ActionEvent event) throws Exception {
		boolean controllo=controller_logout.controlloutente();
		
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/LoginPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void VediDati(ActionEvent event) throws Exception {
		
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/VisualizzaDatiPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
