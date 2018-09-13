package View.FrontController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_caricamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    private ObservableList<String> lista=FXCollections.observableArrayList("Non ha categoria","Altro");
    
    @FXML
    void initialize() {
        assert txtTitolo != null : "fx:id=\"txtTitolo\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert txtAutore != null : "fx:id=\"txtAutore\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert txtNpagine != null : "fx:id=\"txtNpagine\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert txtIndietro != null : "fx:id=\"txtIndietro\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert btnCaricaMetadati != null : "fx:id=\"btnCaricaMetadati\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";
        assert combobox != null : "fx:id=\"combobox\" was not injected: check your FXML file 'InserisciMetadatiPage.fxml'.";   
        combobox.getItems().addAll(lista);
    	combobox.getSelectionModel().selectFirst();
    }
    
    public static int count=0;
    
    public void Aggiorna(MouseEvent event) {
    	if(count==0) {
    		ArrayList<Business.Model.Categoria> elenco=controller_caricamento.prendicategorie();
    		String cat;
    		for(Business.Model.Categoria ca:elenco) {
    			cat=ca.getNome();
    			combobox.getItems().add(1,cat);
    		}
    		count+=1;
    	}
    	if(count==2) {
    		combobox.getItems().add(1,controller_caricamento.categoria);
    		count+=1;
    	}
    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage2 = new Stage();
		AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisitorePage.fxml"));
		Scene scene2 = new Scene(root2);
		primaryStage2.setScene(scene2);
		primaryStage2.show();
    }
    
    public void CaricaMetadati(ActionEvent event) throws Exception {
    	String metadati=controller_caricamento.inseriscimetadati(txtTitolo,txtAnno,txtAutore,txtNpagine,combobox);
    	if(metadati.equals("categoria")) {
    		Stage primaryStage2 = new Stage();
    		BorderPane root2 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/InserisciCategoriaPage.fxml"));
    		Scene scene2 = new Scene(root2);
    		primaryStage2.setScene(scene2);
    		primaryStage2.show();
    	}
    	else if (metadati.equals("true")) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Caricamento metadati");
    			alert.setHeaderText("Il carcameto � avvenuto correttamente!! Adesso puoi fare la scansione delle pagine dell'opera appena inserita");
    			alert.showAndWait();
    			((Node) event.getSource()).getScene().getWindow().hide();
    			Stage primaryStage2 = new Stage();
    			AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisitorePage.fxml"));
    			Scene scene2 = new Scene(root2);
    			primaryStage2.setScene(scene2);
    			primaryStage2.show();
    	}
    }
}
