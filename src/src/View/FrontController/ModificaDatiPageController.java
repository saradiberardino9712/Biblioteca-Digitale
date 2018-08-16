package View.FrontController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ModificaDatiPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtIndirizzo;

    @FXML
    private TextField txtTitoloStudio;

    @FXML
    private TextField txtProfessione;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnModificaDati;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private DatePicker datapicker;

    @FXML
    void ConfermaModifica(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'ModificaDatiPage.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'ModificaDatiPage.fxml'.";
        assert txtIndirizzo != null : "fx:id=\"txtIndirizzo\" was not injected: check your FXML file 'ModificaDatiPage.fxml'.";
        assert txtTitoloStudio != null : "fx:id=\"txtTitoloStudio\" was not injected: check your FXML file 'ModificaDatiPage.fxml'.";
        assert txtProfessione != null : "fx:id=\"txtProfessione\" was not injected: check your FXML file 'ModificaDatiPage.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'ModificaDatiPage.fxml'.";
        assert btnModificaDati != null : "fx:id=\"btnModificaDati\" was not injected: check your FXML file 'ModificaDatiPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'ModificaDatiPage.fxml'.";
        assert datapicker != null : "fx:id=\"datapicker\" was not injected: check your FXML file 'ModificaDatiPage.fxml'.";
        datapicker.setValue(LocalDate.now());

    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/VisualizzaDatiPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}
