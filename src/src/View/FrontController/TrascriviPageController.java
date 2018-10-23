package View.FrontController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class TrascriviPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView immagine;

    @FXML
    private Button btnImmagine;
    
    @FXML
    private HTMLEditor TextEditor;
    
    @FXML
    private Label lblWarning;
    
    @FXML
    private Button btnConfermaTrascrizione;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    void ConfermaTrascrizione(ActionEvent event) {

    }
    


    @FXML
    void initialize() {
        assert immagine != null : "fx:id=\"immagine\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert btnImmagine != null : "fx:id=\"btnImmagine\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert TextEditor != null : "fx:id=\"TextEditor\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert btnConfermaTrascrizione != null : "fx:id=\"btnConfermaTrascrizione\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'TrascriviPage.fxml'.";
        assert lblWarning != null : "fx:id=\"lblWarning\" was not injected: check your FXML file 'TrascriviPage.fxml'.";

    }
    
    private FileChooser fc;
    private File fp;
    
    public void Immagine(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	fc = new FileChooser();
    	fc.getExtensionFilters().addAll(new ExtensionFilter("FILE JPG", "*.jpg"));
    	
    	this.fp = fc.showOpenDialog(stage);
    	
    	try {
    		BufferedImage bufferedImage = ImageIO.read(fp);
    		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
    		immagine.setImage(image);
    	} catch (IOException e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage4 = new Stage();
		AnchorPane root4 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/TrascrittorePage.fxml"));
		Scene scene4 = new Scene(root4);
		primaryStage4.setScene(scene4);
		primaryStage4.show();
    }
    
}
