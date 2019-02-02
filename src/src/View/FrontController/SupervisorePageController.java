package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Business.Controller.controller_dati;
import Business.Controller.controller_domanda;
import Business.Controller.controller_login;
import Business.Controller.controller_logout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SupervisorePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnRicerca;

    @FXML
    private Button btnControlla;

    @FXML
    private Label txtemailua;

    @FXML
    private Hyperlink linkDati;
    
    @FXML
    private Button btnAggiorna;
    
    @FXML
	private Menu MenuNotifiche;
    
    @FXML
    private Button btnPubblicaOpera;

    @FXML
    void initialize() {
        assert btnLogOut != null : "fx:id=\"btnLogOut\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert btnControlla != null : "fx:id=\"btnControlla\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert txtemailua != null : "fx:id=\"txtemailua\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert linkDati != null : "fx:id=\"linkDati\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert btnAggiorna != null : "fx:id=\"btnAggiorna\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert btnPubblicaOpera != null : "fx:id=\"btnPubblicaOpera\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        txtemailua.setText(controller_login.email);
        colore();
    }
    
    public void colore() {
    	if(controller_domanda.notificacolore) {
    		btnAggiorna.setStyle(" -fx-base: red;");
    	}
    }
    
    public void Aggiorna(ActionEvent event) throws Exception {
		btnAggiorna.setStyle(" -fx-base: gray;");
		ArrayList<String> notifiche=controller_domanda.prendinotifichedomanda();
		if(notifiche.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notifiche");
			alert.setHeaderText("Non ci sono notifiche al momento!!");
			alert.showAndWait();
		}
    	String finale=null;
    	for(String e:notifiche) {
    		finale=e;
    		MenuItem item=new MenuItem(finale);
    		MenuNotifiche.getItems().add(item);
    		item.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					((Node)event.getSource()).getScene().getWindow().hide();
		    		Stage primaryStage = new Stage();
		    		BorderPane root = null;
					try {
						root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/AccettaRifiutaRichiestePage.fxml"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    		Scene scene = new Scene(root);
		    		primaryStage.setScene(scene);
		    		primaryStage.show();
		    		item.setDisable(true);
				}
			});
    	}
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
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RicercaOperePage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public void Controlla(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePage.fxml"));
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
    
    public void PubblicaOpera(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/ConsentiPubblicazioneOperaPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}

