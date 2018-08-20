package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;

public class TrascriviPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView immagine;

    @FXML
    private Button btnImmagine;
    
    @FXML
    private HTMLEditor TextEditor;


    @FXML
    void initialize() {
        assert immagine != null : "fx:id=\"immagine\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert btnImmagine != null : "fx:id=\"btnImmagine\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert TextEditor != null : "fx:id=\"TextEditor\" was not injected: check your FXML file 'TrascriviPage.fxml'.";

    }
    
    public void Immagine(ActionEvent event) {
    	Image image = new Image ("/View/img/manoscritto.jpg");
    	immagine.setImage(image);
    }
    
}
