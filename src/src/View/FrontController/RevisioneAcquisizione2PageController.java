package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RevisioneAcquisizione2PageController {
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

    @FXML
    private Button btnVisualizza;

    @FXML
    private Button btnAvanti;
    
    void initialize() {
        assert btnAvanti != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'RevisioneAcquisizione2PageController.fxml'.";
        assert btnVisualizza != null : "fx:id=\"btnCarica\" was not injected: check your FXML file 'RevisioneAcquisizione2PageController.fxml'.";
    }
    

    @FXML
    void avanti(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/ImmaginiCorrettePage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

    }

    @FXML
    void visualizza(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

    }


}
