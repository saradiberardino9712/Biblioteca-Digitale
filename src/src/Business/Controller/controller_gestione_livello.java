package Business.Controller;

import java.util.ArrayList;

import Business.Model.Ruolo;
import Business.Model.Utente;
import View.FrontController.GestioneLivelloPageController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class controller_gestione_livello {
	
	public static int prendiid(String r) {
		Ruolo ruolo=Ruolo.prendiiddb(r);
		int idruolo=0;
		if(!(ruolo==null))
			idruolo=ruolo.getID();
		return idruolo;
	}

	public static ArrayList<String> prendi() {
		int idruolo=prendiid("Trascrittore");
		ArrayList<String> utenti= Utente.prendiutenti(idruolo);
		return utenti;
	}

	public static boolean modifica(String utente, int livellomod) {
		if(utente.length()==0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Gestione livello");
			alert.setHeaderText("Seleziona l'utente");
			alert.showAndWait();
			return false;
		}
		if(livellomod==Integer.parseInt(GestioneLivelloPageController.livello)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Gestione livello");
			alert.setHeaderText("Non è stata fatta nussuna modifica");
			alert.showAndWait();
			return false;
		}
		boolean modifica=Utente.updatelivello(GestioneLivelloPageController.nome,GestioneLivelloPageController.cognome,GestioneLivelloPageController.id,livellomod);
		return modifica;
	}

}
