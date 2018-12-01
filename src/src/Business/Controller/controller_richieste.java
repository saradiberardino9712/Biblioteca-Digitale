package Business.Controller;

import java.util.ArrayList;
import Business.Model.Notifica;
import Business.Model.Ruolo;
import Business.Model.Utente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class controller_richieste {
	
	public static String nome;
	public static String cognome;
	public static String ruolo;
	public static String titolostudio;
	
	public static ObservableList<String> prendiutentidomanda(){
		ArrayList<Integer> listaid=new ArrayList<>();
		for(Notifica n:controller_notifiche.elenco) {
			if((n.getdescrizione()).contains("Accetta/Rifiuta"))
				listaid.add(n.getidutente());
		}
		ArrayList<String> utenti=Utente.prendiutentidomandadb(listaid);
		int count=0;
		ObservableList<String> utentidomanda=FXCollections.observableArrayList();
		for(String u:utenti) {
			count+=1;
			String sostituzione="ruolo: ";
			int s=u.indexOf(sostituzione);
			String finale=" e";
			int f=u.indexOf(finale);
			String cambiare=u.substring(s+sostituzione.length(),f);
			Ruolo ruolo=Ruolo.prendiruolodb(Integer.parseInt(cambiare));
			String r=ruolo.getNomeRuolo();
			String stringa=u.replace(cambiare,r);
			String newstringa="Richiesta " + count + stringa;
			utentidomanda.add(newstringa);
		}
		return utentidomanda;
	}
	
	public static boolean esaminarichiesta(String frase) {
		String inizionomecogn="signora ";
		int inc=frase.indexOf(inizionomecogn);
		String finenomecogn=" con";
		int fnc=frase.indexOf(finenomecogn);
		String nomecognome=frase.substring(inc+inizionomecogn.length(), fnc);
		String spazio=" ";
		int s=nomecognome.indexOf(spazio);
		nome=nomecognome.substring(0,s);
		cognome=nomecognome.substring(s);
		String inizioruolo="ruolo: ";
		int ir=frase.indexOf(inizioruolo);
		String fineruolo=" e";
		int fr= frase.indexOf(fineruolo);
		ruolo=frase.substring(ir+inizioruolo.length(), fr);
		String iniziotitolo="studio: ";
		int it=frase.indexOf(iniziotitolo);
		titolostudio=frase.substring(it+iniziotitolo.length());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Invio responso domanda");
		alert.setHeaderText(titolostudio);
		alert.showAndWait();
		return true;
	}
	
	public static boolean accettadomanda() {
		Ruolo ruolo1=Ruolo.prendiiddb(ruolo);
		int idruolo=ruolo1.getID();
		boolean aggiorna=Utente.accettarifiutodomanda(nome,cognome,idruolo,titolostudio,"accettata");
		return aggiorna;
	}
	
	public static boolean rifiutadomanda() {
		Ruolo ruolo1=Ruolo.prendiiddb(ruolo);
		int idruolo=ruolo1.getID();
		boolean aggiorna=Utente.accettarifiutodomanda(nome,cognome,idruolo,titolostudio,"rifiutata");
		return aggiorna;
	}
}
