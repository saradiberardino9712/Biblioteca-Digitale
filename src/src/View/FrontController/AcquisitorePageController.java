package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_caricamento;
import Business.Controller.controller_consenso_supervisione;
import Business.Controller.controller_dati;
import Business.Controller.controller_login;
import Business.Controller.controller_logout;
import Business.Controller.controller_notifiche;
import Business.Model.Notifica;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AcquisitorePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRicerca;

    @FXML
    private Button btnCarica;

    @FXML
    private Button btnLogout;

    @FXML
    private Label txtEmailua;

    @FXML
    private Button btnInserisciMetadati;

    @FXML
    private Hyperlink linkDati;
    
    @FXML
    private Button btnAggiorna;
    
    @FXML
	private Menu MenuNotifiche;
    
    @FXML
    private VBox acq;
    
    @FXML
    private Button btnEventi;

    @FXML
    void initialize() {
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'AcquisitorePage.fxml'.";
        assert btnCarica != null : "fx:id=\"btnCarica\" was not injected: check your FXML file 'AcquisitorePage.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'AcquisitorePage.fxml'.";
        assert txtEmailua != null : "fx:id=\"txtemailua\" was not injected: check your FXML file 'AcquisitorePage.fxml'.";
        assert btnInserisciMetadati != null : "fx:id=\"btnInserisciMetadati\" was not injected: check your FXML file 'AcquisitorePage.fxml'.";
        assert linkDati != null : "fx:id=\"linkDati\" was not injected: check your FXML file 'AcquisitorePage.fxml'.";
        assert btnAggiorna != null : "fx:id=\"btnAggiorna\" was not injected: check your FXML file 'AcquisitorePage.fxml'.";
        assert acq != null : "fx:id=\"acq\" was not injected: check your FXML file 'AcquisitorePage.fxml'.";
        assert btnEventi != null : "fx:id=\"btnEventi\" was not injected: check your FXML file 'AcquisitorePage.fxml'.";
        assert MenuNotifiche != null : "fx:id=\"MenuNotifiche\" was not injected: check your FXML file 'AcquisitorePage.fxml'.";
        txtEmailua.setText(controller_login.email);
        btnEventi.setDisable(true);
        btnEventi.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        updatenot();
        colore();
    }
    
    public void updatenot() {
    	controller_consenso_supervisione.eliminate();
    	if(controller_consenso_supervisione.imgeliminate.isEmpty()) {
    		ArrayList<Notifica> esistenza=controller_notifiche.esistenza();
    		String desc;
    		String orario;
    		for(Notifica c: esistenza) {
    			desc=c.getdescrizione();
    			orario=c.getorario().toString();
    			controller_consenso_supervisione.updatenot(desc, orario);
    		}
    	}
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
        homepage = (Stage) acq.getScene().getWindow();
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
    			if(item.getText().contains("negate")) {
    				click(item.getText());
    			}
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
    		    	if(notifica.contains("negate")) {
    		    		BorderPane root=null;
    		    		try {
    		    			root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisizioniNegatePage.fxml"));
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

    public void click(String s) {
    	btnEventi.setDisable(false);
    	btnEventi.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
    	btnEventi.setText("Elenco immagini negate");
    	btnEventi.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					onBtnClicked();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Stage primaryStage = new Stage();
				BorderPane root=null;
				try {
					root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisizioniNegatePage.fxml"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
			}
    	});
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
    
    public void InserisciMetadati(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/InserisciMetadatiPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    } 
    
    public void Carica(ActionEvent event) throws Exception {
    	boolean esistenza=controller_caricamento.verifica();
    	if(esistenza) {
    		((Node)event.getSource()).getScene().getWindow().hide();
    		Stage primaryStage = new Stage();
    		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisizioneImmaginePage.fxml"));
    		Scene scene = new Scene(root);
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore");
			alert.setHeaderText("Non ci sono pagine di opere da acquisire!!");
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
}
