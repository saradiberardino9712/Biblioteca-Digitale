package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Business.Controller.controller_consultazione;
import Business.Controller.controller_ricerca;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RicercaOperePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txttesto;

    @FXML
    private Button btnCerca;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private ListView<String> listRisultato;

    @FXML
    private CheckBox checkcatalogo;

    @FXML
    private CheckBox checktesto;

    @FXML
    void initialize() {
        assert txttesto != null : "fx:id=\"txttesto\" was not injected: check your FXML file 'RicercaOperePage.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'RicercaOperePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'RicercaOperePage.fxml'.";
        assert listRisultato != null : "fx:id=\"listRisultato\" was not injected: check your FXML file 'RicercaOperePage.fxml'.";
        assert checkcatalogo != null : "fx:id=\"checkcatalogo\" was not injected: check your FXML file 'RicercaOperePage.fxml'.";
        assert checktesto != null : "fx:id=\"checktesto\" was not injected: check your FXML file 'RicercaOperePage.fxml'.";
        checkcatalogo.setSelected(true);
        checktesto.setSelected(true);
    }
    
    public void Indietro(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/javaFX/UtenteBasePage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    public void Cerca(ActionEvent event) {
    	listRisultato.getItems().clear();
    	boolean catalogo=checkcatalogo.isSelected();
    	boolean testo=checktesto.isSelected();
    	if(catalogo || testo) {
		    boolean cerca=controller_ricerca.cerca(catalogo,testo,txttesto);
		    if(cerca) {
		    	ObservableList<String> list=FXCollections.observableArrayList();
		    	for(String s:controller_ricerca.lista) {
		    		list.add(s);
		    	}
		    	listRisultato.setItems(list);
		    }else {
		    	Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ricerca");
				alert.setHeaderText("Non ci sono risultati!!");
				alert.showAndWait();
		    }
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione");
			alert.setHeaderText("Selezionare il tipo di ricerca!! (nel catalogo e/o nel testo)");
			alert.showAndWait();
    	}
    }
    
    public void click(MouseEvent event) throws IOException {
    	String frase=listRisultato.getSelectionModel().getSelectedItem();
    	boolean consultazione=controller_consultazione.avvia(frase);
    	if(consultazione) {
    		((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/consultazionePage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("C'è stato un problema");
			alert.showAndWait();
    	}
    }
}
