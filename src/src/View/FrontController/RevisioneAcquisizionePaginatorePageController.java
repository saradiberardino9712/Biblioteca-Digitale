package View.FrontController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_revisione_acquisizione;
import Business.Model.Immagine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RevisioneAcquisizionePaginatorePageController {
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

    @FXML
    private Button btnMiniatura;
    
    @FXML
    private Pagination pagination;
    
    @FXML
    private Hyperlink linkIndietro;
    
    @FXML
    void initialize() throws FileNotFoundException {
    	assert pagination != null : "fx:id=\"pagination\" was not injected: check your FXML file 'RevisioneAcquisizionePaginatorePage.fxml'.";
        assert btnMiniatura != null : "fx:id=\"btnVisualizza\" was not injected: check your FXML file 'RevisioneAcquisizionePaginatorePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'RevisioneAcquisizionePaginatorePage.fxml'.";
        visualizza();
    }
    
    public void visualizza() throws FileNotFoundException {
    	ArrayList<Immagine> img=new ArrayList<>();
		if(RiepilogoRevisioneAcquisizionePageController.esito.contains("corrette")) {
			img=controller_revisione_acquisizione.imgcorrette;
		}else {
			img=controller_revisione_acquisizione.imgsbagliate;
		}
    	int count=0;
    	ArrayList<ImageView> images= new ArrayList<>();
    	for(Immagine i:img) {
    		count+=1;
    		ImageView imgView=new ImageView();
    		imgView.setFitHeight(361);
    		imgView.setFitWidth(313);
    		FileInputStream inputstream = new FileInputStream(i.getUrl()); 
    		Image image = new Image(inputstream); 
    		imgView.setImage(image);
    		images.add(imgView);
    	}
    	pagination.pageCountProperty().set(count);
    	pagination.maxPageIndicatorCountProperty().set(6);
    	pagination.setPageFactory(n->images.get(n));
    }

    public void Miniatura(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/RevisioneAcquisizioneMiniaturaPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    public void Indietro(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage2 = new Stage();
		BorderPane root2 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RiepilogoRevisioneAcquisizionePage.fxml"));
		Scene scene2 = new Scene(root2);
		primaryStage2.setScene(scene2);
		primaryStage2.show();
    }
}
