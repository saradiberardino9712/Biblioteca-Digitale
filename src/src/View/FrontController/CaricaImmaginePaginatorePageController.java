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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
    private Pagination pagination;
    
    @FXML
    private Button btnIndietro;

    @FXML
    private Button btnCarica;
    
    @FXML
    void CaricaDefinitiva(ActionEvent event) {

    }

    @FXML
    private Button btnMiniatura;
    
    @FXML
    private Button btnSeleziona;

    @FXML
    void initialize() {
        assert pagination != null : "fx:id=\"pagination\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        assert btnSeleziona != null : "fx:id=\"btnSeleziona\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        assert btnIndietro != null : "fx:id=\"btnIndietro\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        assert btnCarica != null : "fx:id=\"btnCarica\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";
        assert btnMiniatura != null : "fx:id=\"btnMiniatura\" was not injected: check your FXML file 'CaricaImmaginePaginatorePage.fxml'.";

    }
    
File filesJpg[];
    
    @FXML
    private void SelezionaImg(ActionEvent event) {
        
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
         openDirectoryChooser(stage);        
         
         pagination.setPageFactory(new Callback<Integer, Node>() {
            public Node call(Integer pageIndex) {
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
            imageView.setFitWidth(312);
            imageView.setFitHeight(360);
           // imageView.setPreserveRatio(true);
            
            imageView.setSmooth(true);
            imageView.setCache(true);
        } catch (IOException ex) {
            
        }
         
        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);
        
        
        return pageBox;
    }  
    
    
    
    
    public void initialize(URL url, ResourceBundle rb) {       
        
        
    }    
    
    private void openDirectoryChooser(Stage parent) {
        
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(parent);
 
        if (selectedDirectory != null) {
            FilenameFilter filterJpg = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpg");
                }
            };
 
            filesJpg = selectedDirectory.listFiles(filterJpg);
            
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
    
    public void VisualizzaMiniatura(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/CaricaImmaginiMiniaturaPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}
