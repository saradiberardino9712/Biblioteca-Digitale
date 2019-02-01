package View.FrontController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Business.Controller.controller_revisione_acquisizione;
import Business.Model.Immagine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RevisioneAcquisizionePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listImg;

    @FXML
    private Button btnCorretta;

    @FXML
    private Button btnSbagliata;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private ImageView img;

    @FXML
    private VBox revimg;

    @FXML
    void initialize() {
        assert listImg != null : "fx:id=\"listImg\" was not injected: check your FXML file 'RevisioneAcquisizionePage.fxml'.";
        assert btnCorretta != null : "fx:id=\"btnCorretta\" was not injected: check your FXML file 'RevisioneAcquisizionePage.fxml'.";
        assert btnSbagliata != null : "fx:id=\"btnSbagliata\" was not injected: check your FXML file 'RevisioneAcquisizionePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'RevisioneAcquisizionePage.fxml'.";
        assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'RevisioneAcquisizionePage.fxml'.";
        assert revimg != null : "fx:id=\"consup\" was not injected: check your FXML file 'RevisioneAcquisizionePage.fxml'.";
        controllo();
        carica();
    }
    
    public static String basta="niente";
    public void controllo() {
    	if(basta.equals("corrette")) {
    		btnCorretta.setText("Avanti");
    		btnCorretta.setOnAction(new EventHandler<ActionEvent>() {
    		    @Override public void handle(ActionEvent e) {
    		    	((Node) e.getSource()).getScene().getWindow().hide();
    				Stage primaryStage2 = new Stage();
    				BorderPane root2 = null;
					try {
						root2 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RiepilogoRevisioneAcquisizionePage.fxml"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				Scene scene2 = new Scene(root2);
    				primaryStage2.setScene(scene2);
    				primaryStage2.show();
    		    }
    		});
    	}else if (basta.equals("sbagliate")) {
    		btnSbagliata.setText("Avanti");
    		btnSbagliata.setOnAction(new EventHandler<ActionEvent>() {
    		    @Override public void handle(ActionEvent e) {
    		    	((Node) e.getSource()).getScene().getWindow().hide();
    				Stage primaryStage2 = new Stage();
    				BorderPane root2 = null;
					try {
						root2 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RiepilogoRevisioneAcquisizionePage.fxml"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				Scene scene2 = new Scene(root2);
    				primaryStage2.setScene(scene2);
    				primaryStage2.show();
    		    }
    		});
    	}
    }
    
    public void carica() {
    	String s;
    	ObservableList<String> lista= FXCollections.observableArrayList();
    	for(Immagine i:controller_revisione_acquisizione.imgesaminare) {
    		s="Opera: " + i.getTitoloOpera() + ", Pagina n°: " + i.getNumeropagina();
    		lista.add(s);
    	}
    	listImg.setItems(lista);
    }
    
    public void indietro(ActionEvent event) throws Exception {
    	Stage homepage1 = (Stage) revimg.getScene().getWindow();
        homepage1.close();
        Stage torna= SupervisorePageController.homepage;
        torna.setIconified(false);
    }
    
    public void click(MouseEvent event) throws FileNotFoundException {
    	String selezione=listImg.getSelectionModel().getSelectedItem();
    	String url=controller_revisione_acquisizione.prendiurl(selezione);
    	if(url!=null) {
    		FileInputStream inputstream = new FileInputStream(url); 
    		Image image = new Image(inputstream);
    		img.setImage(image);
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("C'è un errore riprovare!!");
			alert.showAndWait();
    	}
    }
    
    public void corretta(ActionEvent event) throws IOException {
    	boolean corretta=controller_revisione_acquisizione.esaminare("corretta");
    	if(corretta) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Revisione acquisizione");
			alert.setHeaderText("L'immagine è corretta!! Pronta per la pubblicazione");
			alert.showAndWait();
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage2 = new Stage();
			BorderPane root2 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RiepilogoRevisioneAcquisizionePage.fxml"));
			Scene scene2 = new Scene(root2);
			primaryStage2.setScene(scene2);
			primaryStage2.show();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("C'è un errore riprovare!!");
			alert.showAndWait();
    	}
    }

    public void sbagliata(ActionEvent event) throws IOException {
    	boolean sbagliata=controller_revisione_acquisizione.esaminare("sbagliata");
    	if(sbagliata) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Revisione acquisizione");
			alert.setHeaderText("L'immagine è sbagliata!!");
			alert.showAndWait();
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage2 = new Stage();
			BorderPane root2 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RiepilogoRevisioneAcquisizionePage.fxml"));
			Scene scene2 = new Scene(root2);
			primaryStage2.setScene(scene2);
			primaryStage2.show();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("C'è un errore riprovare!!");
			alert.showAndWait();
    	}
    }
}
