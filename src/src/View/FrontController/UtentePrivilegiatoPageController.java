package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

import Business.Controller.controller_dati;
import Business.Controller.controller_domanda;
import Business.Controller.controller_login;
import Business.Controller.controller_logout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class UtentePrivilegiatoPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtruoloua;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnRicerca;

    @FXML
    private Label txtemailua;

    @FXML
    private Button btnRichiesta;

    @FXML
    private Hyperlink linkDati;
    
    @FXML
    private ComboBox<String> comboNotifiche;
    ObservableList<String> list = FXCollections.observableArrayList("notifica");

    @FXML
    void initialize() {
        assert txtruoloua != null : "fx:id=\"txtruoloua\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert txtemailua != null : "fx:id=\"txtemailua\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert btnRichiesta != null : "fx:id=\"btnRichiesta\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert linkDati != null : "fx:id=\"linkDati\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert comboNotifiche != null : "fx:id=\"comboNotifiche\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        comboNotifiche.setItems(list);
        txtemailua.setText(controller_login.email);
    }
    
    public void Logout(ActionEvent event) throws Exception {
    	boolean disattiva=controller_logout.disattivautente(txtemailua);
    	if(disattiva) {
    		((Node)event.getSource()).getScene().getWindow().hide();
    		Stage primaryStage = new Stage();
    		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/LoginPage.fxml"));
    		Scene scene = new Scene(root);
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}
    }
    
    public void Ricerca(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RicercaOpere.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public void VediDati(ActionEvent event) throws Exception {
		boolean visualizza=controller_dati.visualizza(txtemailua);
		if(visualizza) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/VisualizzaDatiPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
    
    public void Richiesta(ActionEvent event) throws Exception {
    	boolean prendi=controller_domanda.datirichiesta(txtemailua,txtruoloua);
		if(prendi) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/ModuloRichiestaPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}	
    }
}

