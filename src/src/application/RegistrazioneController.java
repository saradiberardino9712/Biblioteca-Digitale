package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.PasswordField;

public class RegistrazioneController implements Initializable {
	@FXML
	private TextField txtEmail;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btnInvia;
	
	@FXML
	private Label lblRegistrato;
	
	@FXML
	public ComboBox<String> combobox;
	
	ObservableList<String> list = FXCollections.observableArrayList("Utente base", "Utente privilegiato", "Acquisitore", "Supervisore", "Trascrittore", "Revisore", "Manager");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		combobox.setItems(list);
	}
	
	public void Invia(ActionEvent event) {
		lblRegistrato.setText("Benvenuto nella Biblioteca Digitale! \nGrazie per esserti registrato!");
	}

}
