package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

import Business.Controller.controller_dati;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ImmaginiCorrettePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAvanti;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    void initialize() {
        assert btnAvanti != null : "fx:id=\"btnAvanti\" was not injected: check your FXML file 'ImmaginiCorrettePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'ImmaginiCorrettePage.fxml'.";

    }
    
    public void indietro(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage3 = new Stage();
		AnchorPane root3 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/SupervisorePage.fxml"));
		Scene scene3 = new Scene(root3);
		primaryStage3.setScene(scene3);
		primaryStage3.show();
    }
}
