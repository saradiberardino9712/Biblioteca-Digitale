package View.FrontController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import Business.Controller.controller_pubblicazione_opera;
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
import javafx.stage.Stage;

public class PubblicaImmaginiPageController {

    @FXML
    private Button btnPubblica;

    @FXML
    private Hyperlink linkIndietro;
    
    @FXML
    private ListView<String> listopere;
    
    @FXML
    private Pagination pagination;
    
    @FXML
    void initialize() {
    	assert btnPubblica != null : "fx:id=\"btnPubblica\" was not injected: check your FXML file 'PubblicaImmaginiPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'PubblicaImmaginiPage.fxml'.";
        assert pagination != null : "fx:id=\"pagination\" was not injected: check your FXML file 'PubblicaImmaginiPage.fxml'.";
        assert listopere != null : "fx:id=\"listopere\" was not injected: check your FXML file 'PubblicaImmaginiPage.fxml'.";
        visualizza();
    }
    
    public void visualizza() {
    	ArrayList<String> titoli=controller_pubblicazione_opera.prendi();
    	ObservableList<String> lista=FXCollections.observableArrayList();
    	for(String s:titoli) {
    		lista.add(s);
    	}
    	listopere.setItems(lista);
    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	Stage page=(Stage) listopere.getScene().getWindow();
    	page.close();
    	Stage torna=SupervisorePageController.homepage;
    	torna.setIconified(false);
	}
    
    public void click(MouseEvent event) throws FileNotFoundException {
    	String titolo=listopere.getSelectionModel().getSelectedItem();
    	ArrayList<Immagine> img=controller_pubblicazione_opera.prendiimg(titolo);
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
    	pagination.pageCountProperty().set(count);
    	pagination.maxPageIndicatorCountProperty().set(count);
    	pagination.setPageFactory(n->images.get(n));
    }
    
    public void pubblica(ActionEvent event) throws IOException {
    	String titolo=listopere.getSelectionModel().getSelectedItem();
    	boolean pubblica=controller_pubblicazione_opera.pubblica(titolo);
    	if(pubblica) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Pubblica opera");
			alert.setHeaderText("La pubblicazione è andata a buon fine!!");
			alert.showAndWait();
			Stage torna=SupervisorePageController.homepage;
			torna.close();
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
    		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/SupervisorePage.fxml"));
    		Scene scene = new Scene(root);
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Pubblica opera");
			alert.setHeaderText("C'è stato un errore riprovare!!");
			alert.showAndWait();
    	}
    }

}
