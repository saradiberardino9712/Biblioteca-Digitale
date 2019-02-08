package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Business.Controller.controller_eliminazione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EliminaUtentePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listutente;

    @FXML
    private TextField lblEmail;

    @FXML
    private Button btnElimina;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    void initialize() {
        assert listutente != null : "fx:id=\"listutente\" was not injected: check your FXML file 'EliminaUtentePage.fxml'.";
        assert lblEmail != null : "fx:id=\"lblEmail\" was not injected: check your FXML file 'EliminaUtentePage.fxml'.";
        assert btnElimina != null : "fx:id=\"btnElimina\" was not injected: check your FXML file 'EliminaUtentePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'EliminaUtentePage.fxml'.";
        carica();
    }
    
    public void carica() {
    	ArrayList<String> utente=controller_eliminazione.prendiu();
    	ObservableList<String> list=FXCollections.observableArrayList();
    	for(String s:utente) {
    		list.add(s);
    	}
    	listutente.setItems(list);
    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage6 = new Stage();
		BorderPane root6 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AmministratorePage.fxml"));
		Scene scene6 = new Scene(root6);
		primaryStage6.setScene(scene6);
		primaryStage6.show();
    }
    
    public static String selezione="no selezione";
    public void click(MouseEvent event) {
    	selezione=listutente.getSelectionModel().getSelectedItem();
    	String in=", Email: ";
		int s=selezione.indexOf(in);
		String email=selezione.substring(s+in.length());
		lblEmail.setText(email);
    }
    
    public void Elimina(ActionEvent event) throws IOException {
    	if(selezione.equals("no selezione")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Elimina Utente");
			alert.setHeaderText("Selezionare utente da eliminare!!");
			alert.showAndWait();
    	}else {
	    	boolean elimina =controller_eliminazione.eliminau(lblEmail);
	    	if(elimina) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Elimina Utente");
				alert.setHeaderText("L'eliminazione è avvenuta con successo!!");
				alert.showAndWait();
				((Node) event.getSource()).getScene().getWindow().hide();
				Stage primaryStage6 = new Stage();
				BorderPane root6 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AmministratorePage.fxml"));
				Scene scene6 = new Scene(root6);
				primaryStage6.setScene(scene6);
				primaryStage6.show();
	    	}else {
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Elimina Utente");
				alert.setHeaderText("C'è stato un problema. Riprovare");
				alert.showAndWait();
	    	}
    	}
    }
}
