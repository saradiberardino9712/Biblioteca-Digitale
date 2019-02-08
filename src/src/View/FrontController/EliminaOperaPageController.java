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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EliminaOperaPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnElimina;

    @FXML
    private TextField lblNomeOpera;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    void Elimina(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnElimina != null : "fx:id=\"btnElimina\" was not injected: check your FXML file 'EliminaOperaPage.fxml'.";
        assert lblNomeOpera != null : "fx:id=\"lblNomeOpera\" was not injected: check your FXML file 'EliminaOperaPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'EliminaOperaPage.fxml'.";

    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage6 = new Stage();
		BorderPane root6 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AmministratorePage.fxml"));
		Scene scene6 = new Scene(root6);
		primaryStage6.setScene(scene6);
		primaryStage6.show();
    }
}
