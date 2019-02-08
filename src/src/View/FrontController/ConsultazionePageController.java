package View.FrontController;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


import Business.Controller.controller_consultazione;
import Business.Model.Immagine;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PageRange;
import javafx.scene.ImageCursorBuilder;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConsultazionePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pagination paginatoreImmagine;

    @FXML
    private Pagination paginatoreTrascrizione;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private Button btnDownload;

    @FXML
    void initialize() throws FileNotFoundException {
        assert paginatoreImmagine != null : "fx:id=\"paginatoreImmagine\" was not injected: check your FXML file 'ConsultazionePage.fxml'.";
        assert paginatoreTrascrizione != null : "fx:id=\"paginatoreTrascrizione\" was not injected: check your FXML file 'ConsultazionePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'ConsultazionePage.fxml'.";
        assert btnDownload != null : "fx:id=\"btnDownload\" was not injected: check your FXML file 'ConsultazionePage.fxml'.";
        button();
        visualizza();
    }
    
    public void button() {
    	controller_consultazione.impostaruolo();
    	if(controller_consultazione.ruoloutente.equals("Utente Privilegiato")) {
    		btnDownload.setDisable(false);
    	}else {
    		
    		btnDownload.setVisible(false);
    	}
    }
    
    public void visualizza() throws FileNotFoundException {
    	ArrayList<Immagine> img=controller_consultazione.prendiimmagini();
    	int count=0;
    	ArrayList<ImageView> images= new ArrayList<>();
    	for(Immagine i:img) {
    		count+=1;
    		ImageView imgView=new ImageView();
    		imgView.setFitHeight(270);
    		imgView.setFitWidth(225);
    		FileInputStream inputstream = new FileInputStream(i.getUrl()); 
    		Image image = new Image(inputstream); 
    		imgView.setImage(image);
    		images.add(imgView);
    	}
    	paginatoreImmagine.pageCountProperty().set(count);
    	paginatoreImmagine.maxPageIndicatorCountProperty().set(count);
    	paginatoreImmagine.setPageFactory(n->images.get(n));
    	/*ArrayList<Immagine> trascrizioni=controller_consultazione.prendiimmagini();
    	paginatoreTrascrizione.pageCountProperty().set(count);
    	paginatoreTrascrizione.maxPageIndicatorCountProperty().set(count);
    	paginatoreTrascrizione.setPageFactory(n->images.get(n));*/
    }
    
    public void Indietro(ActionEvent event) throws Exception {
		controller_consultazione.impostaruolo();
    	switch(controller_consultazione.ruoloutente) {
			case "Utente Base":((Node) event.getSource()).getScene().getWindow().hide();
							Stage primaryStage = new Stage();
							AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/UtenteBasePage.fxml"));
							Scene scene = new Scene(root);
							primaryStage.setScene(scene);
							primaryStage.show();
							break;
			case "Utente Privilegiato":((Node) event.getSource()).getScene().getWindow().hide();
									Stage primaryStage1 = new Stage();
									AnchorPane root1 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/UtentePrivilegiatoPage.fxml"));
									Scene scene1 = new Scene(root1);
									primaryStage1.setScene(scene1);
									primaryStage1.show();
									break;
			case "Acquisitore":((Node) event.getSource()).getScene().getWindow().hide();
							Stage primaryStage2 = new Stage();
							AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AcquisitorePage.fxml"));
							Scene scene2 = new Scene(root2);
							primaryStage2.setScene(scene2);
							primaryStage2.show();
							break;
			case "Supervisore":((Node) event.getSource()).getScene().getWindow().hide();
							Stage primaryStage3 = new Stage();
							AnchorPane root3 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/SupervisorePage.fxml"));
							Scene scene3 = new Scene(root3);
							primaryStage3.setScene(scene3);
							primaryStage3.show();
							break;
			case "Trascrittore":((Node) event.getSource()).getScene().getWindow().hide();
							Stage primaryStage4 = new Stage();
							AnchorPane root4 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/TrascrittorePage.fxml"));
							Scene scene4 = new Scene(root4);
							primaryStage4.setScene(scene4);
							primaryStage4.show();
							break;
			case "Revisore":((Node) event.getSource()).getScene().getWindow().hide();
						Stage primaryStage5 = new Stage();
						AnchorPane root5 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/javaFX/RevisorePage.fxml"));
						Scene scene5 = new Scene(root5);
						primaryStage5.setScene(scene5);
						primaryStage5.show();
						break;
			case "Manager":((Node) event.getSource()).getScene().getWindow().hide();
						Stage primaryStage6 = new Stage();
						BorderPane root6 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
						Scene scene6 = new Scene(root6);
						primaryStage6.setScene(scene6);
						primaryStage6.show();
						break;
			case "Amministratore":((Node) event.getSource()).getScene().getWindow().hide();
						Stage primaryStage7 = new Stage();
						BorderPane root7 = (BorderPane) FXMLLoader.load(getClass().getResource("/View/javaFX/AmministratorePage.fxml"));
						Scene scene7 = new Scene(root7);
						primaryStage7.setScene(scene7);
						primaryStage7.show();
						break;	
		}
	}
    
    public void Download(ActionEvent event) throws IOException {
    	/*StringBuilder sb = new StringBuilder();
    	FileInputStream inputstream = new FileInputStream("C:\\Users\\Sara\\Desktop\\Drive\\img\\Salterio_diurno_del_XVII_secolo.jpg"); 
		Image image = new Image(inputstream);
    	sb.append(image);
    	File f = new File("C:\\Users\\Sara\\Desktop\\"+controller_consultazione.open+".zip");
    	ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
    	ZipEntry e = new ZipEntry("pag1.jpg");
    	out.putNextEntry(e);
    	byte[] data = sb.toString().getBytes();
    	out.write(data, 0, data.length);
    	out.closeEntry();
    	out.close();*/
    	FileInputStream inputstream = new FileInputStream("C:\\Users\\Sara\\Desktop\\Drive\\img\\Salterio_diurno_del_XVII_secolo.jpg"); 
		Image image = new Image(inputstream);
    	File outputFile = new File("C:\\Users\\Sara\\Desktop");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        
    }
}
