package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;
import Business.Controller.*;
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
	private Button btnRichiesta;

	@FXML
	private Button btnRicerca;
	
	@FXML
	private Hyperlink linkDati;
	
	@FXML
	private Label txtemailua;

    @FXML
    private Label txtruoloua;
    
	@FXML
	void initialize() {
		assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'UtenteBase.fxml'.";
		assert btnRichiesta != null : "fx:id=\"btnModulo\" was not injected: check your FXML file 'UtenteBase.fxml'.";
		assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'UtenteBase.fxml'.";
		assert linkDati != null : "fx:id=\"linkDati\" was not injected: check your FXML file 'UtenteBase.fxml'.";
		assert txtemailua != null : "fx:id=\"txtemailua\" was not injected: check your FXML file 'UtenteBase.fxml'.";
		assert txtruoloua != null : "fx:id=\"txtruoloua\" was not injected: check your FXML file 'UtenteBase.fxml'.";
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

	public void Ricerca(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RicercaOperePage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
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
