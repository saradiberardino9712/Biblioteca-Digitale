package View.FrontController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Business.Controller.controller_consenso_supervisione;
import Business.Model.Immagine;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConsentiSupervisionePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listImmAcq;

    @FXML
    private Button btnConsenti;

    @FXML
    private Button btnNegaConsenso;

    @FXML
    private Hyperlink linkIndietro;
    
    @FXML
    private ImageView img;
    
    @FXML
    private VBox consup;

    @FXML
    void initialize() {
        assert listImmAcq != null : "fx:id=\"listImmAcq\" was not injected: check your FXML file 'ConsentiSupervisionePage.fxml'.";
        assert btnConsenti != null : "fx:id=\"btnConsenti\" was not injected: check your FXML file 'ConsentiSupervisionePage.fxml'.";
        assert btnNegaConsenso != null : "fx:id=\"btnNegaConsenso\" was not injected: check your FXML file 'ConsentiSupervisionePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'ConsentiSupervisionePage.fxml'.";
        assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'ConsentiSupervisionePage.fxml'.";
        assert consup != null : "fx:id=\"consup\" was not injected: check your FXML file 'ConsentiSupervisionePage.fxml'.";
        carica();
    }
    
    public void carica() {
    	controller_consenso_supervisione.verifica();
    	String s;
    	ObservableList<String> lista= FXCollections.observableArrayList();
    	for(Immagine i:controller_consenso_supervisione.listaimg) {
    		s="Opera: " + i.getTitoloOpera() + ", Pagina n°: " + i.getNumeropagina();
    		lista.add(s);
    	}
    	listImmAcq.setItems(lista);
    }
    
    public void indietro(ActionEvent event) throws Exception {
    	Stage homepage1 = (Stage) consup.getScene().getWindow();
        homepage1.close();
        Stage torna= ManagerPageController.homepage;
        torna.setIconified(false);
    }
    
    public void click(MouseEvent event) throws FileNotFoundException {
    	String selezione=listImmAcq.getSelectionModel().getSelectedItem();
    	String url=controller_consenso_supervisione.prendiurl(selezione);
    	if(url!=null) {
    		FileInputStream inputstream = new FileInputStream(url); 
    		Image image = new Image(inputstream);
    		img.setImage(image);
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("C'è un errore riprovare!!");
			alert.showAndWait();
    	}
    }
    
    public void consenti(ActionEvent event) throws IOException {
    	boolean consenti=controller_consenso_supervisione.consenso("yes");
    	if(consenti) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Consenso Supervisione");
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
    
    public void negaConsenso(ActionEvent event) throws IOException {
    	boolean nonconsenti=controller_consenso_supervisione.consenso("no");
    	if(nonconsenti) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Consenso Supervisione");
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
