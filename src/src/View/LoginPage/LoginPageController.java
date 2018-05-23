package View.LoginPage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnAccedi;

    @FXML
    private Button btnRegistrati;

    @FXML
    void Accedi(ActionEvent event) {
    	String email= txtEmail.getText();
    	String password=txtPassword.getText();
    	
    }

    @FXML
    void Registrati(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert btnAccedi != null : "fx:id=\"btnAccedi\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert btnRegistrati != null : "fx:id=\"btnRegistrati\" was not injected: check your FXML file 'LoginPage.fxml'.";

    }
}
