package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;
import Business.Controller.controller_richieste;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccettaRifiutaRichiestePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ListView<String> listview;

    @FXML
    private Button btnAccetta;

    @FXML
    private Button btnRifiuta;

    @FXML
    private Hyperlink linkIndietro;
    
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtRuolo;

    @FXML
    private TextField txtTitoloStudio;
    
    @FXML
    private VBox due;

    @FXML
    void initialize() {
        assert btnAccetta != null : "fx:id=\"btnAccetta\" was not injected: check your FXML file 'AccettaRifiutaRichiestePage.fxml'.";
        assert btnRifiuta != null : "fx:id=\"btnRifiuta\" was not injected: check your FXML file 'AccettaRifiutaRichiestePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'AccettaRifiutaRichiestePage.fxml'.";
        assert listview != null : "fx:id=\"listview\" was not injected: check your FXML file 'AccettaRifiutaRichiestePage.fxml'.";
        assert txtNome != null : "fx:id=\"txtnome\" was not injected: check your FXML file 'AccettaRifiutaRichiestePage.fxml'.";
        assert txtCognome != null : "fx:id=\"txtcognome\" was not injected: check your FXML file 'AccettaRifiutaRichiestePage.fxml'.";
        assert txtRuolo != null : "fx:id=\"txtruolo\" was not injected: check your FXML file 'AccettaRifiutaRichiestePage.fxml'.";
        assert txtTitoloStudio != null : "fx:id=\"txttitolostudio\" was not injected: check your FXML file 'AccettaRifiutaRichiestePage.fxml'.";
        visualizza();
    }
    
	public void visualizza() {
		ObservableList<String> elencoutenti=controller_richieste.prendiutentidomanda();
    	listview.setItems((ObservableList<String>) elencoutenti);
    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	Stage homepage1 = (Stage) due.getScene().getWindow();
        homepage1.close();
        Stage torna= ManagerPageController.homepage;
        torna.setIconified(false);
    }
    
    public void Esamina(MouseEvent event) {
    	String frase=listview.getSelectionModel().getSelectedItem();
    	boolean esamina=controller_richieste.esaminarichiesta(frase);
    	if(esamina) {
    		txtNome.setText(controller_richieste.nome);
    		txtCognome.setText(controller_richieste.cognome);
    		txtRuolo.setText(controller_richieste.ruolo);
    		txtTitoloStudio.setText(controller_richieste.titolostudio);
    	}
    }
    
    public void Accetta(ActionEvent event) throws Exception {
    	boolean accetta= controller_richieste.accettadomanda();
    	if(accetta) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invio responso domanda");
			alert.setHeaderText("E' stato mandato il seguente messaggio all'utente che ha effettuato la richiesta:\" La sua richiesta è stata accettata \"");
			alert.showAndWait();
			((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    	}
    }
    
    public void Rifiuta(ActionEvent event) throws Exception {
    	boolean rifiuta= controller_richieste.rifiutadomanda();
    	if(rifiuta) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invio responso domanda");
			alert.setHeaderText("E' stato mandato il seguente messaggio all'utente che ha effettuato la richiesta:\" La sua richiesta è stata rifiutata \"");
			alert.showAndWait();
			((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    	}
    }
}
