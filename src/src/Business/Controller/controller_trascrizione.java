package Business.Controller;

import java.util.ArrayList;
import Business.Model.Immagine;
import Business.Model.Notifica;
import Business.Model.Ruolo;
import Business.Model.TestoDigitale;
import Business.Model.Utente;

public class controller_trascrizione {

	public static ArrayList<String> listaopere;
	public static ArrayList<Immagine> img;
	public static ArrayList<Integer> idimg=new ArrayList<>();
	
	public static ArrayList<String> verifica() {
		idimg.clear();
		listaopere=new ArrayList<>();
		ArrayList<Notifica> not=Notifica.prendinotifichetesto(Utente.getIstance().getID(), "Trascrivi");
		for(Notifica n:not) {
			idimg.add(n.getIdimg());
		}
		String titolo;
		img=Immagine.verifica("acquisito");
		for(Immagine i:img) {
			if(idimg.contains(i.getID())) {
				titolo=i.getTitoloOpera();
				listaopere.add(titolo);
			}
		}
		return listaopere;
	}

	public static ArrayList<Integer> prendipagine(String selected) {
		ArrayList<Integer> pag= new ArrayList<>();
		for(Immagine i:img) {
			if(idimg.contains(i.getID()) && i.getTitoloOpera().equals(selected)) 
				pag.add(i.getNumeropagina());
		}
		return pag;
	}
	
	public static int prendiid(String r) {
		Ruolo ruolo=Ruolo.prendiiddb(r);
		int idruolo=0;
		if(!(ruolo==null))
			idruolo=ruolo.getID();
		return idruolo;
	}

	public static boolean completa(String testo, int pagina, String selected) {
		boolean inserisci;
		boolean notifica;
		boolean vista;
		int idutente=Utente.getIstance().getID();
		int idimmagine = 0;
		for(Immagine i:img) {
			if(i.getNumeropagina()==pagina && i.getTitoloOpera().equals(selected)){
					idimmagine=i.getID();
			}
		}
		vista=Notifica.updatevistadbtesto("Trascrivi",null,idimmagine,idutente);
		inserisci= TestoDigitale.caricaTesto(testo, idutente, idimmagine);
		int idruolonot=prendiid("Manager");
		notifica= Notifica.creanotificatesto("E' stata caricata una trascrizione!! E' richiesto il suo consenso!! Clicca qui o su \" Consenti revisione \" ", 0,idruolonot, idutente,idimmagine);
		if(inserisci && notifica && vista)
			return true;
		else
			return false;
	}

	public static ArrayList<String> prenditrascrizioni(String stato) {
		ArrayList<TestoDigitale> lista=TestoDigitale.verifica(stato);
		ArrayList<String> listatrascrizioni=new ArrayList<>();
		String testo;
		for(TestoDigitale t:lista) {
			testo="La trascrizione dell'opera: "+ t.getTitoloOpera()+", pagina n°: "+ t.getNumpag();
			listatrascrizioni.add(testo);
		}
		return listatrascrizioni;
	}

	public static boolean aggiorna() {
		boolean update = false;
		ArrayList<Notifica>not=controller_notifiche.elenco;
		for(Notifica n:not) {
			if(n.getdescrizione().contains("negate")) {
				update=Notifica.updatevistadb(n.getdescrizione(), n.getorario().toString());
				break;
			}
		}
		return update;
	}

}
