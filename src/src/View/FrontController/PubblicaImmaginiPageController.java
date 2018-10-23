package View.FrontController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PubblicaImmaginiPageController {

    @FXML
    private Button btnPubblica;

    @FXML
    private Hyperlink linkIndietro;
    
    void initialize() {
        assert btnPubblica != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'RevisioneAcquisizione2PageController.fxml'.";
        assert linkIndietro != null : "fx:id=\"btnCarica\" was not injected: check your FXML file 'RevisioneAcquisizione2PageController.fxml'.";
    }
    
    @FXML
    public void Indietro(ActionEvent event) throws Exception {
		
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage3 = new Stage();
		AnchorPane root3 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/ImmaginiCorrettePage.fxml"));
		Scene scene3 = new Scene(root3);
		primaryStage3.setScene(scene3);
		primaryStage3.show();
		}
    
    

    @FXML
    void pubblica(ActionEvent event) {
    	
    }

}
