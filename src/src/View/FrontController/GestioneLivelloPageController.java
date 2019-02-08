package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_gestione_livello;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
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
    private ListView<String> listutenti;
    
    @FXML
    private Label txtavviso;

    @FXML
    void initialize() {
        assert txtNomeUtente != null : "fx:id=\"txtNomeUtente\" was not injected: check your FXML file 'GestioneLivelloPage.fxml'.";
        assert spinnerLivello != null : "fx:id=\"spinnerLivello\" was not injected: check your FXML file 'GestioneLivelloPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'GestioneLivelloPage.fxml'.";
        assert listutenti != null : "fx:id=\"listutenti\" was not injected: check your FXML file 'GestioneLivelloPage.fxml'.";
        assert btnModifica != null : "fx:id=\"btnModifica\" was not injected: check your FXML file 'GestioneLivelloPage.fxml'.";
        assert txtavviso != null : "fx:id=\"txtavviso\" was not injected: check your FXML file 'GestioneLivelloPage.fxml'.";
        SpinnerValueFactory<Integer> spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        this.spinnerLivello.setValueFactory(spinner);
        carica();
    }
    
    public void carica() {
    	ArrayList<String> utenti=controller_gestione_livello.prendi();
    	ObservableList<String> u=FXCollections.observableArrayList();
    	for(String s:utenti) {
    		u.add(s);
    	}
    	listutenti.setItems(u);
    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage6 = new Stage();
		BorderPane root6 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
		Scene scene6 = new Scene(root6);
		primaryStage6.setScene(scene6);
		primaryStage6.show();
    }
 	
    public static String livello=null;
    public static String cognome;
    public static String nome;
    public static int id;
    public void click(MouseEvent event) {
    	String utente=listutenti.getSelectionModel().getSelectedItem();
    	String innome="Nome: ";
		int sn=utente.indexOf(innome);
		String finnome=", Cognome: ";
		int fn=utente.indexOf(finnome);
		nome=utente.substring(sn+innome.length(),fn);
		String incogn="Cognome: ";
		int sc=utente.indexOf(incogn);
		String fincogn=", Livello: ";
		int fc=utente.indexOf(fincogn);
		cognome=utente.substring(sc+incogn.length(),fc);
		String nomecogn=nome +" " + cognome;
		txtNomeUtente.setText(nomecogn);
		String inliv="Livello: ";
		int sl=utente.indexOf(inliv);
		String finid=". Nome: ";
		int fid=utente.indexOf(finid);
		id=Integer.parseInt(utente.substring(0,fid));
		livello=utente.substring(sl+inliv.length());
		if(livello.equals("0"))
			txtavviso.setText("Questo utente al momento non ha un livello (0). \n Se non selezioni nessun livello si inserisce in automatico livello 1");
		else {
			txtavviso.setText("Il livello attuale di questo utente é: " + livello);
		}
    }
    
    public void Modifica(ActionEvent event) throws IOException {
    	String utente=txtNomeUtente.getText();
    	int livellomod=spinnerLivello.getValue();
    	boolean modifica=controller_gestione_livello.modifica(utente,livellomod);
    	if(modifica) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Gestione livello");
			alert.setHeaderText("La modifica è avvenuta con successo");
			alert.showAndWait();
			((Node)event.getSource()).getScene().getWindow().hide();
	    	Stage primaryStage = new Stage();
	    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    	}
    }   
}
