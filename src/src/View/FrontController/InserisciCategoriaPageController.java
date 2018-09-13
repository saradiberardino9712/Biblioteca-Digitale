package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;
import Business.Controller.controller_caricamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class InserisciCategoriaPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCategoria;

    @FXML
    private Button btnInserisci;

    @FXML
    void initialize() {
        assert txtCategoria != null : "fx:id=\"txtCategoria\" was not injected: check your FXML file 'InserisciCategoriaPage.fxml'.";
        assert btnInserisci != null : "fx:id=\"btnInserisci\" was not injected: check your FXML file 'InserisciCategoriaPage.fxml'.";
    }
    
    public void Inserisci(ActionEvent event) throws Exception {
    	boolean categoria=controller_caricamento.inseriscicategoria(txtCategoria);
    	if(categoria) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Caricamento metadati");
			alert.setHeaderText("L'inserimento della categoria � andato a buon fine");
			alert.showAndWait();
    	} 
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Caricamento metadati");
			alert.setHeaderText("Controllare se la categoria � gi� presente. Altrimenti riprovare!!");
			alert.showAndWait();
			
    	}
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
