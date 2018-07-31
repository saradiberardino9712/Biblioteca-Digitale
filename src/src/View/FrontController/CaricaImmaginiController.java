package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CaricaImmaginiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSeleziona1;

    @FXML
    private Button btnSeleziona2;

    @FXML
    void initialize() {
        assert btnSeleziona1 != null : "fx:id=\"btnSeleziona1\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert btnSeleziona2 != null : "fx:id=\"btnSeleziona2\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";

    }
}


