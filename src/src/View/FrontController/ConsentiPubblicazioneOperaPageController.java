package View.FrontController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_consenso_pubblicazione;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConsentiPubblicazioneOperaPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listaOpere;

    @FXML
    private Pagination paginatoreOpere;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private Button btnConsentiPubblicazione;

    @FXML
    private Button btnNonConsentirePubblicazione;
    
    @FXML
    private VBox pubop;

    @FXML
    void initialize() {
        assert listaOpere != null : "fx:id=\"listaOpere\" was not injected: check your FXML file 'ConsentiPubblicazioneOpearaPage.fxml'.";
        assert paginatoreOpere != null : "fx:id=\"paginatoreOpere\" was not injected: check your FXML file 'ConsentiPubblicazioneOpearaPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'ConsentiPubblicazioneOpearaPage.fxml'.";
        assert btnConsentiPubblicazione != null : "fx:id=\"btnConsentiPubblicazione\" was not injected: check your FXML file 'ConsentiPubblicazioneOpearaPage.fxml'.";
        assert btnNonConsentirePubblicazione != null : "fx:id=\"btnNonConsentirePubblicazione\" was not injected: check your FXML file 'ConsentiPubblicazioneOpearaPage.fxml'.";
        assert pubop != null : "fx:id=\"pubop\" was not injected: check your FXML file 'ConsentiPubblicazioneOperaPage.fxml'.";
        carica();
    }
    
    public void carica() {
    	ArrayList<String> listaopere=controller_consenso_pubblicazione.prendi();
    	ObservableList<String> lista=FXCollections.observableArrayList();
    	for(String s: listaopere) {
    		lista.add(s);
    	}
    	listaOpere.setItems(lista);
    }

    public void click(MouseEvent event) throws FileNotFoundException {
    	String titolo=listaOpere.getSelectionModel().getSelectedItem();
    	ArrayList<Immagine> img=controller_consenso_pubblicazione.visualizza(titolo);
    	int count=0;
    	ArrayList<ImageView> images= new ArrayList<>();
    	for(Immagine i:img) {
    		count+=1;
    		ImageView imgView=new ImageView();
    		imgView.setFitHeight(270);
    		imgView.setFitWidth(225);
    		FileInputStream inputstream = new FileInputStream(i.getUrl()); 
    		Image image = new Image(inputstream); 
    		imgView.setImage(image);
    		images.add(imgView);
    	}
    	paginatoreOpere.pageCountProperty().set(count);
    	paginatoreOpere.maxPageIndicatorCountProperty().set(count);
    	paginatoreOpere.setPageFactory(n->images.get(n));
    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	Stage homepage1 = (Stage) pubop.getScene().getWindow();
        homepage1.close();
        Stage torna= ManagerPageController.homepage;
        torna.setIconified(false);
	}
    
    public void ConsentiPubblicazione(ActionEvent event) throws InterruptedException, IOException {
    	String titolo=listaOpere.getSelectionModel().getSelectedItem();
    	boolean consenso=controller_consenso_pubblicazione.consenso(titolo,true);
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
    
    public void NonConsentirePubblicazione(ActionEvent event) throws InterruptedException, IOException {
    	String titolo=listaOpere.getSelectionModel().getSelectedItem();
    	boolean consenso=controller_consenso_pubblicazione.consenso(titolo,false);
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
