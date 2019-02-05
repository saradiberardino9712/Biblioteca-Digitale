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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GestioneLivelloPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNomeUtente;

    @FXML
    private Spinner<Integer> spinnerLivello;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private Button btnModifica;

    @FXML
    void Modifica(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtNomeUtente != null : "fx:id=\"txtNomeUtente\" was not injected: check your FXML file 'GestioneLivelloPage.fxml'.";
        assert spinnerLivello != null : "fx:id=\"spinnerLivello\" was not injected: check your FXML file 'GestioneLivelloPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'GestioneLivelloPage.fxml'.";
        assert btnModifica != null : "fx:id=\"btnModifica\" was not injected: check your FXML file 'GestioneLivelloPage.fxml'.";
        SpinnerValueFactory<Integer> spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        this.spinnerLivello.setValueFactory(spinner);
    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage6 = new Stage();
		BorderPane root6 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
		Scene scene6 = new Scene(root6);
		primaryStage6.setScene(scene6);
		primaryStage6.show();
    }
    
}
