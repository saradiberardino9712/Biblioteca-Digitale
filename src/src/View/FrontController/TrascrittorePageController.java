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

public class TrascrittorePageController {


	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button btnConsulta;

	    @FXML
	    private Button btnLogOut;

	    @FXML
	    private Button btnTrascrivi;

	    @FXML
	    void initialize() {
	        assert btnConsulta != null : "fx:id=\"btnConsulta\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
	        assert btnLogOut != null : "fx:id=\"btnLogOut\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";
	        assert btnTrascrivi != null : "fx:id=\"btnTrascrivi\" was not injected: check your FXML file 'TrascrittorePage.fxml'.";

	    }
	    
	    public void Logout(ActionEvent event) throws Exception {
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/LoginPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
	    }
	
}