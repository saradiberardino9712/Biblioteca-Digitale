package Business.Controller;

import java.util.ArrayList;
import Business.Model.Notifica;
import Business.Model.Ruolo;
import Business.Model.Utente;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class controller_domanda {
	
	public static String nome;
	public static String cognome;
	public static String ruolo;
	private static Utente utente;
	
	public static boolean datirichiesta() {
		utente=Utente.getIstance();
		if(utente==null)
			return false;
		nome=utente.getNome();
		cognome=utente.getCognome();
		ruolo=utente.getNomeRuolo();
		ArrayList<Notifica> elenconot=Notifica.prendinotifiche(0,prendiidmanager(),"Accetta/Rifiuta");
		for(Notifica n: elenconot) {
			int id=n.getidutente();
			if(utente.getID()==id) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Errore nell'inivio della domanda");
				alert.setHeaderText("La richiesta è già stata inviata!! Attendere la risposta.");
				alert.showAndWait();
				return false;
			}	
		}	
		return true;
	}
	
	public static int prendiidmanager() {
		Ruolo ruolo=Ruolo.prendiiddb("Manager");
		int idmanager=0;
		if(!(ruolo==null))
			idmanager=ruolo.getID();
		return idmanager;
	}
	
	public static boolean invia(TextField txttitolostudio) {
		String titolostudio=txttitolostudio.getText();
		if(!titolostudio.equals(utente.getTitoloStudio()))
			return false;
		boolean modificastato=Utente.settastato("in attesa");
		boolean notifica=false;
		if(modificastato) {
			int idruolonot=prendiidmanager();
			int idutente=utente.getID();
			notifica=Notifica.creanotifica("E' stata effettuata una richiesta per diventare trascrittore!! Clicca qui o su \"Accetta/Rifiuta\" ",0,idruolonot,idutente);
		}
		if(notifica)
			return true;
		else
			return false;
	}
}
