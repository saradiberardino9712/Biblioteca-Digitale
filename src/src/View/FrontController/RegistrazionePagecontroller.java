package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;


public class RegistrazionePagecontroller implements Initializable{
	
	@FXML
	public ComboBox<String> combobox;
	
	ObservableList<String> list = FXCollections.observableArrayList("Utente base", "Utente Privilegiato", "Acquisitore", "Supervisore", "Trascrittore", "Revisore", "Manager");
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		combobox.setItems(list);
		
	}
	
	public void Registrati(ActionEvent event) throws Exception{
		
	}

}
