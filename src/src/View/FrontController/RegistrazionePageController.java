package View.FrontController;

import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RegistrazionePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtIndirizzo;

    @FXML
    private TextField txtTitoloStudio;

    @FXML
    private TextField txtProfessione;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ComboBox<String> combobox;

    ObservableList<String> list = FXCollections.observableArrayList("Utente Base", "Utente Privilegiato", "Acquisitore",
			"Supervisore", "Trascrittore", "Revisore", "Manager");
    
    @FXML
    private Button btnRegistrati;

    @FXML
    private DatePicker datapicker;
    
    @FXML
    private Hyperlink linkIndietro;

    @FXML
    void initialize() {
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtIndirizzo != null : "fx:id=\"txtIndirizzo\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtTitoloStudio != null : "fx:id=\"txtTitoloStudio\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtProfessione != null : "fx:id=\"txtProfessione\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert combobox != null : "fx:id=\"combobox\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert btnRegistrati != null : "fx:id=\"btnRegistrati\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        assert datapicker != null : "fx:id=\"datapicker\" was not injected: check your FXML file 'RegistrazionePage.fxml'.";
        combobox.setItems(list);
        datapicker.setValue(LocalDate.now());
    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/LoginPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public void Registrati(ActionEvent event) throws Exception {
		boolean conferma = controller_registrazione.inviaRegistrazione(txtNome, txtCognome, txtIndirizzo, txtPassword,
				datapicker, txtEmail, txtTitoloStudio, txtProfessione, combobox);
		if (conferma) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Registrazione");
			alert.setHeaderText("Registrazione avvenuta con successo. Puoi effettuare il login ora!!");
			alert.showAndWait();
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/LoginPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
}


