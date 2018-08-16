package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

import Business.Controller.controller_login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class TrascrittorePageController {


	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button btnConsulta;

	    @FXML
	    private Button btnLogOut;

	    @FXML
	    private Button btnTrascrivi;
	    
	    @FXML
	    private Label txtemailua;

	    @FXML
	    void initialize() {
	        assert btnConsulta != null : "fx:id=\"btnConsulta\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
	        assert btnLogOut != null : "fx:id=\"btnLogOut\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
	        assert btnTrascrivi != null : "fx:id=\"btnTrascrivi\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
	        assert txtemailua != null : "fx:id=\"txtemailua\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
	        txtemailua.setText(controller_login.email);

	    }
	    
	    public void Logout(ActionEvent event) throws Exception {
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/LoginPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
	    }
	    
	    public void Ricerca(ActionEvent event) throws Exception {
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RicercaOperePage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
	    }
	    
	    public void Trascrivi(ActionEvent event) throws Exception {
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/TrascriviPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
	    }
	
}