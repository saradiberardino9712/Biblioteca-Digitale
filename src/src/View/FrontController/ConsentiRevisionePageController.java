package View.FrontController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_consenso_revisione;
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
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class ConsentiRevisionePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listtrascrizioni;

    @FXML
    private Button btnConsenti;

    @FXML
    private Button btnNegaConsenso;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private VBox consup;

    @FXML
    private HTMLEditor text;

    @FXML
    void initialize() {
        assert listtrascrizioni != null : "fx:id=\"listtrascrizioni\" was not injected: check your FXML file 'Consenti revisione.fxml'.";
        assert btnConsenti != null : "fx:id=\"btnConsenti\" was not injected: check your FXML file 'Consenti revisione.fxml'.";
        assert btnNegaConsenso != null : "fx:id=\"btnNegaConsenso\" was not injected: check your FXML file 'Consenti revisione.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'Consenti revisione.fxml'.";
        assert consup != null : "fx:id=\"consup\" was not injected: check your FXML file 'Consenti revisione.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'Consenti revisione.fxml'.";
        carica();
    }
    
    public void carica() {
    	listtrascrizioni.getItems().clear();
    	controller_consenso_revisione.verifica();
    	String s;
    	ObservableList<String> lista= FXCollections.observableArrayList();
    	for(TestoDigitale t:controller_consenso_revisione.listatrascrizioni) {
    		s="Opera: " + t.getTitoloOpera() + ", Pagina n°: " + t.getNumpag();
    		if(!lista.contains(s))
    			lista.add(s);
    	}
    	listtrascrizioni.setItems(lista);
    }
    
    public void indietro(ActionEvent event) throws Exception {
    	Stage homepage1 = (Stage) consup.getScene().getWindow();
        homepage1.close();
        Stage torna= ManagerPageController.homepage;
        torna.setIconified(false);
    }
    
    public static String selezione="no selezione";
    public void click(MouseEvent event) throws FileNotFoundException {
    	selezione=listtrascrizioni.getSelectionModel().getSelectedItem();
    	ArrayList<String> testo=controller_consenso_revisione.prenditesto(selezione);
    	if(testo.size()>1) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Ci sono trascrizioni fatte da vari trascrittori");
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
    
    public void consenti(ActionEvent event) throws IOException {
    	if(selezione.contains("no selezione")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Non hai selezionato nessuna trascrizione!");
			alert.showAndWait();
    	}else {
	    	boolean consenti=controller_consenso_revisione.consenso("yes");
	    	if(consenti) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Consenso Revisione");
				alert.setHeaderText("Consenso ammesso!!");
				alert.showAndWait();
				Stage torna= ManagerPageController.homepage;
				torna.close();
				((Node)event.getSource()).getScene().getWindow().hide();
			    Stage primaryStage3 = new Stage();
				BorderPane root3 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
				Scene scene3 = new Scene(root3);
				primaryStage3.setScene(scene3);
				primaryStage3.show();
	    	}else {
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Attenzione!!");
				alert.setHeaderText("C'è un errore riprovare!!");
				alert.showAndWait();
	    	}
    	}
    }
    
    public void negaConsenso(ActionEvent event) throws IOException {  	
    	if(selezione.contains("no selezione")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Non hai selezionato nessuna trascrizione!");
			alert.showAndWait();
    	}else {
	    	boolean consenti=controller_consenso_revisione.consenso("no");
	    	if(consenti) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Consenso Revisione");
				alert.setHeaderText("Consenso non ammesso!!");
				alert.showAndWait();
				Stage torna= ManagerPageController.homepage;
				torna.close();
				((Node)event.getSource()).getScene().getWindow().hide();
			    Stage primaryStage3 = new Stage();
				BorderPane root3 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
				Scene scene3 = new Scene(root3);
				primaryStage3.setScene(scene3);
				primaryStage3.show();
	    	}else {
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Attenzione!!");
				alert.setHeaderText("C'è un errore riprovare!!");
				alert.showAndWait();
	    	}
    	}
    }
}

