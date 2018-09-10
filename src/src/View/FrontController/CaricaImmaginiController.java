package View.FrontController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;

public class CaricaImmaginiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView listCarica;

    @FXML
    private Button btnSeleziona1;
    
    @FXML
    private Hyperlink linkIndietro;
    
    @FXML
    private TextField txtTitoloOpera;

    @FXML
    private TextField txtNumeroPagina;
    
    @FXML
    private Button btnCaricaImmagine;
    
    @FXML
    private ImageView imgAnteprima;
    
    @FXML
    private TextField txtURL;
    
    @FXML
    private Button btnPaginatore;
    
    @FXML
    void CaricaImmagine(ActionEvent event) {

    }
    
    @FXML
    private Button btnMiniatura;


    @FXML
    void initialize() {
        assert listCarica != null : "fx:id=\"txtCarica\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert btnSeleziona1 != null : "fx:id=\"btnSeleziona1\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert txtTitoloOpera != null : "fx:id=\"txtTitoloOpera\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert txtNumeroPagina != null : "fx:id=\"txtNumeroPagina\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert btnCaricaImmagine != null : "fx:id=\"btnCaricaImmagine\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert imgAnteprima != null : "fx:id=\"imgAnteprima\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert txtURL != null : "fx:id=\"txtURL\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert btnPaginatore != null : "fx:id=\"btnPaginatore\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";
        assert btnMiniatura != null : "fx:id=\"btnMiniatura\" was not injected: check your FXML file 'CaricaImmagini.fxml'.";

    }
    
    /*
    public void Seleziona1(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().addAll(new ExtensionFilter("FILE JPG", "*.jpg"));
    	File selectedFile = fc.showOpenDialog(null);
    	
    	if(selectedFile != null) {
    		listCarica.getItems().add(selectedFile.getAbsolutePath());
    	}
    	else {
    		System.out.println("file is not valid");
    	}
    }
    */
    
    private FileChooser fc;
    private File fp;
    
    public void Seleziona1(ActionEvent event) {
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
    
    public void paginatore(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage2 = new Stage();
		AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/CaricaImmaginePaginatorePage.fxml"));
		Scene scene2 = new Scene(root2);
		primaryStage2.setScene(scene2);
		primaryStage2.show();
    }
    
    public void VisualizzaMiniatura(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/CaricaImmaginiMiniaturaPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}