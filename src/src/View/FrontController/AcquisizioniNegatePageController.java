package View.FrontController;

import java.net.URL;
import java.util.ResourceBundle;
import Business.Controller.controller_consenso_supervisione;
import Business.Model.Immagine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AcquisizioniNegatePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listAcquisizioniNegate;

    @FXML
    private Hyperlink linkIndietro;

    @FXML
    private VBox acqneg;

    @FXML
    void initialize() {
        assert listAcquisizioniNegate != null : "fx:id=\"listAcquisizioniNegate\" was not injected: check your FXML file 'AcquisizioniNegatePage.fxml'.";
        assert linkIndietro != null : "fx:id=\"linkIndietro\" was not injected: check your FXML file 'AcquisizioniNegatePage.fxml'.";
        assert acqneg != null : "fx:id=\"acqneg\" was not injected: check your FXML file 'AcquisizioniNegatePage.fxml'.";
        setta();
    }
    
    public void setta() {
    	controller_consenso_supervisione.eliminate();
		ObservableList<String> lista=FXCollections.observableArrayList();
		String s1;
		if(!controller_consenso_supervisione.imgeliminate.isEmpty()) {
			for(Immagine i:controller_consenso_supervisione.imgeliminate) {
				s1="Opera: " + i.getTitoloOpera() + ", Pagina n°: " + i.getNumeropagina();
				lista.add(s1);
			}
			listAcquisizioniNegate.setItems(lista);
		}
    }
    
    public void click(MouseEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Attenzione!!");
		alert.setHeaderText("E' una tabella solo a titolo informativo!! Vai nella sezione \" Carica \" ");
		alert.showAndWait();
    }
    
    public void Indietro(ActionEvent event) throws Exception {
    	Stage homepage1 = (Stage) acqneg.getScene().getWindow();
        homepage1.close();
        Stage torna= AcquisitorePageController.homepage;
        torna.setIconified(false);
    }
}
