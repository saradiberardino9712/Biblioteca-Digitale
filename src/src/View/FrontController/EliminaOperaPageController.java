package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Business.Controller.controller_eliminazione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EliminaOperaPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ListView<String> listopere;

    @FXML
    private Button btnElimina;

    @FXML
    private TextField lblNomeOpera;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    void initialize() {
        assert btnElimina != null : "fx:id=\"btnElimina\" was not injected: check your FXML file 'EliminaOperaPage.fxml'.";
        assert lblNomeOpera != null : "fx:id=\"lblNomeOpera\" was not injected: check your FXML file 'EliminaOperaPage.fxml'.";
        assert listopere != null : "fx:id=\"listopere\" was not injected: check your FXML file 'EliminaOperaPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'EliminaOperaPage.fxml'.";
        carica();
    }
    
    public void carica() {
    	ArrayList<String> opere=controller_eliminazione.prendi();
    	ObservableList<String> list=FXCollections.observableArrayList();
    	for(String s:opere) {
    		list.add(s);
    	}
    	listopere.setItems(list);
    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage6 = new Stage();
		BorderPane root6 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AmministratorePage.fxml"));
		Scene scene6 = new Scene(root6);
		primaryStage6.setScene(scene6);
		primaryStage6.show();
    }
    
    public static String selezione="no selezione";
    public void click(MouseEvent event) {
    	selezione=listopere.getSelectionModel().getSelectedItem();
    	lblNomeOpera.setText(selezione);
    }
    
    public void Elimina(ActionEvent event) throws IOException {
    	if(selezione.equals("no selezione")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Elimina Opera");
			alert.setHeaderText("Selezionare l'opera da eliminare!!");
			alert.showAndWait();
    	}else {
	    	boolean elimina =controller_eliminazione.elimina();
	    	if(elimina) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Elimina Utente");
				alert.setHeaderText("L'eliminazione è avvenuta con successo!!");
				alert.showAndWait();
				((Node) event.getSource()).getScene().getWindow().hide();
				Stage primaryStage6 = new Stage();
				BorderPane root6 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AmministratorePage.fxml"));
				Scene scene6 = new Scene(root6);
				primaryStage6.setScene(scene6);
				primaryStage6.show();
	    	}else {
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Elimina Utente");
				alert.setHeaderText("C'è stato un problema. Riprovare");
				alert.showAndWait();
	    	}
    	}
    }
}
