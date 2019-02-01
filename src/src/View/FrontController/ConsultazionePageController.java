package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;

import Business.Controller.controller_dati;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
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
    void Download(ActionEvent event) {

    }


    @FXML
    void initialize() {
        assert paginatoreImmagine != null : "fx:id=\"paginatoreImmagine\" was not injected: check your FXML file 'ConsultazionePage.fxml'.";
        assert paginatoreTrascrizione != null : "fx:id=\"paginatoreTrascrizione\" was not injected: check your FXML file 'ConsultazionePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'ConsultazionePage.fxml'.";
        assert btnDownload != null : "fx:id=\"btnDownload\" was not injected: check your FXML file 'ConsultazionePage.fxml'.";

    }
    
    public void Indietro(ActionEvent event) throws Exception {
		switch(controller_dati.ruolo) {
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
}
