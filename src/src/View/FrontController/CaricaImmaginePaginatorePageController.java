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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CaricaImmaginePaginatorePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pagination PaginatoreOpere;
    
    File filesJpg[];

    @FXML
    private Button btnSelezionaMomentaneo;

    @FXML
    private Hyperlink linkIndietro;

    

    @FXML
    void initialize() {
        assert PaginatoreOpere != null : "fx:id=\"PaginatoreOpere\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        assert btnSelezionaMomentaneo != null : "fx:id=\"btnSelezionaMomentaneo\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";

    }
    

    private FileChooser fc;
    //private File fp;
    
    public void SelezionaMomentaneo(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	fc = new FileChooser();
    	fc.getExtensionFilters().addAll(new ExtensionFilter("FILE JPG", "*.jpg"));
    	
    	List <File>fp = fc.showOpenMultipleDialog(stage);
    	
    	try {
    		BufferedImage bufferedImage = ImageIO.read((File) fp);
    		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
    		PaginatoreOpere.setPageFactory((Callback<Integer, Node>) image);
    	} catch (IOException e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    
    public void indietro(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/CaricaImmagini.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}
