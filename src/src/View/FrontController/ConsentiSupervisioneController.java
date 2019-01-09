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
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConsentiSupervisioneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<?> listImmAcq;

    @FXML
    private Button btnConsenti;

    @FXML
    private Button btnNegaConsenso;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    void consenti(ActionEvent event) {

    }

    @FXML
    void negaConsenso(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert listImmAcq != null : "fx:id=\"listImmAcq\" was not injected: check your FXML file 'ConsentiSupervisionePage.fxml'.";
        assert btnConsenti != null : "fx:id=\"btnConsenti\" was not injected: check your FXML file 'ConsentiSupervisionePage.fxml'.";
        assert btnNegaConsenso != null : "fx:id=\"btnNegaConsenso\" was not injected: check your FXML file 'ConsentiSupervisionePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'ConsentiSupervisionePage.fxml'.";

    }
    
    public void indietro(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage3 = new Stage();
		BorderPane root3 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
		Scene scene3 = new Scene(root3);
		primaryStage3.setScene(scene3);
		primaryStage3.show();
    }
}
