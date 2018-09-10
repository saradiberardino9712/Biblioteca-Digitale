package View.FrontController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CaricaImmaginePaginatorePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pagination PaginatoreOpere;

    @FXML
    private Button btnSelezionaMomentaneo;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    void indietro(ActionEvent event) {

    }
    
    File filesJpg[];
    
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private Pagination pagination;

    @FXML
    void initialize() {
        assert PaginatoreOpere != null : "fx:id=\"PaginatoreOpere\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        assert btnSelezionaMomentaneo != null : "fx:id=\"btnSelezionaMomentaneo\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";

    }
    
    public void SelezionaMomentaneo(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	openDirectoryChooser(stage);
    	pagination.setPageFactory(new Callback<Integer, Node>(){
    		@Override
    		public Node call (Integer pageIndex) {
    			return createPage(pageIndex);
    		}
    	});

    }
    
    public VBox createPage(int index) {
    	ImageView imageView = new ImageView();
    	
    	File file = filesJpg[index];
    	try {
    		BufferedImage bufferedImage = ImageIO.read(file);
    		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
    		imageView.setImage(image);
    		imageView.setFitWidth(400);
    		imageView.setFitHeight(360);
    		
    		imageView.setSmooth(true);
    		imageView.setCache(true);
    	} catch (IOException ex) {
    		
    	}
    	
    	VBox pageBox = new VBox();
    	pageBox.getChildren().add(imageView);
    	
    	return pageBox;
    }
    
    private void openDirectoryChooser(Stage parent) {
    	DirectoryChooser directoryChooser = new DirectoryChooser();
    	File selectedDirectory = directoryChooser.showDialog(parent);
    	
    	if(selectedDirectory != null) {
    		FilenameFilter filterJpg = new FilenameFilter() {
    			
    			public boolean accpet(File dir, String name) {
    				return name.toLowerCase().endsWith(".jpg");
    			}
    		};
    		
    	}
    	
    	filesJpg = selectedDirectory.listFiles();
    }
}
