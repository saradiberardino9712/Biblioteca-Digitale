package View.FrontController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Business.Controller.controller_assegnazione_trascrizioni;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class AssegnaTrascrizioniController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Integer> listpagine;

    @FXML
    private ListView<String> listtrascrittori;

    @FXML
    private Button btnAssegnatrascrizione;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private MenuButton menuopere;

    @FXML
    void initialize() {
        assert listpagine != null : "fx:id=\"listpagine\" was not injected: check your FXML file 'AssegnaOpere.fxml'.";
        assert listtrascrittori != null : "fx:id=\"listtrascrittori\" was not injected: check your FXML file 'AssegnaOpere.fxml'.";
        assert btnAssegnatrascrizione != null : "fx:id=\"btnAssegnatrascrizione\" was not injected: check your FXML file 'AssegnaOpere.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'AssegnaOpere.fxml'.";
        assert menuopere != null : "fx:id=\"menuopere\" was not injected: check your FXML file 'AssegnaTrascrizioni.fxml'.";
        setta();
    }
    
    public void setta() {
    	ObservableList<String> list=controller_assegnazione_trascrizioni.prendiopere();
    	for(String s:list) {
    		MenuItem item = new MenuItem();
    		item.setText(s);
    		menuopere.getItems().add(item);
    		item.setOnAction(new EventHandler<ActionEvent>() {
    			public void handle(ActionEvent e) {
    				menuopere.setText(item.getText());
    				visualizza(item.getText());
    			}
    		});
    	}
    	ObservableList<String> listatra=controller_assegnazione_trascrizioni.prenditrascrittori();
    	listtrascrittori.setItems(listatra);
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Attenzione!!");
		alert.setHeaderText("Seleziona prima l'opera");
		alert.showAndWait();
    }
    
    public void visualizza(String opera) {
    	ObservableList<Integer> listapagine=controller_assegnazione_trascrizioni.prendipagine(opera);
    	listpagine.setItems(listapagine);
    }
    
    public void Indietro(ActionEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public static int entra=0;
    public static ArrayList<String> elenco= new ArrayList<>();
    public static int pagina;
    public void AssegnaTrascrizione(ActionEvent event) throws IOException {
    	String trascrittore = null;
    	if(esito.equals("Si") && entra>0) {
    		if(listtrascrittori.getSelectionModel().getSelectedItem() != null) {
    			trascrittore=listtrascrittori.getSelectionModel().getSelectedItem();
    		}else {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Attenzione!!");
    			alert.setHeaderText("Seleziona il trascrittore");
    			alert.showAndWait();
    		}
    		elenco.add(trascrittore);
    		choosestage();
    	}else if(entra==0 && esito.equals("No")) {
			if(listpagine.getSelectionModel().getSelectedItem() != null) {
				pagina=listpagine.getSelectionModel().getSelectedItem();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Attenzione!!");
				alert.setHeaderText("Seleziona la pagina da assegnare");
				alert.showAndWait();
			}
			if(listtrascrittori.getSelectionModel().getSelectedItem() != null) {
				trascrittore=listtrascrittori.getSelectionModel().getSelectedItem();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Attenzione!!");
				alert.setHeaderText("Seleziona il trascrittore");
				alert.showAndWait();
			}
	    	elenco.add(trascrittore);
	    	choosestage();
    	}else {
    		boolean assegna=controller_assegnazione_trascrizioni.assegna(pagina,elenco);
    		if(assegna) {
    			Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Assegna trascrizione");
				alert.setHeaderText("L'assegnamento è andato a buon fine");
				alert.showAndWait();
				entra=0;
				esito="No";
				elenco.clear();
				pagina=0;
				Stage home=(Stage) listtrascrittori.getScene().getWindow();
				home.close();
				((Node)event.getSource()).getScene().getWindow().hide();
		    	Stage primaryStage = new Stage();
		    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/View/javaFX/ManagerPage.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
    		}else {
    			Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Attenzione!!");
				alert.setHeaderText("C'è stato un errore");
				alert.showAndWait();
    		}
    	}
    }
    
    public static String esito="No";
    public void choosestage() {
    	BorderPane choosestage=new BorderPane();
    	VBox contentBox = new VBox();
		Label testo = new Label("Vuoi assegnare la stessa pagina ad un altro trascrittore??");
		testo.setWrapText(true);
		testo.setId("label");
		testo.setStyle("-fx-font-family: Cambria; -fx-font-size: 20pt");
		contentBox.getChildren().add(testo);
		contentBox.setAlignment(Pos.CENTER);
		HBox buttonsBox = new HBox(20);
		for (int i = 0; i < 2; i++) {
			if(i==0) {
				Button button = new Button("Si");
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent ae) {
						for(String s:elenco) {
							listtrascrittori.getItems().remove(s);
						}
						esito="Si";
						entra+=1;
						Stage choose=(Stage) button.getScene().getWindow();
						choose.close();
					}
				});
				buttonsBox.getChildren().add(button);
			}else {
				Button button = new Button("No");
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent ae) {
						esito="No";
						entra+=1;
						Stage choose=(Stage) button.getScene().getWindow();
						choose.close();
						try {
							AssegnaTrascrizione(ae);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				buttonsBox.getChildren().add(button);
			}		
		}
		buttonsBox.setAlignment(Pos.CENTER);
		choosestage.setCenter(contentBox);
		choosestage.setBottom(buttonsBox);
		BorderPane.setMargin(contentBox, new Insets(20, 10, 10, 10));
		BorderPane.setMargin(buttonsBox, new Insets(10, 10, 20, 10));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(choosestage,500,200);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}
