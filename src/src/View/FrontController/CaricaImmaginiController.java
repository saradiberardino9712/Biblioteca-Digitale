package View.FrontController;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
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
    private Button btnSeleziona2;

    @FXML
    void initialize() {
        assert listCarica != null : "fx:id=\"txtCarica\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert btnSeleziona1 != null : "fx:id=\"btnSeleziona1\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert btnSeleziona2 != null : "fx:id=\"btnSeleziona2\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";

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
    
    public void Seleziona2(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().addAll(new ExtensionFilter("FILE JPG", "*.jpg"));
    	List<File> selectedFiles = fc.showOpenMultipleDialog(null);
    	
    	if(selectedFiles != null) {
    		for(int i = 0; i < selectedFiles.size(); i++) {
    		listCarica.getItems().add(selectedFiles.get(i).getAbsolutePath());
    		}
    	}
    	else {
    		System.out.println("file is not valid");
    	}
    }
}
