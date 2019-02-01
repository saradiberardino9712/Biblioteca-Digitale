package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Business.Controller.controller_revisione_acquisizione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RiepilogoRevisioneAcquisizionePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnVisualizzaMiniaturaCorrette;

    @FXML
    private Button btnVisualizzaPaginatoreCorrette;

    @FXML
    private Button btnVisualizzaMiniaturaSbagliate;

    @FXML
    private Button btnVisualizzaPaginatoreSbagliate;

    @FXML
    private Button btnAnnulla;

    @FXML
    private Button btnCompletaRevisione;

    @FXML
    private Button btnControllaAltre;
    
    @FXML
    private VBox riprev;

    @FXML
    void initialize() {
        assert btnVisualizzaMiniaturaCorrette != null : "fx:id=\"btnVisualizzaMiniaturaCorrette\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert btnVisualizzaPaginatoreCorrette != null : "fx:id=\"btnVisualizzaPaginatoreCorrette\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert btnVisualizzaMiniaturaSbagliate != null : "fx:id=\"btnVisualizzaMiniaturaSbagliate\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert btnVisualizzaPaginatoreSbagliate != null : "fx:id=\"btnVisualizzaPaginatoreSbagliate\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert btnAnnulla != null : "fx:id=\"btnAnnulla\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert btnCompletaRevisione != null : "fx:id=\"btnCompletaRevisione\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert riprev != null : "fx:id=\"riprev\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert btnControllaAltre != null : "fx:id=\"btnControllaAltre\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
    }
    
    public void ControllaAltre(ActionEvent event) throws IOException {
        int conteggioC=controller_revisione_acquisizione.imgcorrette.size();
        int conteggioS=controller_revisione_acquisizione.imgsbagliate.size();
    	if(conteggioC>5 && conteggioS>5) {
    		btnControllaAltre.setDisable(true);
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Si possono controllare al massimo 6 immagini sbagliate e 6 corrette!!");
			alert.showAndWait();
    	}else if (conteggioS>5) {
    		if(controller_revisione_acquisizione.imgesaminare.isEmpty()) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Errore");
    			alert.setHeaderText("Non ci sono pagine di opere da controllare!!");
    			alert.showAndWait();
    		}else {
	    		RevisioneAcquisizionePageController.basta="sbagliate";
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Attenzione!!");
				alert.setHeaderText("Si possono controllare al massimo 6 immagini sbagliate!! Adesso puoi controllare solo se sono corrette");
				alert.showAndWait();
	    		((Node)event.getSource()).getScene().getWindow().hide();
	        	Stage primaryStage3 = new Stage();
	        	BorderPane root3 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePage.fxml"));
	    		Scene scene3 = new Scene(root3);
	    		primaryStage3.setScene(scene3);
	    		primaryStage3.show();
    		}
    	}else if(conteggioC>5) {
    		if(controller_revisione_acquisizione.imgesaminare.isEmpty()) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Errore");
    			alert.setHeaderText("Non ci sono pagine di opere da controllare!!");
    			alert.showAndWait();
    		}else {
	    		RevisioneAcquisizionePageController.basta="corrette";
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Attenzione!!");
				alert.setHeaderText("Si possono controllare al massimo 6 immagini corrette!! Adesso puoi controllare solo se sono sbagliate");
				alert.showAndWait();
	    		((Node)event.getSource()).getScene().getWindow().hide();
	        	Stage primaryStage3 = new Stage();
	        	BorderPane root3 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePage.fxml"));
	    		Scene scene3 = new Scene(root3);
	    		primaryStage3.setScene(scene3);
	    		primaryStage3.show();
    		}
    	}else {
    		if(controller_revisione_acquisizione.imgesaminare.isEmpty()) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Errore");
    			alert.setHeaderText("Non ci sono pagine di opere da controllare!!");
    			alert.showAndWait();
    		}else {
    			((Node)event.getSource()).getScene().getWindow().hide();
            	Stage primaryStage3 = new Stage();
            	BorderPane root3 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePage.fxml"));
        		Scene scene3 = new Scene(root3);
        		primaryStage3.setScene(scene3);
        		primaryStage3.show();
    		}
    	}
    }
    
    public static String esito;
    public void VisualizzaMiniaturaCorrette(ActionEvent event) throws Exception {
    	if(controller_revisione_acquisizione.imgcorrette.isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Al momento non ci sono immagini corrette.");
			alert.showAndWait();
    	}else {
	    	esito="corrette";
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage3 = new Stage();
			BorderPane root3 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizioneMiniaturaPage.fxml"));
			Scene scene3 = new Scene(root3);
			primaryStage3.setScene(scene3);
			primaryStage3.show();
    	}
    }
    
    public void VisualizzaMiniaturaSbagliate(ActionEvent event) throws Exception {
    	if(controller_revisione_acquisizione.imgsbagliate.isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Al momento non ci sono immagini sbagliate.");
			alert.showAndWait();
    	}else {
	    	esito="sbagliate";
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage3 = new Stage();
	    	BorderPane root3 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizioneMiniaturaPage.fxml"));
			Scene scene3 = new Scene(root3);
			primaryStage3.setScene(scene3);
			primaryStage3.show();
    	}
    }
    
    public void VisualizzaPaginatoreCorrette(ActionEvent event) throws Exception {
    	if(controller_revisione_acquisizione.imgcorrette.isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Al momento non ci sono immagini corrette.");
			alert.showAndWait();
    	}else {
	    	esito="corrette";
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage3 = new Stage();
	    	BorderPane root3 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePaginatorePage.fxml"));
			Scene scene3 = new Scene(root3);
			primaryStage3.setScene(scene3);
			primaryStage3.show();
    	}
    }
    
    public void VisualizzaPaginatoreSbagliate(ActionEvent event) throws Exception {
    	if(controller_revisione_acquisizione.imgsbagliate.isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Al momento non ci sono immagini sbagliate.");
			alert.showAndWait();
    	}else {
	    	esito="sbagliate";
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage3 = new Stage();
	    	BorderPane root3 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePaginatorePage.fxml"));
			Scene scene3 = new Scene(root3);
			primaryStage3.setScene(scene3);
			primaryStage3.show();
    	}
    }
    
    public void CompletaRevisione(ActionEvent event) throws IOException, InterruptedException {
    	boolean completa=controller_revisione_acquisizione.completa();
    	if(completa) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Revisione acquisizione");
			alert.setHeaderText("La procedura è andata a buon fine.");
			alert.showAndWait();
	        Stage torna= SupervisorePageController.homepage;
	        torna.close();
	        ((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/javaFX/SupervisorePage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    	}else{
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("C'è stato un errore");
			alert.showAndWait();
    	}
    }

    public void annulla(ActionEvent event) throws IOException, InterruptedException {
    	boolean annulla=controller_revisione_acquisizione.annulla();
    	if(annulla) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Annulla");
			alert.setHeaderText("La procedura è stata annullata");
			alert.showAndWait();
	        Stage torna= SupervisorePageController.homepage;
	        torna.close();
	        ((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/javaFX/SupervisorePage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    	}else{
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("C'è stato un errore");
			alert.showAndWait();
    	}
    }
}
