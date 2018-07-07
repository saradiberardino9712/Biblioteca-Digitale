package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

import Business.Controller.controller_login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RicercaOperePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCerca;

    @FXML
    private TextArea txtRisultatoRicerca;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    void initialize() {
        assert txtCerca != null : "fx:id=\"txtCerca\" was not injected: check your FXML file 'RicercaOpere.fxml'.";
        assert txtRisultatoRicerca != null : "fx:id=\"txtRisultatoRicerca\" was not injected: check your FXML file 'RicercaOpere.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'RicercaOpere.fxml'.";

    }
    
    	public void Indietro(ActionEvent event) throws Exception {
    		switch(controller_login.ruolo) {
    		case "Utente Base":((Node) event.getSource()).getScene().getWindow().hide();
    							Stage primaryStage = new Stage();
    							AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/UtenteBase.fxml"));
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
    		case "Acquisitore":((Node) event.getSource()).getScene().getWindow().hide();
    							Stage primaryStage2 = new Stage();
    							AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisitorePage.fxml"));
    							Scene scene2 = new Scene(root2);
    							primaryStage2.setScene(scene2);
    							primaryStage2.show();
    							break;
    		case "Supervisore":((Node) event.getSource()).getScene().getWindow().hide();
    							Stage primaryStage3 = new Stage();
    							AnchorPane root3 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/SupervisorePage.fxml"));
    							Scene scene3 = new Scene(root3);
    							primaryStage3.setScene(scene3);
    							primaryStage3.show();
    							break;
    		case "Trascrittore":((Node) event.getSource()).getScene().getWindow().hide();
    							Stage primaryStage4 = new Stage();
    							AnchorPane root4 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/TrascrittorePage.fxml"));
    							Scene scene4 = new Scene(root4);
    							primaryStage4.setScene(scene4);
    							primaryStage4.show();
    							break;
    		case "Revisore":((Node) event.getSource()).getScene().getWindow().hide();
    						Stage primaryStage5 = new Stage();
    						AnchorPane root5 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisorePage.fxml"));
    						Scene scene5 = new Scene(root5);
    						primaryStage5.setScene(scene5);
    						primaryStage5.show();
    						break;
    		case "Manager":((Node) event.getSource()).getScene().getWindow().hide();
    						Stage primaryStage6 = new Stage();
    						AnchorPane root6 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage"));
    						Scene scene6 = new Scene(root6);
    						primaryStage6.setScene(scene6);
    						primaryStage6.show();
    						break;
    		}
    	}
}