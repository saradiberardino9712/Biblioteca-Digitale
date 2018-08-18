package View.FrontController;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import javafx.stage.FileChooser.ExtensionFilter;

public class CaricaImmaginiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView listCarica;

    @FXML
    private Button btnSeleziona1;
    
    @FXML
    private Hyperlink linkIndietro;
    
    @FXML
    private TextField txtTitoloOpera;

    @FXML
    private TextField txtNumeroPagina;
    
    @FXML
    private Button btnCaricaImmagine;
    
    @FXML
    void CaricaImmagine(ActionEvent event) {

    }


    @FXML
    void initialize() {
        assert listCarica != null : "fx:id=\"txtCarica\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert btnSeleziona1 != null : "fx:id=\"btnSeleziona1\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert txtTitoloOpera != null : "fx:id=\"txtTitoloOpera\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert txtNumeroPagina != null : "fx:id=\"txtNumeroPagina\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert btnCaricaImmagine != null : "fx:id=\"btnCaricaImmagine\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";

    }
    
    public void Seleziona1(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().addAll(new ExtensionFilter("FILE JPG", "*.jpg"));
    	File selectedFile = fc.showOpenDialog(null);
    	
    	if(selectedFile != null) {
    		listCarica.getItems().add(selectedFile.getAbsolutePath());
    	}
    	else {
    		System.out.println("file is not valid");
    	}
    }
    
    public void indietro(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage2 = new Stage();
		AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisitorePage.fxml"));
		Scene scene2 = new Scene(root2);
		primaryStage2.setScene(scene2);
		primaryStage2.show();
    }
}