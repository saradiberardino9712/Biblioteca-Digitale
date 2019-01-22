package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_dati;
import Business.Controller.controller_domanda;
import Business.Controller.controller_login;
import Business.Controller.controller_logout;
import Business.Controller.controller_notifiche;
import Business.Controller.controller_richieste;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UtentePrivilegiatoPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnRicerca;

    @FXML
    private Label txtEmailua;

    @FXML
    private Button btnRichiesta;

    @FXML
    private Hyperlink linkDati;
    
    @FXML
    private Menu MenuNotifiche;

    @FXML
    private Button btnAggiorna;
    
    @FXML
    private VBox quattro;
    
    @FXML
    private Button btnEventi;

    @FXML
    void initialize() {
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert txtEmailua != null : "fx:id=\"txtemailua\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert btnRichiesta != null : "fx:id=\"btnRichiesta\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert linkDati != null : "fx:id=\"linkDati\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert MenuNotifiche != null : "fx:id=\"MenuNotifiche\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert btnAggiorna != null : "fx:id=\"btnAggiorna\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert quattro != null : "fx:id=\"quattro\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
        assert btnEventi != null : "fx:id=\"btnEventi\" was not injected: check your FXML file 'UtentePrivilegiatoPage.fxml'.";
		btnEventi.setDisable(true);
		btnEventi.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        txtEmailua.setText(controller_login.email);
        colore();
    }
    
    public static boolean azione=false;
    public void colore() {
    	if(azione) {
    		if(controller_notifiche.prendinotificheutenteid()) {
    			MenuNotifiche.setStyle(" -fx-background-color : red;");
    			VisualizzaNotifiche();
    		}	
    	}else {
    		if(controller_notifiche.prendinotificheutenteid()) {
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
    //prende la homepage dell'utente base
    private void onBtnClicked() throws IOException {
        homepage = (Stage) quattro.getScene().getWindow();
        homepage.close();
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
    			if(item.getText().contains("accettata")) {
    				click(item.getText());
    			}
    		});
    		if(item.getText().contains("accettata")) {
    			item.setOnAction(new EventHandler<ActionEvent>() {
    				public void handle(ActionEvent e) {
    					notifica=item.getText();
    					if(cambia() && controller_notifiche.vista1()) {
    						try {
								onBtnClicked();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
    						Stage primaryStage = new Stage();
    						AnchorPane root=null;
    						try {
    							root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/javaFX/TrascrittorePage.fxml"));
    						} catch (IOException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    						}
    						Scene scene = new Scene(root);
    						primaryStage.setScene(scene);
    						primaryStage.show();
    						item.setDisable(true);
    					}
    				}
    			});
    		}else if(item.getText().contains("rifiutata")) {
    			item.setOnAction(new EventHandler<ActionEvent>() {
    				public void handle(ActionEvent e) {
    					notifica=item.getText();
    					if(controller_notifiche.vista1()) {
    						Alert alert = new Alert(AlertType.INFORMATION);
    						alert.setTitle("Notifiche");
    						alert.setHeaderText("Mi dispiace la richiesta è stata rifiutata puoi ritentare in futuro!!");
    						alert.showAndWait();
    						azione=true;
    						try {
								onBtnClicked();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    						Stage primaryStage = new Stage();
    			    		AnchorPane root=null;
							try {
								root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/javaFX/UtentePrivilegiatoPage.fxml"));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    			    		Scene scene = new Scene(root);
    			    		primaryStage.setScene(scene);
    			    		primaryStage.show();
    					}
    				}
    			});
    		}	
    	}		
    }
    
    private boolean cambia() {
    	boolean cambia=controller_richieste.cambia();
    	if(cambia) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notifiche");
			alert.setHeaderText("Sei diventato trascrittore!! Complimenti!! ");
			alert.showAndWait();
			return true;
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notifiche");
			alert.setHeaderText("Riprova più tardi!!");
			alert.showAndWait();
			return false;
    	}	
    }
    
    public void click(String s) {
    	btnEventi.setDisable(false);
    	btnEventi.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
    	btnEventi.setText("Vai al tuo nuovo profilo");
    	btnEventi.setOnAction(new EventHandler<ActionEvent>() {
    				public void handle(ActionEvent e) {
    					notifica=s;
    					if(cambia() && controller_notifiche.vista()) {
    						try {
								onBtnClicked();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
    						Stage primaryStage = new Stage();
    						AnchorPane root=null;
    						try {
    							root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/javaFX/TrascrittorePage.fxml"));
    						} catch (IOException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    						}
    						Scene scene = new Scene(root);
    						primaryStage.setScene(scene);
    						primaryStage.show();
    					}
    				}
    	});
    }
    
    public void Aggiorna(ActionEvent event) {
    	if(controller_notifiche.prendinotificheutenteid()) {
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
    
     public void Richiesta(ActionEvent event) throws Exception {
    	boolean prendi=controller_domanda.datirichiesta();
		if(prendi) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/ModuloRichiestaPage.fxml"));
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
}

