package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;
import Business.Controller.controller_dati;
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
    private Button btnSupervisiona;

    @FXML
    private Button btnLogout;

    @FXML
    private Label txtemailua;

    @FXML
    private Hyperlink linkDati;

    @FXML
    private ComboBox<String> comboNotifiche;
    ObservableList<String> list = FXCollections.observableArrayList("notifica");

    @FXML
    void AssegnaOpere(ActionEvent event) {

    }

    @FXML
    void ConsentiPubblicazione(ActionEvent event) {

    }

    @FXML
    void GestisciLivello(ActionEvent event) {

    }

    @FXML
    void Supervisiona(ActionEvent event) {

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
        assert btnSupervisiona != null : "fx:id=\"btnSupervisiona\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert txtemailua != null : "fx:id=\"txtemailua\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert linkDati != null : "fx:id=\"linkDati\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert comboNotifiche != null : "fx:id=\"comboNotifiche\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnConsentiPubblicazione != null : "fx:id=\"btnConsentiPubblicazione\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnGestisciLivello != null : "fx:id=\"btnGestisciLivello\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnSupervisiona != null : "fx:id=\"btnSupervisiona\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        comboNotifiche.setItems(list);
        txtemailua.setText(controller_login.email);
    }
    
    public void Ricerca(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RicercaOperePage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
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
    
    public void Trascrivi(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/TrascriviPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public void Assegna(ActionEvent event) throws Exception {
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
}


