package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPassword;

    @FXML
    private Button btnAccedi;

    @FXML
    private Button btnRegistrati;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void initialize() {
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert lblEmail != null : "fx:id=\"lblEmail\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert lblPassword != null : "fx:id=\"lblPassword\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert btnAccedi != null : "fx:id=\"btnAccedi\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert btnRegistrati != null : "fx:id=\"btnRegistrati\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'LoginPage.fxml'.";
    }
    
    public void Registrazione(ActionEvent event) throws Exception{
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RegistrazionePage.fxml"));
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }	

   public void Login(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/javaFX/UtenteBase.fxml"));
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
}
