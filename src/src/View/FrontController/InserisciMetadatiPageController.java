package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InserisciMetadatiPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtTitolo;

    @FXML
    private TextField txtAnno;

    @FXML
    private TextField txtAutore;

    @FXML
    private TextField txtNpagine;

    @FXML
    private Hyperlink txtIndietro;

    @FXML
    private Button btnCaricaMetadati;

    @FXML
    private ComboBox<String> combobox;
    
    ObservableList<String> list = FXCollections.observableArrayList("Altro");

    @FXML
    void CaricaMetadati(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtTitolo != null : "fx:id=\"txtTitolo\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert txtAutore != null : "fx:id=\"txtAutore\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert txtNpagine != null : "fx:id=\"txtNpagine\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert txtIndietro != null : "fx:id=\"txtIndietro\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert btnCaricaMetadati != null : "fx:id=\"btnCaricaMetadati\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert combobox != null : "fx:id=\"combobox\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        combobox.setItems(list);

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
