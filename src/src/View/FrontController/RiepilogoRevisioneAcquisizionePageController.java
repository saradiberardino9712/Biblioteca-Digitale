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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RiepilogoRevisioneAcquisizionePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnVisualizzaMiniaturaCorrette;

    @FXML
    private Button btnVisualizzaPaginatoreCorrette;

    @FXML
    private Button btnVisualizzaMiniaturaSbagliate;

    @FXML
    private Button btnVisualizzaPaginatoreSbagliate;

    @FXML
    private Button btnAnnulla;

    @FXML
    private Button btnCompletaRevisione;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    void CompletaRevisione(ActionEvent event) {

    }

    @FXML
    void annulla(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnVisualizzaMiniaturaCorrette != null : "fx:id=\"btnVisualizzaMiniaturaCorrette\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert btnVisualizzaPaginatoreCorrette != null : "fx:id=\"btnVisualizzaPaginatoreCorrette\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert btnVisualizzaMiniaturaSbagliate != null : "fx:id=\"btnVisualizzaMiniaturaSbagliate\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert btnVisualizzaPaginatoreSbagliate != null : "fx:id=\"btnVisualizzaPaginatoreSbagliate\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert btnAnnulla != null : "fx:id=\"btnAnnulla\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert btnCompletaRevisione != null : "fx:id=\"btnCompletaRevisione\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'RiepilogoRevisioneAcquisizionePage.fxml'.";

    }
    
    public void indietro(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage3 = new Stage();
		AnchorPane root3 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/SupervisorePage.fxml"));
		Scene scene3 = new Scene(root3);
		primaryStage3.setScene(scene3);
		primaryStage3.show();
    }
    
    public void VisualizzaMiniaturaCorrette(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage3 = new Stage();
		AnchorPane root3 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePage.fxml"));
		Scene scene3 = new Scene(root3);
		primaryStage3.setScene(scene3);
		primaryStage3.show();
    }
    
    public void VisualizzaMiniaturaSbagliate(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage3 = new Stage();
		AnchorPane root3 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePage.fxml"));
		Scene scene3 = new Scene(root3);
		primaryStage3.setScene(scene3);
		primaryStage3.show();
    }
    
    public void VisualizzaPaginatoreCorrette(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage3 = new Stage();
		AnchorPane root3 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizione2Page.fxml"));
		Scene scene3 = new Scene(root3);
		primaryStage3.setScene(scene3);
		primaryStage3.show();
    }
    
    public void VisualizzaPaginatoreSbagliate(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage3 = new Stage();
		AnchorPane root3 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizione2Page.fxml"));
		Scene scene3 = new Scene(root3);
		primaryStage3.setScene(scene3);
		primaryStage3.show();
    }
}
