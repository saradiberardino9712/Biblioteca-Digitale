package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_dati;
import Business.Controller.controller_login;
import Business.Controller.controller_logout;
import Business.Controller.controller_notifiche;
import Business.Controller.controller_ricerca;
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

public class TrascrittorePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRicerca;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnTrascrivi;

    @FXML
    private Label txtEmailua;

    @FXML
    private Hyperlink linkDati;
    
    @FXML
    private Button btnAggiorna;
    
    @FXML
	private Menu MenuNotifiche;

    @FXML
    void initialize() {
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogOut\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
        assert btnTrascrivi != null : "fx:id=\"btnTrascrivi\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
        assert txtEmailua != null : "fx:id=\"txtemailua\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
        assert linkDati != null : "fx:id=\"linkDati\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
        assert btnAggiorna != null : "fx:id=\"btnAggiorna\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
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
        homepage = (Stage) txtEmailua.getScene().getWindow();
        homepage.setIconified(true);
    }
    
    public static String notifica=null;
    public void VisualizzaNotifiche() {
    	ArrayList<String> notifiche=controller_notifiche.notifiche;
    	String finale=null;
    	MenuNotifiche.getItems().clear();
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
    		    	if(notifica.contains("Trascrivi")) {
    		    		try {
    		    			root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/TrascriviPage.fxml"));
    		    		} catch (IOException e1) {
    		    			// TODO Auto-generated catch block
    		    			e1.printStackTrace();
    		    		}
    		    		Scene scene = new Scene(root);
        		    	primaryStage.setScene(scene);
        		    	primaryStage.show();
    		    	}
    				item.setDisable(true);
    			}
    		});
    	}	
    }
    
    public void Aggiorna(ActionEvent event) throws Exception {
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
    	azione=true;
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
     
    public void Ricerca(ActionEvent event) throws Exception {
    	azione=true;
		boolean ricerca=controller_ricerca.verifica();
		if(ricerca) {
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RicercaOperePage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Ricerca");
			alert.setHeaderText("Non ci sono opere al momento!!");
			alert.showAndWait();
		}
    }
    
    public void Trascrivi(ActionEvent event) throws Exception {
    	azione=true;
    	boolean controllo = false;
    	ArrayList<String> notifiche=controller_notifiche.notifiche;
    	for(String e:notifiche) {
    		if(e.contains("Trascrivi")) {
    			controllo=true;
    			notifica=e;
    		}
    	}
    	if(controllo) {
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/TrascriviPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Trascrizioni");
			alert.setHeaderText("Non ci sono trascrizioni da effettuare al momento!!");
			alert.showAndWait();
    	}
    }  
}
