package View.FrontController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.Map.Entry;
import Business.Controller.controller_revisione_acquisizione;
import Business.Model.Immagine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RevisioneAcquisizioneMiniaturaPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnVisualizzaPaginatore;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img6;

    @FXML
    private Label txttesto11;

    @FXML
    private Label txttesto12;

    @FXML
    private Label txttesto41;

    @FXML
    private Label txttesto42;

    @FXML
    private Label txttesto32;

    @FXML
    private Label txttesto31;

    @FXML
    private Label txttesto22;

    @FXML
    private Label txttesto21;

    @FXML
    private Label txttesto62;

    @FXML
    private Label txttesto61;

    @FXML
    private Label txttesto52;

    @FXML
    private Label txttesto51;

    @FXML
    void initialize() throws FileNotFoundException {
        assert btnVisualizzaPaginatore != null : "fx:id=\"btnVisualizzaPaginatore\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert img1 != null : "fx:id=\"img1\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert img3 != null : "fx:id=\"img3\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert img2 != null : "fx:id=\"img2\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert img4 != null : "fx:id=\"img4\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert img5 != null : "fx:id=\"img5\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert img6 != null : "fx:id=\"img6\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto11 != null : "fx:id=\"txttesto11\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto12 != null : "fx:id=\"txttesto12\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto41 != null : "fx:id=\"txttesto41\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto42 != null : "fx:id=\"txttesto42\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto32 != null : "fx:id=\"txttesto32\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto31 != null : "fx:id=\"txttesto31\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto22 != null : "fx:id=\"txttesto22\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto21 != null : "fx:id=\"txttesto21\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto62 != null : "fx:id=\"txttesto62\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto61 != null : "fx:id=\"txttesto61\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto52 != null : "fx:id=\"txttesto52\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        assert txttesto51 != null : "fx:id=\"txttesto51\" was not injected: check your FXML file 'RevisioneAcquisizioneminiaturaPage.fxml'.";
        visualizza();
    }
    
	public void visualizza() throws FileNotFoundException {
		ArrayList<Immagine> images;
		if(RiepilogoRevisioneAcquisizionePageController.esito.equals("corrette")) {
			images=controller_revisione_acquisizione.imgcorrette;
		}else
			images=controller_revisione_acquisizione.imgsbagliate;
		int index=0;
		TreeMap<Integer,Immagine> vis=new TreeMap<>();
		for(Immagine e: images) {
			vis.put(index, e);
			index++;
		}
    	String url;
    	int n;
    	String titolo;
    	Immagine img;
    	int i;
    	for(Entry<Integer, Immagine> elem:vis.entrySet()) {
    		i=elem.getKey()+1;
    		img=elem.getValue();
    		url=img.getUrl();
    		n=img.getNumeropagina();
    		titolo=img.getTitoloOpera();
    		FileInputStream inputstream = new FileInputStream(url); 
    		Image image = new Image(inputstream); 
    		switch(i) {
    		case 1: img1.setImage(image);
    				txttesto11.setText("Pagina n° " + n);
    				txttesto12.setText(" Opera: " + titolo);
    				break;
    		case 2: img2.setImage(image);
					txttesto21.setText("Pagina n° " + n);
					txttesto22.setText(" Opera: " + titolo);
					break;
    		case 3: img3.setImage(image);
					txttesto31.setText("Pagina n° " + n);
					txttesto32.setText(" Opera: " + titolo);
					break;
    		case 4: img4.setImage(image);
					txttesto41.setText("Pagina n° " + n);
					txttesto42.setText(" Opera: " + titolo);
					break;
    		case 5: img5.setImage(image);
					txttesto51.setText("Pagina n° " + n);
					txttesto52.setText(" Opera: " + titolo);
					break;
    		case 6: img6.setImage(image);
					txttesto61.setText("Pagina n° " + n);
					txttesto62.setText(" Opera: " + titolo);
					break;
    		}	
    	}
    }
	
    public void Indietro(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage2 = new Stage();
		BorderPane root2 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RiepilogoRevisioneAcquisizionePage.fxml"));
		Scene scene2 = new Scene(root2);
		primaryStage2.setScene(scene2);
		primaryStage2.show();
    }
    
    public void Paginatore(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage3 = new Stage();
    	BorderPane root3 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizionePaginatorePage.fxml"));
		Scene scene3 = new Scene(root3);
		primaryStage3.setScene(scene3);
		primaryStage3.show();
    }
}
