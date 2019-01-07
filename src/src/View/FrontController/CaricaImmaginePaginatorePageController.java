package View.FrontController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_caricamento;
import Business.Model.Immagine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CaricaImmaginePaginatorePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pagination pagination;
    
    @FXML
    private Button btnCaricaAltre;

    @FXML
    private Button btnCarica;

    @FXML
    private Button btnMiniatura;

    @FXML
    void initialize() throws FileNotFoundException {
    	assert pagination != null : "fx:id=\"pagination\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        assert btnCaricaAltre != null : "fx:id=\"btnCaricaAltre\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        assert btnCarica != null : "fx:id=\"btnCarica\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        assert btnMiniatura != null : "fx:id=\"btnMiniatura\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        visualizza();
    }
    
    public ArrayList<Immagine> img;

    public void visualizza() throws FileNotFoundException {
    	img=controller_caricamento.visualizzariepilogo();
    	int count=0;
    	ArrayList<ImageView> images= new ArrayList<>();
    	for(Immagine i:img) {
    		count+=1;
    		ImageView imgView=new ImageView();
    		imgView.setFitHeight(361);
    		imgView.setFitWidth(313);
    		FileInputStream inputstream = new FileInputStream(i.getUrl()); 
    		Image image = new Image(inputstream); 
    		imgView.setImage(image);
    		images.add(imgView);
    	}
    	pagination.pageCountProperty().set(count);
    	pagination.maxPageIndicatorCountProperty().set(6);
    	pagination.setPageFactory(n->images.get(n));
    }
  
    public void CaricaAltre(ActionEvent event) throws Exception {
    	CaricaImmaginiMiniaturaPageController.conteggio+=1;
    	if(CaricaImmaginiMiniaturaPageController.conteggio>5) {
    		btnCaricaAltre.setDisable(true);
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Si possono caricare al massimo 6 immagini!!");
			alert.showAndWait();
    	}else {
    		boolean verifica=controller_caricamento.verifica();
    		if(verifica) {
    			((Node)event.getSource()).getScene().getWindow().hide();
    			Stage primaryStage = new Stage();
    			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisizioneImmaginePage.fxml"));
    			Scene scene = new Scene(root);
    			primaryStage.setScene(scene);
    			primaryStage.show();
    		}else {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Errore");
    			alert.setHeaderText("Non ci sono pagine di opere da acquisire!!");
    			alert.showAndWait();
    		}
    	}
    }
    
    public void VisualizzaMiniatura(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/CaricaImmaginiMiniaturaPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public void CaricaDefinitiva(ActionEvent event) throws Exception {
    	CaricaImmaginiMiniaturaPageController.conteggio=0;
    	boolean definitivo=controller_caricamento.caricadefinitiva(img);
    	if(definitivo) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Caricamento immagine");
			alert.setHeaderText("Il caricamento dell'immagine/i è andato a buon fine!!");
			alert.showAndWait();
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage2 = new Stage();
			AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisitorePage.fxml"));
			Scene scene2 = new Scene(root2);
			primaryStage2.setScene(scene2);
			primaryStage2.show();
    	}else {
    		controller_caricamento.annulla();
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Caricamento immagine");
			alert.setHeaderText("C'è stato un errore riprovare!!");
			alert.showAndWait();
    	}
    }
}
