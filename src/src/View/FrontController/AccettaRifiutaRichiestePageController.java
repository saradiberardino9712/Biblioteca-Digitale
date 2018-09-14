package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AccettaRifiutaRichiestePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAccetta;

    @FXML
    private Button btnRifiuta;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    void Accetta(ActionEvent event) {

    }

    @FXML
    void Rifiuta(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnAccetta != null : "fx:id=\"btnAccetta\" was not injected: check your FXML file 'AccettaRifiutaRichiestePage.fxml'.";
        assert btnRifiuta != null : "fx:id=\"btnRifiuta\" was not injected: check your FXML file 'AccettaRifiutaRichiestePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'AccettaRifiutaRichiestePage.fxml'.";

    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}
