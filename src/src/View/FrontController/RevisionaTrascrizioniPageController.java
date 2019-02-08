package View.FrontController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_revisione_trascrizione;
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

public class RevisionaTrascrizioniPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listtrascrizioni;

    @FXML
    private Button btnConferma;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private HTMLEditor text;

    @FXML
    void initialize() {
        assert listtrascrizioni != null : "fx:id=\"listtrascrizioni\" was not injected: check your FXML file 'RevisionaTrascrizioniPage.fxml'.";
        assert btnConferma != null : "fx:id=\"btnConferma\" was not injected: check your FXML file 'RevisionaTrascrizioniPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'RevisionaTrascrizioniPage.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'RevisionaTrascrizioniPage.fxml'.";
        carica();
    }
    
    public void carica() {
    	String s;
    	ObservableList<String> lista= FXCollections.observableArrayList();
    	for(TestoDigitale t:controller_revisione_trascrizione.traesaminare) {
    		s="Opera: " + t.getTitoloOpera() + ", Pagina n°: " + t.getNumpag();
    		if(!lista.contains(s))
    			lista.add(s);
    	}
    	listtrascrizioni.setItems(lista);
    }
    
    public void Indietro(ActionEvent event) {
    	Stage homepage1 = (Stage) btnConferma.getScene().getWindow();
        homepage1.close();
        Stage torna= RevisorePageController.homepage;
        torna.setIconified(false);
    }
    
    public static String selezione="no selezione";
    public void click(MouseEvent event) throws FileNotFoundException {
    	selezione=listtrascrizioni.getSelectionModel().getSelectedItem();
    	ArrayList<String> testo=controller_revisione_trascrizione.prenditesto(selezione);
    	if(testo.size()>1) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Ci sono trascrizioni fatte da vari trascrittori. Unisci le varie versioni e crea quella definitiva!!");
			alert.showAndWait();
			String stringa;
			int count=1;
			for(String s:testo) {
				stringa= text.getHtmlText()+"Versione "+ Integer.toString(count)+ "\n" +s;
				text.setHtmlText(stringa);
				count++;
			}
    	}else {
    		for(String s:testo) {
				text.setHtmlText(s);
			}
    	}
    }
    
    public void Conferma(ActionEvent event) throws IOException {
    	String testo=text.getHtmlText();
    	boolean conferma=controller_revisione_trascrizione.conferma(testo);
    	if(conferma) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Revisione trascrizione");
			alert.setHeaderText("La revisione è avvenuta con successo!!");
			alert.showAndWait();
			Stage torna=RevisorePageController.homepage;
			torna.close();
			((Node)event.getSource()).getScene().getWindow().hide();
		 	Stage primaryStage = new Stage();
		    AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RevisorePage.fxml"));
		    Scene scene = new Scene(root);
		    primaryStage.setScene(scene);
		    primaryStage.show();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
		    alert.setTitle("Revisione trascrizione");
		    alert.setHeaderText("C'è stato un problema!!");
		    alert.showAndWait();
    	}
    }
}
