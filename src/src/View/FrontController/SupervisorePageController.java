package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_dati;
import Business.Controller.controller_login;
import Business.Controller.controller_logout;
import Business.Controller.controller_notifiche;
import Business.Controller.controller_revisione_acquisizione;
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

public class SupervisorePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnRicerca;

    @FXML
    private Button btnControlla;

    @FXML
    private Label txtEmailua;

    @FXML
    private Hyperlink linkDati;
    
    @FXML
    private Button btnAggiorna;
    
    @FXML
	private Menu MenuNotifiche;
    
    @FXML
    private VBox sup;
    
    @FXML
    private Button btnPubblicaOpera;

    @FXML
    void initialize() {
        assert btnLogout != null : "fx:id=\"btnLogOut\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert btnControlla != null : "fx:id=\"btnControlla\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert txtEmailua != null : "fx:id=\"txtemailua\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert linkDati != null : "fx:id=\"linkDati\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert btnAggiorna != null : "fx:id=\"btnAggiorna\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert sup != null : "fx:id=\"sup\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
        assert btnPubblicaOpera != null : "fx:id=\"btnPubblicaOpera\" was not injected: check your FXML file 'SupervisorePage.fxml'.";
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
        homepage = (Stage) sup.getScene().getWindow();
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
    		    	if(notifica.contains("Controlla")) {
    		    		controller_revisione_acquisizione.verifica();
    		    		try {
    		    			root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePage.fxml"));
    		    		} catch (IOException e1) {
    		    			// TODO Auto-generated catch block
    		    			e1.printStackTrace();
    		    		}
    		    		Scene scene = new Scene(root);
        		    	primaryStage.setScene(scene);
        		    	primaryStage.show();
    		    	}
    		    	if(notifica.contains("Pubblica")) {
    		    		try {
    		    			root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/PubblicaImmaginiPage.fxml"));
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
    
    public void Controlla(ActionEvent event) throws Exception {
    	azione=true;
	    boolean controlla=controller_revisione_acquisizione.verifica();
	    if(controlla) {
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePage.fxml"));
	    	Scene scene = new Scene(root);
	    	primaryStage.setScene(scene);
	    	primaryStage.show();
	    }else {
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Controlla immagini");
			alert.setHeaderText("Non ci sono immagini da esaminare al momento!!");
			alert.showAndWait();
	    }
    }  
    
    public void Ricerca(ActionEvent event) throws Exception {
    	azione=true;
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RicercaOperePage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }    
    
    public void PubblicaOpera(ActionEvent event) throws Exception {
    	ArrayList<String> notifiche=controller_notifiche.notifiche;
    	boolean controllo= false;
    	for(String e:notifiche) {
    		if(e.contains("Pubblica")) {
    			controllo=true;
    			notifica=e;
    		}
    	}
    	if(controllo) {
    		onBtnClicked();
    		azione=true;
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/PubblicaImmaginiPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Controlla immagini");
			alert.setHeaderText("Non ci sono opere da pubblicare al momento!!");
			alert.showAndWait();
    	}
    }
}

