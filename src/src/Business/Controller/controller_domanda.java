package Business.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import Business.Model.Notifica;
import Business.Model.Ruolo;
import Business.Model.Utente;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class controller_domanda {
	
	public static String nome;
	public static String cognome;
	public static String ruolo;
	private static Utente utente;
	public static boolean notificacolore;
	public static ArrayList<Notifica> elenco;
	
	public static boolean datirichiesta() {
		utente=Utente.getIstance();
		if(utente==null)
			return false;
		nome=utente.getNome();
		cognome=utente.getCognome();
		ruolo=utente.getNomeRuolo();
		ArrayList<Notifica> elenconot=Notifica.prendinotifiche(prendiidmanager(),"Accetta Domande");
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
		boolean modificastato=Utente.settastato(utente,"in attesa");
		Ruolo ruolo=Ruolo.prendiiddb("Manager");
		int idmanager=0;
		if(ruolo==null)
			return false;
		else
			idmanager=ruolo.getID();
		int idutente=utente.getID();
		boolean notifica=Notifica.creanotifica("E' stata effettuata una richiesta per diventare trascrittore!! Clicca qui o su \"Accetta Domande\" ",idmanager,idutente);
		if(!(notifica)) {
			notificacolore=false;
			return false;
		}else
			notificacolore=true;
		return modificastato;
	}
	//prende le notifiche dal db per la domanda da trascrittore che devono arrivare al manager
	public static ArrayList<String> prendinotifichedomanda(){
		elenco= Notifica.prendinotifiche(prendiidmanager(),"Accetta Domande");
		Collections.sort(elenco, new Ordinamentodecrescente());
		String descrizione=null;
		String orario=null;
		String stringa=null;
		ArrayList<String> notifiche=new ArrayList<>();
		for(Notifica n:elenco) {
			descrizione=n.getdescrizione();
			orario=(n.getorario()).toString();
			stringa=descrizione.concat(orario);
			if(notifiche.contains(stringa))
				notifiche.remove(stringa);
			else
				notifiche.add(stringa);
		}	
		return notifiche;
	}
}

class Ordinamentodecrescente implements Comparator<Notifica>{
	public int compare(Notifica not1,Notifica not2) {
		return not2.getid()-not1.getid();
	}
}
