package Business.Controller;

import java.util.ArrayList;
import java.util.TreeMap;
import Business.Model.Notifica;
import Business.Model.Ruolo;
import Business.Model.Utente;
import View.FrontController.ManagerPageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class controller_richieste {
	
	private static ArrayList<String> email=new ArrayList<>();
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
		TreeMap<String,String> utenti=Utente.prendiutentidomandadb(listaid);
		int count=0;
		ObservableList<String> utentidomanda=FXCollections.observableArrayList();
		for(String k:utenti.keySet()) {
			count+=1;
			String sostituzione="ruolo: ";
			int s=k.indexOf(sostituzione);
			String finale=" e";
			int f=k.indexOf(finale);
			String cambiare=k.substring(s+sostituzione.length(),f);
			Ruolo ruolo=Ruolo.prendiruolodb(Integer.parseInt(cambiare));
			String r=ruolo.getNomeRuolo();
			String stringa=k.replace(cambiare,r);
			String newstringa="Richiesta " + count + stringa;
			utentidomanda.add(newstringa);
		}
		for(String v:utenti.values()) {
			email.add(v);
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
		return true;
	}
	
	public static boolean accettadomanda(String frase) {
		String in_richiesta= "Richiesta ";
		int in_r=frase.indexOf(in_richiesta);
		String fn_richiesta=": ";
		int fn_r=frase.indexOf(fn_richiesta);
		String posizione=frase.substring(in_r+in_richiesta.length(), fn_r);
		int pos=Integer.parseInt(posizione);
		String emailu= email.get(pos-1);
		email.remove(pos-1);
		boolean aggiorna=Utente.accettarifiutadomanda(emailu,"accettata");
		boolean notifica=false;
		boolean vista=false;
		if(aggiorna) {
			int idutente=Utente.getIstance().getID();
			int idutentenot=Utente.prendiidutente(emailu,0);
			notifica=Notifica.creanotifica("La tua richiesta è stata accettata!! ",idutentenot,idutente);
			String not=ManagerPageController.notifica;
			String desc="Rifiuta\" ";
			int d=not.indexOf(desc);
			String orario=not.substring(d+desc.length());
			vista=Notifica.updatevistadb("Accetta",orario);
		}
		if(notifica && vista)
			return true;
		else
			return false;
	}
	
	public static boolean rifiutadomanda(String frase) {
		String in_richiesta= "Richiesta ";
		int in_r=frase.indexOf(in_richiesta);
		String fn_richiesta=": ";
		int fn_r=frase.indexOf(fn_richiesta);
		String posizione=frase.substring(in_r+in_richiesta.length(), fn_r);
		int pos=Integer.parseInt(posizione);
		String emailu= email.get(pos-1);
		email.remove(pos-1);
		boolean aggiorna=Utente.accettarifiutadomanda(emailu,"rifiutata");
		boolean notifica=false;
		boolean vista=false;
		if(aggiorna) {
			int idutente=Utente.getIstance().getID();
			int idutentenot=Utente.prendiidutente(emailu,0);
			notifica=Notifica.creanotifica("La tua richiesta è stata rifiutata!! ",idutentenot,idutente);
			String not=ManagerPageController.notifica;
			String desc="Rifiuta\" ";
			int d=not.indexOf(desc);
			String orario=not.substring(d+desc.length());
			vista=Notifica.updatevistadb("Rifiuta",orario);
		}
		if(notifica && vista)
			return true;
		else
			return false;
	}
	
	public static boolean cambia() {
		Ruolo trascrittore=Ruolo.prendiiddb("Trascrittore");
		int nuovoid=trascrittore.getID();
		boolean cambia=Utente.cambiaruoloutente(nuovoid);
		return cambia;
	}
}
