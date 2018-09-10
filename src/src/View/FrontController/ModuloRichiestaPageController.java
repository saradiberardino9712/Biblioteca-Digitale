package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;
import Business.Controller.controller_domanda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ModuloRichiestaPageController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtnomeua;

    @FXML
    private Label txtcognomeua;

    @FXML
    private TextField txtTitoloStudio;

    @FXML
    private Hyperlink linkIndietro;
    
    @FXML
    private Button btnInviaRichiesta;

    @FXML
    void initialize() {
        assert txtnomeua != null : "fx:id=\"txtnomeua\" was not injected: check your FXML file 'ModuloRichiestaPage.fxml'.";
        assert txtcognomeua != null : "fx:id=\"txtcognomeua\" was not injected: check your FXML file 'ModuloRichiestaPage.fxml'.";
        assert txtTitoloStudio != null : "fx:id=\"txtTitoloStudio\" was not injected: check your FXML file 'ModuloRichiestaPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'ModuloRichiestaPage.fxml'.";
        assert btnInviaRichiesta != null : "fx:id=\"btnInviaRichiesta\" was not injected: check your FXML file 'ModuloRichiestaPage.fxml'.";
        txtnomeua.setText(controller_domanda.nome);
        txtcognomeua.setText(controller_domanda.cognome);
    }
    
    public void Indietro(ActionEvent event) throws Exception {
		switch(controller_domanda.ruolo) {
			case "Utente Base":((Node) event.getSource()).getScene().getWindow().hide();
							Stage primaryStage = new Stage();
							AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/UtenteBasePage.fxml"));
							Scene scene = new Scene(root);
							primaryStage.setScene(scene);
							primaryStage.show();
							break;
			case "Utente Privilegiato":((Node) event.getSource()).getScene().getWindow().hide();
									Stage primaryStage1 = new Stage();
									AnchorPane root1 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/UtentePrivilegiatoPage.fxml"));
									Scene scene1 = new Scene(root1);
									primaryStage1.setScene(scene1);
									primaryStage1.show();
									break;
		}
	}

    public void InviaRichiesta(ActionEvent event) throws Exception {
    	boolean invia=controller_domanda.invia(txtTitoloStudio);
    	if(invia) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invia richiesta");
			alert.setHeaderText("Richiesta andata a buon fine!!");
			alert.showAndWait();
			switch(controller_domanda.ruolo) {
				case "Utente Base":((Node) event.getSource()).getScene().getWindow().hide();
								Stage primaryStage = new Stage();
								AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/UtenteBasePage.fxml"));
								Scene scene = new Scene(root);
								primaryStage.setScene(scene);
								primaryStage.show();
								break;
				case "Utente Privilegiato":((Node) event.getSource()).getScene().getWindow().hide();
										Stage primaryStage1 = new Stage();
										AnchorPane root1 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/UtentePrivilegiatoPage.fxml"));
										Scene scene1 = new Scene(root1);
										primaryStage1.setScene(scene1);
										primaryStage1.show();
										break;
			}
    	}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invia richiesta");
			alert.setHeaderText("Titolo di studio non corrisponde a quello inserito durante la registrazione");
			alert.showAndWait();
    	}
    }
}
