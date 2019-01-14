package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AcquisizioniNegatePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<?> listAcquisizioniNegate;

    @FXML
    private Hyperlink linkIndietro;


    @FXML
    void initialize() {
        assert listAcquisizioniNegate != null : "fx:id=\"listAcquisizioniNegate\" was not injected: check your FXML file 'AcquisizioniNegatePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'AcquisizioniNegatePage.fxml'.";

    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage2 = new Stage();
		AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisitorePage.fxml"));
		Scene scene2 = new Scene(root2);
		primaryStage2.setScene(scene2);
		primaryStage2.show();
    }
}
