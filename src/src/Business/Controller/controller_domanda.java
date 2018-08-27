package Business.Controller;

import java.util.ArrayList;
import Business.Model.Notifica;
import Business.Model.Ruolo;
import Business.Model.Utente;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class controller_domanda {
	
	public static String nome;
	public static String cognome;
	public static String ruolo;
	private static Utente utente;
	
	public static boolean datirichiesta(Label txtemailua, Label txtruoloua) {
		String email=txtemailua.getText();
		utente=Utente.cercautentedb(email, null);
		if(utente==null)
			return false;
		nome=utente.getNome();
		cognome=utente.getCognome();
		ruolo=txtruoloua.getText();
		return true;
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
		int idutente=utente.getIDruolo();
		boolean notifica=Notifica.creanotifica("E' stata effettuata una richiesta per diventare trascrittore!! Clicca su \"Accetta Domande\" ",idmanager,idutente);
		if(!(notifica))
			return false;
		return modificastato;
	}
	
	public static ArrayList<String> prendinotifiche(){
		Ruolo ruolo=Ruolo.prendiiddb("Manager");
		int idmanager=0;
		if(!(ruolo==null))
			idmanager=ruolo.getID();
		ArrayList<Notifica> elenco= Notifica.prendinotifiche(idmanager);
		String descrizione=null;
		String orario=null;
		String stringa=null;
		ArrayList<String> notifiche=new ArrayList<>();
		for(Notifica n:elenco) {
			descrizione=n.getdescrizione();
			orario=(n.getorario()).toString();
			stringa=descrizione.concat(orario);
			notifiche.add(stringa);
		}
		return notifiche;
	}
}
