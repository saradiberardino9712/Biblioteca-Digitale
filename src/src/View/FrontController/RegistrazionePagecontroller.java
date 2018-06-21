package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Business.Controller.controller_registrazione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RegistrazionePagecontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtIndirizzo;

    @FXML
    private TextField txtProfessione;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtDataNascita;

    @FXML
    private TextField txtTitoloStudio;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
	public ComboBox<String> combobox;
	
	ObservableList<String> list = FXCollections.observableArrayList("Utente base", "Utente Privilegiato", "Acquisitore", "Supervisore", "Trascrittore", "Revisore", "Manager");
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		combobox.setItems(list);
	}
	
    @FXML
    private Button btnRegistrati;

    @FXML
    void initialize() {
        assert txtIndirizzo != null : "fx:id=\"txtIndirizzo\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtProfessione != null : "fx:id=\"txtProfessione\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtDataNascita != null : "fx:id=\"txtDataNascita\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtTitoloStudio != null : "fx:id=\"txtTitoloStudio\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert combobox != null : "fx:id=\"combobox\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert btnRegistrati != null : "fx:id=\"btnRegistrati\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
    }
    
    public void Registrati(ActionEvent event) throws IOException {
    	boolean conferma=controller_registrazione.inviaRegistrazione(txtNome,txtCognome,txtIndirizzo, txtPassword, txtDataNascita, txtEmail, txtTitoloStudio, txtProfessione, combobox);
    	if (conferma) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Registrazione");
    		alert.setHeaderText("Registrazione avvenuta con successo. Puoi effettuare il login ora!!");
    		alert.showAndWait();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Registrazione");
    		alert.setHeaderText("Errore");
    		alert.showAndWait();
    	}
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/LoginPage.fxml"));
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }	
}
