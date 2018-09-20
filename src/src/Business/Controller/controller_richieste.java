package Business.Controller;

import java.util.ArrayList;
import Business.Model.Notifica;
import Business.Model.Ruolo;
import Business.Model.Utente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class controller_richieste {
	
	private static ArrayList<Integer> richiestedaesaminare=new ArrayList<>();
	private static ArrayList<Utente> utenti=new ArrayList<>();
	public static String nome;
	public static String cognome;
	public static String ruolo;
	public static String titolostudio;
	public static String stringa;
	
	public static ObservableList<String> prendiutentidomanda(){
		ArrayList<Integer> listaid=new ArrayList<>();
		for(Notifica n:controller_domanda.elenco) {
			listaid.add(n.getidutente());
		}
		utenti=Utente.prendiutentidomandadb(listaid);
		String nome;
		String cognome;
		String ruolo;
		String titolostudio;
		String stringa;
		int id;
		ObservableList<String> utentidomanda=FXCollections.observableArrayList();
		int count=0;
		for (Utente u:utenti) {
				count+=1;
				id=u.getID();
				richiestedaesaminare.add(id);
				nome =u.getNome();
				cognome=u.getCognome();
				Ruolo ruolodb=Ruolo.cercaruolodb(u);
				ruolo=ruolodb.getNomeRuolo();
				titolostudio=u.getTitoloStudio();
				stringa="Richiesta " + count + ": il signor/signora " + nome + " " + cognome + " con ruolo: " + ruolo + " e titolo di studio: " + titolostudio;
				utentidomanda.add(stringa);
		}	
		return utentidomanda;
	}
	
	public static boolean esaminarichiesta(String frase) {
		String i="Richiesta ";
		int inizio=frase.indexOf(i);
		String f=": il";
		int fine=frase.indexOf(f);
		String posizione=frase.substring(inizio+i.length(),fine);
		int pos=Integer.parseInt(posizione);
		int idscelta=richiestedaesaminare.get(pos-1);
		boolean trovato=false;
		for(Utente u: utenti) {
			if(u.getID()==idscelta) {
				nome=u.getNome();
				cognome=u.getCognome();
				Ruolo ruolodb=Ruolo.cercaruolodb(u);
				ruolo=ruolodb.getNomeRuolo();
				titolostudio=u.getTitoloStudio();
				trovato=true;
			}
		}
		return trovato;
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
