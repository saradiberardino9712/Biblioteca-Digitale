package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Business.Controller.controller_pubblicazione_trascrizione;
import Business.Model.TestoDigitale;
import javafx.collections.FXCollections;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class PubblicaTrascrizioniPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnPubblica;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private ListView<String> listtrascrizioni;

    @FXML
    private HTMLEditor text;

    @FXML
    void initialize() {
        assert btnPubblica != null : "fx:id=\"btnPubblica\" was not injected: check your FXML file 'PubblicaTrascrizioniPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'PubblicaTrascrizioniPage.fxml'.";
        assert listtrascrizioni != null : "fx:id=\"listtrascrizioni\" was not injected: check your FXML file 'PubblicaTrascrizioniPage.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'PubblicaTrascrizioniPage.fxml'.";
        visualizza();
    }
    
    public void visualizza() {
    	controller_pubblicazione_trascrizione.prendi();
    	String s;
    	ObservableList<String> lista= FXCollections.observableArrayList();
    	for(TestoDigitale t:controller_pubblicazione_trascrizione.traesaminare) {
    		s="Opera: " + t.getTitoloOpera() + ", Pagina n°: " + t.getNumpag();
    		if(!lista.contains(s))
    			lista.add(s);
    	}
    	listtrascrizioni.setItems(lista);
    }
    
    public void Indietro(ActionEvent event) {
    	Stage page=(Stage) listtrascrizioni.getScene().getWindow();
    	page.close();
    	Stage torna=RevisorePageController.homepage;
    	torna.setIconified(false);
    }
    
    public static String selezione="no selezione";
    public void click(MouseEvent event) {
    	selezione=listtrascrizioni.getSelectionModel().getSelectedItem();
    	String testo=controller_pubblicazione_trascrizione.prenditesto(selezione);
		text.setHtmlText(testo);
    }
    
    public void pubblica(ActionEvent event) throws IOException {
    	String selezione=listtrascrizioni.getSelectionModel().getSelectedItem();
    	boolean pubblica=controller_pubblicazione_trascrizione.pubblica(selezione);
    	if(pubblica) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Pubblica trascrizione");
			alert.setHeaderText("La pubblicazione è andata a buon fine!!");
			alert.showAndWait();
			Stage torna=RevisorePageController.homepage;
			torna.close();
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
    		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisorePage.fxml"));
    		Scene scene = new Scene(root);
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Pubblica trascrizione");
			alert.setHeaderText("C'è stato un errore riprovare!!");
			alert.showAndWait();
    	}
    }
}

