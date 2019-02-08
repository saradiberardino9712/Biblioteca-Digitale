package View.FrontController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_consenso_pubblicazione;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class ConsentiPubblicazioneTrascrizionePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listaTrascrizioni;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private Button btnConsentirePubblicazione;

    @FXML
    private Button btnNonConsentirePubblicazione;

    @FXML
    private HTMLEditor text;

    @FXML
    void initialize() {
        assert listaTrascrizioni != null : "fx:id=\"listaTrascrizioni\" was not injected: check your FXML file 'ConsentiPubblicazioneTrascrizionePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'ConsentiPubblicazioneTrascrizionePage.fxml'.";
        assert btnConsentirePubblicazione != null : "fx:id=\"btnConsentirePubblicazione\" was not injected: check your FXML file 'ConsentiPubblicazioneTrascrizionePage.fxml'.";
        assert btnNonConsentirePubblicazione != null : "fx:id=\"btnNonConsentirePubblicazione\" was not injected: check your FXML file 'ConsentiPubblicazioneTrascrizionePage.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'ConsentiPubblicazioneTrascrizionePage.fxml'.";
        carica();
    }
    
    public void carica() {
    	ArrayList<TestoDigitale> listatrascrizioni=controller_consenso_pubblicazione.prenditra();
    	ObservableList<String> lista=FXCollections.observableArrayList();
    	String s;
    	for(TestoDigitale t:listatrascrizioni) {
    		s="Opera: " + t.getTitoloOpera() + ", Pagina n°: " + t.getNumpag();
    		if(!lista.contains(s))
    			lista.add(s);
    	}
    	listaTrascrizioni.setItems(lista);
    }
    
    public void Indietro(ActionEvent event) {
    	Stage homepage1 = (Stage) listaTrascrizioni.getScene().getWindow();
        homepage1.close();
        Stage torna= ManagerPageController.homepage;
        torna.setIconified(false);
    }
    
    public void click(MouseEvent event) throws FileNotFoundException {
    	String selezione=listaTrascrizioni.getSelectionModel().getSelectedItem();
    	ArrayList<String> listatesti =controller_consenso_pubblicazione.prenditesto(selezione);
    	if(listatesti.size()>1) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Ci sono trascrizioni fatte da vari trascrittori");
			alert.showAndWait();
			String stringa;
			int count=1;
			for(String s:listatesti) {
				stringa= text.getHtmlText()+"Versione "+ Integer.toString(count)+ "\n" +s;
				text.setHtmlText(stringa);
				count++;
			}
    	}else {
    		for(String s:listatesti) {
				text.setHtmlText(s);
			}
    	}
    }
    
    public void ConsentirePubblicazione(ActionEvent event) throws IOException {
    	String selezione=listaTrascrizioni.getSelectionModel().getSelectedItem();
    	boolean consenso=controller_consenso_pubblicazione.consensotra(selezione,true);
    	if(!consenso) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Consenti pubblicazione");
			alert.setHeaderText("C'è stato un errore riprovare!!");
			alert.showAndWait();
    	}
    	Stage torna= ManagerPageController.homepage;
        torna.close();
        ((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public void NonConsentirePubblicazione(ActionEvent event) throws IOException {
    	String selezione=listaTrascrizioni.getSelectionModel().getSelectedItem();
    	boolean consenso=controller_consenso_pubblicazione.consensotra(selezione,false);
    	if(!consenso) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Consenti pubblicazione");
			alert.setHeaderText("C'è stato un errore riprovare!!");
			alert.showAndWait();
    	}
    	Stage torna= ManagerPageController.homepage;
        torna.close();
        ((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}
