package View.FrontController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_trascrizione;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class TrascriviPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView immagine;

    @FXML
    private HTMLEditor TextEditor;

    @FXML
    private Button btnConfermaTrascrizione;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private Label lblWarning;

    @FXML
    private ComboBox<String> comboOpera;

    @FXML
    private MenuButton MenuPagine;

    @FXML
    void initialize() {
        assert immagine != null : "fx:id=\"immagine\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert TextEditor != null : "fx:id=\"TextEditor\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert btnConfermaTrascrizione != null : "fx:id=\"btnConfermaTrascrizione\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert lblWarning != null : "fx:id=\"lblWarning\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert comboOpera != null : "fx:id=\"comboOpera\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert MenuPagine != null : "fx:id=\"MenuPagine\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        comboOpera.getSelectionModel().select("Seleziona opera");
        settaopere();
    }
    
    public void settaopere() {
    	comboOpera.getItems().clear();
    	ArrayList<String> listaopere=controller_trascrizione.verifica();
    	ObservableList<String> list = FXCollections.observableArrayList();
    	for(String s: listaopere) {
    		if(!list.contains(s))
    			list.add(s);
    	}
    	comboOpera.setItems(list);
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Selezionare");
		alert.setHeaderText("Seleziona prima l'opera e poi la pagina!!");
		alert.showAndWait();
    }
    
    public void click(MouseEvent event) {
    	count=0;
    	MenuPagine.getItems().clear();
    }
    
    public int count=0;
    public static Integer pagina;
    public static String selected;
    public void clickpagina(MouseEvent event) {
    	if(comboOpera.getSelectionModel().getSelectedItem().equals("Seleziona opera")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Selezionare");
    		alert.setHeaderText("Seleziona prima l'opera e poi la pagina!!");
    		alert.showAndWait();
    	}else {
    		selected=comboOpera.getSelectionModel().getSelectedItem();
    		ArrayList<Integer>listapagine= controller_trascrizione.prendipagine(selected);
        	for(Integer i: listapagine) {
        		MenuItem item = new MenuItem();
        		if(count==0) {	
        			item.setText(Integer.toString(i));
        			MenuPagine.getItems().add(item);
        		}
        		item.setOnAction(new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent e) {
        				for(Immagine i: controller_trascrizione.img) {
        					pagina=Integer.parseInt(item.getText());
        					MenuPagine.setText(Integer.toString(pagina));
        					if(i.getNumeropagina()==pagina) {
        						FileInputStream inputstream = null;
								try {
									inputstream = new FileInputStream(i.getUrl());
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
        			    		Image image = new Image(inputstream);
        			    		immagine.setImage(image);
        					}
        				}
        			}
        		});
        	}
        	count=1;
    	}
    }

    public void Indietro(ActionEvent event) throws IOException {
    	Stage torna=TrascrittorePageController.homepage;
		torna.close();
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/javaFX/TrascrittorePage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public void ConfermaTrascrizione(ActionEvent event) throws IOException {
    	String testo=TextEditor.getHtmlText();
		boolean conferma=controller_trascrizione.completa(testo,pagina,selected);
		if(conferma) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Trascrizione");
			alert.setHeaderText("La trascrizione è avvenuta con successo!!");
			alert.showAndWait();
			Stage torna=TrascrittorePageController.homepage;
			torna.close();
			((Node)event.getSource()).getScene().getWindow().hide();
		 	Stage primaryStage = new Stage();
		    AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/javaFX/TrascrittorePage.fxml"));
		    Scene scene = new Scene(root);
		    primaryStage.setScene(scene);
		    primaryStage.show();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
		    alert.setTitle("Trascrizione");
		    alert.setHeaderText("C'è stato un problema!!");
		    alert.showAndWait();
    	}
    }
}
