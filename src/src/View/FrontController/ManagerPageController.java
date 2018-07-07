package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ManagerPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRicerca;

    @FXML
    private Button btnAssegnaOpera;

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
    private Button btnSupervisionaImmagini;

    @FXML
    private Button btnLogout;

    @FXML
    void initialize() {
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnAssegnaOpera != null : "fx:id=\"btnAssegnaOpera\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnRevisiona != null : "fx:id=\"btnRevisiona\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnTrascrivi != null : "fx:id=\"btnTrascrivi\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnAssegnaPagine != null : "fx:id=\"btnAssegnaPagine\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnConsentiPubblicazione != null : "fx:id=\"btnConsentiPubblicazione\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnGestisciLivello != null : "fx:id=\"btnGestisciLivello\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnSupervisionaImmagini != null : "fx:id=\"btnSupervisionaImmagini\" was not injected: check your FXML file 'ManagerPage.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'ManagerPage.fxml'.";

    }
    
    public void Logout(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/LoginPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
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
