package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_dati;
import Business.Controller.controller_login;
import Business.Controller.controller_logout;
import Business.Controller.controller_notifiche;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRicerca;

    @FXML
    private Button btnAssegnaOpere;

    @FXML
    private Button btnRevisiona;

    @FXML
    private Button btnTrascrivi;

    @FXML
    private Button btnAssegnaPagine;

    @FXML
    private Button btnConsentiPubblicazione;

    @FXML
    private Button btnGestisciLivello;

    @FXML
    private Button btnConsentiSupervisione;

    @FXML
    private Button btnLogout;

    @FXML
    private Label txtEmailua;

    @FXML
    private Hyperlink linkDati;

    @FXML
    private Menu MenuNotifiche;

    @FXML
    private Button btnAccettaRifiuta;
    
    @FXML
    private VBox uno;
    
    @FXML
    private Button btnAggiorna;

    @FXML
    void ConsentiPubblicazione(ActionEvent event) {

    }

    @FXML
    void GestisciLivello(ActionEvent event) {

    }


    @FXML
    void initialize() {
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnAssegnaOpere != null : "fx:id=\"btnAssegnaOpere\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnRevisiona != null : "fx:id=\"btnRevisiona\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnTrascrivi != null : "fx:id=\"btnTrascrivi\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnAssegnaPagine != null : "fx:id=\"btnAssegnaPagine\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnConsentiPubblicazione != null : "fx:id=\"btnConsentiPubblicazione\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnGestisciLivello != null : "fx:id=\"btnGestisciLivello\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnConsentiSupervisione != null : "fx:id=\"btnConsentiSupervisione\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert txtEmailua != null : "fx:id=\"txtemailua\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert linkDati != null : "fx:id=\"linkDati\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert MenuNotifiche != null : "fx:id=\"MenuNotifiche\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnAccettaRifiuta != null : "fx:id=\"btnAccettaRifiuta\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        txtEmailua.setText(controller_login.email);
        colore();
    }
    
    public static boolean azione=false;
    //aggiornamento automatico delle notifiche
    public void colore() {
    	if(azione) {
    		if(controller_notifiche.prendinotificheutente()) {
    			MenuNotifiche.setStyle(" -fx-background-color : red;");
    			VisualizzaNotifiche();
    		}	
    	}else {
    		if(controller_notifiche.prendinotificheutente()) {
    			MenuNotifiche.setStyle(" -fx-background-color : red;");
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Notifiche");
    			alert.setHeaderText("Ci sono notifiche!! Clicca sul pulsante rosso \"Notifiche\" per vederle!! ");
    			alert.showAndWait();
    			VisualizzaNotifiche();
    		}else {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Notifiche");
    			alert.setHeaderText("Non ci sono notifiche al momento!!");
    			alert.showAndWait();
    		}
    	}
    }
    
    public static Stage homepage;
    //prende la homepage del manager
    private void onBtnClicked() throws IOException {
        homepage = (Stage) uno.getScene().getWindow();
        homepage.setIconified(true);
    }
    
    public static String notifica=null;
    public void VisualizzaNotifiche() {
    	ArrayList<String> notifiche=controller_notifiche.notifiche;
    	String finale=null;
    	for(String e:notifiche) {
    		finale=e;
       		MenuItem item=new MenuItem(finale);
    		MenuNotifiche.getItems().add(item);
    		MenuNotifiche.setOnMenuValidation(event ->{
    			MenuNotifiche.setStyle(" -fx-background-color : LIGHTGRAY;");
    		});
    		item.setOnAction(new EventHandler<ActionEvent>() {
    			public void handle(ActionEvent e) {
    		    	try {
    		    		notifica=item.getText();
    		    		azione=true;
						onBtnClicked();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
    		    	Stage primaryStage = new Stage();
    		    	BorderPane root=null;
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
    
    public void Aggiorna(ActionEvent event) {
    	if(controller_notifiche.prendinotificheutente()) {
    		MenuNotifiche.setStyle(" -fx-background-color : red;");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notifiche");
			alert.setHeaderText("Ci sono notifiche!! Clicca sul pulsante rosso \"Notifiche\" per vederle!! ");
			alert.showAndWait();
			VisualizzaNotifiche();
    	}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notifiche");
			alert.setHeaderText("Non ci sono notifiche al momento!!");
			alert.showAndWait();
    	}
    }
    
	public void Logout(ActionEvent event) throws Exception {
    	boolean disattiva=controller_logout.disattivautente();
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
		boolean visualizza=controller_dati.visualizza();
    	if(visualizza) {
    		((Node) event.getSource()).getScene().getWindow().hide();
    		Stage primaryStage = new Stage();
    		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/VisualizzaDatiPage.fxml"));
    		Scene scene = new Scene(root);
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}
	}	
	
    public void AccettaRifiuta(ActionEvent event) throws IOException {
    	ArrayList<String> notifiche=controller_notifiche.notifiche;
    	boolean domande= false;
    	for(String e:notifiche) {
    		if(e.contains("Accetta")) {
    			domande=true;
    			notifica=e;
    		}
    	}
    	if(domande) {
    		onBtnClicked();
    		azione=true;
    		Stage primaryStage = new Stage();
    		BorderPane root=(BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/AccettaRifiutaRichiestePage.fxml"));
    		Scene scene = new Scene(root);
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Accetta/Rifiuta");
			alert.setHeaderText("Non ci sono richieste da esaminare al momento!!");
			alert.showAndWait();
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
	
	public void Trascrivi(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/TrascriviPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
	
	public void AssegnaOpere(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/AssegnaOpere.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
	
	public void Revisiona(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RevisionaTrascrizioniPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
	
	public void AssegnaPagine(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/AssegnaPagine.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
	
	public void ConsentiSupervisione(ActionEvent event) throws Exception {
		boolean visualizza=controller_dati.visualizza();
    	if(visualizza) {
    		((Node) event.getSource()).getScene().getWindow().hide();
    		Stage primaryStage = new Stage();
    		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/ConsentiSupervisionePage.fxml"));
    		Scene scene = new Scene(root);
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}
	}	
}
