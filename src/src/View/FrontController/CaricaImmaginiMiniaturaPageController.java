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

public class CaricaImmaginiMiniaturaPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink btnIndietro;

    @FXML
    private Button btnPaginatore;
    
    @FXML
    private Button btnCarica;
    
    @FXML
    void CaricaDefinitiva(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnIndietro != null : "fx:id=\"btnIndietro\" was not injected: check your FXML file 'CaricaImmaginiMiniaturaPage.fxml'.";
        assert btnPaginatore != null : "fx:id=\"btnPaginatore\" was not injected: check your FXML file 'CaricaImmaginiMiniaturaPage.fxml'.";
        assert btnCarica != null : "fx:id=\"btnCarica\" was not injected: check your FXML file 'CaricaImmaginiMiniaturaPage.fxml'.";

    }
    
    public void indietro(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/CaricaImmagini.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public void paginatore(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/CaricaImmaginePaginatorePage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}
