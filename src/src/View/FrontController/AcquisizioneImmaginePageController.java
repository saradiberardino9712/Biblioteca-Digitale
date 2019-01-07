package View.FrontController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import Business.Controller.controller_caricamento;
import Business.Model.Immagine;
import Business.Model.Opera;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AcquisizioneImmaginePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSeleziona;
    
    @FXML
    private Hyperlink linkIndietro;
    
    @FXML
    private ListView<String> listViewOpere;

    @FXML
    private ComboBox<String> comboboxNpag;
    
    @FXML
    private Button btnCaricaImmagine;
    
    @FXML
    private ImageView imgAnteprima;
    
    @FXML
    private TextField txtURL;

    @FXML
    void initialize() {
        assert btnSeleziona != null : "fx:id=\"btnSeleziona1\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert comboboxNpag != null : "fx:id=\"comboboxNpag\" was not injected: check your FXML file 'AcquisizioneImmaginePage.fxml'.";
        assert btnCaricaImmagine != null : "fx:id=\"btnCaricaImmagine\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert imgAnteprima != null : "fx:id=\"imgAnteprima\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert txtURL != null : "fx:id=\"txtURL\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert listViewOpere != null : "fx:id=\"listViewOpere\" was not injected: check your FXML file 'AcquisizioneImmaginePage.fxml'.";
        setta();
    }
    
    public void setta() {
    	ArrayList<Opera> listaopere=controller_caricamento.listaopere;
    	ObservableList<String> lista=FXCollections.observableArrayList();
    	String titolo;
    	for(Opera o: listaopere) {
    		titolo=o.getTitolo();
    		lista.add(titolo);
    	}
    	listViewOpere.setItems(lista);
    }
    
    public void click(MouseEvent event) {
    	String titolo=listViewOpere.getSelectionModel().getSelectedItem();
    	ArrayList<Immagine> listaimmagini=controller_caricamento.listaimmagini;
    	ArrayList<Integer> pagcaricate=new ArrayList<>();
    	int pagtot = 0;
    	boolean trovato=false;
    	if(listaimmagini.isEmpty()) {
    		for(Opera o: controller_caricamento.listaopere) {
    			if(titolo.equals(o.getTitolo())) {
    				pagtot=o.getPagine();	
    			}
    		}
    	}else {
    		for (Immagine img:listaimmagini) {
    			if(titolo.equals(img.getTitoloOpera())) {
    				trovato=true;
    				pagtot=img.getPagineOpera();
    				pagcaricate.add(img.getNumeropagina());
    			}
    		}
    	}
    	if(!trovato) {
    		for(Opera o: controller_caricamento.listaopere) {
    			if(titolo.equals(o.getTitolo())) {
    				pagtot=o.getPagine();	
    			}
    		}
    	}
    	comboboxNpag.getItems().clear();
    	for(int i=1;i<=pagtot;i++) {
    		if(!pagcaricate.contains(i))
    			comboboxNpag.getItems().add(Integer.toString(i));
    	}
    }
    
    private FileChooser fc;
    private File fp;
    
    public void Seleziona(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	fc = new FileChooser();
    	fc.getExtensionFilters().addAll(new ExtensionFilter("FILE JPG", "*.jpg"));
    	
    	this.fp = fc.showOpenDialog(stage);
    	
    	try {
    		BufferedImage bufferedImage = ImageIO.read(fp);
    		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
    		imgAnteprima.setImage(image);
    		if(fp != null) {
        		txtURL.setText(fp.getAbsolutePath());
        	}
        	else {
        		System.out.println("file is not valid");
        	}
    	} catch (IOException e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    public void indietro(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage2 = new Stage();
		AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisitorePage.fxml"));
		Scene scene2 = new Scene(root2);
		primaryStage2.setScene(scene2);
		primaryStage2.show();
    }
    
    public void CaricaImmagine(ActionEvent event) throws Exception {
    	boolean caricamento=controller_caricamento.controlloopera(listViewOpere,comboboxNpag,txtURL);
    	if(caricamento) {
    		boolean carica=controller_caricamento.carica();
    		if(carica) {
    			((Node) event.getSource()).getScene().getWindow().hide();
    			Stage primaryStage2 = new Stage();
    			BorderPane root2 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/CaricaImmaginiMiniaturaPage.fxml"));
    			Scene scene2 = new Scene(root2);
    			primaryStage2.setScene(scene2);
    			primaryStage2.show();
    		}else {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Caricamento immagine");
    			alert.setHeaderText("C'è stato un errore riprovare!!");
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
}