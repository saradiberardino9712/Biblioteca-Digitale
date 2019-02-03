package Business.Controller;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import Business.Model.Immagine;
import Business.Model.Notifica;
import Business.Model.Opera;
import Business.Model.Ruolo;
import Business.Model.Utente;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class controller_consenso_pubblicazione {

	public static ArrayList<Immagine> listaimg;
	public static ArrayList<Immagine> img;
	private static ArrayList<String> opere=new ArrayList<>();
	
	public static boolean verifica() {
		TreeMap<String,Integer> conteggio= new TreeMap<>();
		ArrayList<Opera> listaopere= Opera.prendiopere(null);
		listaimg=Immagine.verifica("in revisione acquisizione");
		String titolo;
		for(Opera o:listaopere) {
			titolo=o.getTitolo();
			int count = 0;
			for(Immagine i:listaimg) {
				if(titolo.equals(i.getTitoloOpera())){
					if(!(conteggio.containsKey(titolo))) {
						count=1;
					}else {
						count=conteggio.get(titolo)+1;
					}
					conteggio.put(titolo, count);
				}
			}
		}
		int opcaricate;
		for(Entry<String, Integer> e:conteggio.entrySet()) {
			titolo=e.getKey();
			opcaricate=e.getValue();
			for(Opera o:listaopere) {
				if(titolo.equals(o.getTitolo()) && opcaricate==o.getPagine()) {
					opere.add(titolo);
				}
			}
		}
		if(opere.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Consenti pubblicazione");
			alert.setHeaderText("Ci sono opere incomplete");
			alert.showAndWait();
			return false;
		}	
		else
			return true;
	}
	
	public static ArrayList<String> prendi() {
		String titolo;
		ArrayList<String> listatitoli=new ArrayList<>();
		for(Immagine i:listaimg) {
			titolo=i.getTitoloOpera();
			if(!(listatitoli.contains(titolo)) && opere.contains(titolo))
				listatitoli.add(titolo);
		}
		return listatitoli;
	}

	public static ArrayList<Immagine> visualizza(String titolo) {
		img=new ArrayList<>();
		for(Immagine i: listaimg) {
			if(titolo.equals(i.getTitoloOpera())){
				img.add(i);
			}
		}
		return img;
	}
	
	public static int prendiid(String r) {
		Ruolo ruolo=Ruolo.prendiiddb(r);
		int idruolo=0;
		if(!(ruolo==null))
			idruolo=ruolo.getID();
		return idruolo;
	}

	public static boolean consenso(String titolo, boolean esito) throws InterruptedException {
		int npag;
		boolean update;
		boolean v;
		ArrayList<Boolean> vista=new ArrayList<>();
		ArrayList<Boolean> u=new ArrayList<>();
		boolean notifica = false;
		String elimina = null;
		if(esito) {
			for(Immagine i:img) {
				npag=i.getNumeropagina();
				update=Immagine.updatestato("acquisito", npag, titolo);
				u.add(update);
				for(String s:controller_notifiche.notifiche) {
					if(s.contains("Consenti pubblicazione")) {
						elimina=s;
						String desc="pubblicazione \"";
						int d=s.indexOf(desc);
						String orario=s.substring(d+desc.length());
						v=Notifica.updatevistadb("Consenti pubblicazione",orario);
						vista.add(v);
						break;
					}
				}
				controller_notifiche.notifiche.remove(elimina);
			}
			if(u.contains(false)|| vista.contains(false)) {
				return false;
			}else {
				int idruolonot=prendiid("Supervisore");
				int idutente=Utente.getIstance().getID();
				notifica=Notifica.creanotifica("E' stato dato il consenso per la pubblicazione!! Clicca qui o su \" Pubblica \" ",0,idruolonot,idutente);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Consenti pubblicazione");
				alert.setHeaderText("Il consenso è stato ammesso");
				alert.showAndWait();
			}
		}else {
			for(Immagine i:img) {
				npag=i.getNumeropagina();
				update=Immagine.updatestato("in attesa supervisione", npag, titolo);
				u.add(update);
				for(String s:controller_notifiche.notifiche) {
					if(s.contains("Consenti pubblicazione")) {
						elimina=s;
						String desc="pubblicazione \"";
						int d=s.indexOf(desc);
						String orario=s.substring(d+desc.length());
						v=Notifica.updatevistadb("Consenti pubblicazione",orario);
						vista.add(v);
						break;
					}
				}
				controller_notifiche.notifiche.remove(elimina);
			}
			if(u.contains(false)|| vista.contains(false)) {
				return false;
			}else {
				for(int i=0;i<img.size();i++) {
					int idruolonot=prendiid("Supervisore");
					int idutente=Utente.getIstance().getID();
					notifica=Notifica.creanotifica("La revisione dell'immagine non è stata approvata!! Clicca qui o su \" Controlla \" ",0,idruolonot,idutente);
				}
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Consenti pubblicazione");
				alert.setHeaderText("Il consenso è stato negato");
				alert.showAndWait();
			}
		}
		if(notifica)
			return true;
		else 
			return false;	
	}	
}
