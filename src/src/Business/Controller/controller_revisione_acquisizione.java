package Business.Controller;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Business.Model.Immagine;
import Business.Model.Notifica;
import Business.Model.Ruolo;
import Business.Model.Utente;

public class controller_revisione_acquisizione {

	public static ArrayList<Immagine> imgesaminare;
	public static ArrayList<Immagine> imgsbagliate= new ArrayList<>();
	public static ArrayList<Immagine> imgcorrette= new ArrayList<>();
	private static int npag;
	private static String titolo;
	
	public static boolean verifica() {
		imgesaminare=Immagine.verifica("in attesa supervisione");
		if(imgesaminare.isEmpty())
			return false;
		return true;
	}
	
	public static String prendiurl(String selezione) {
		String url = null;
		for(Immagine i:imgesaminare) {
			npag=i.getNumeropagina();
			titolo=i.getTitoloOpera();
    		if(selezione.contains(titolo) && selezione.contains(Integer.toString(npag))) {
    			url=i.getUrl();
    			return url;
    		}
    	}
		return null;
	}
	
	public static int prendiid(String r) {
		Ruolo ruolo=Ruolo.prendiiddb(r);
		int idruolo=0;
		if(!(ruolo==null))
			idruolo=ruolo.getID();
		return idruolo;
	}

	public static boolean esaminare(String decisione) {
		boolean update=false;
		boolean vista=false;
		Immagine img = null;
		if(decisione.equals("corretta")) {
			for(Immagine i:imgesaminare) {
				if(i.getNumeropagina()==npag && i.getTitoloOpera().equals(titolo)) {
					img=i;
					imgcorrette.add(img);
					update=Immagine.updatestato("in caricamento acquisizione", npag, titolo);
				}
			}
		}else {
			for(Immagine i:imgesaminare) {
				if(i.getNumeropagina()==npag && i.getTitoloOpera().equals(titolo)) {
					imgsbagliate.add(i);
					img=i;
					update=Immagine.updatestato("eliminata", npag, titolo);
				}
			}
		}
		imgesaminare.remove(img);
		String elimina = null;
		if(update) {
			for(String s:controller_notifiche.notifiche) {
				if(s.contains("Controlla")) {
					elimina=s;
					String desc="Controlla \" ";
					int d=s.indexOf(desc);
					String orario=s.substring(d+desc.length());
					vista=Notifica.updatevistadb("Controlla",orario);
					break;
				}
			}
		}
		controller_notifiche.notifiche.remove(elimina);
		if(vista)
			return true;
		else 
			return false;
	}

	@SuppressWarnings("unlikely-arg-type")
	public static boolean annulla() throws InterruptedException {
		boolean update;
		boolean notifica=false;
		ArrayList<Boolean> lista=new ArrayList<>();
		int npag;
		String titolo;
		for (Immagine i:imgcorrette) {
			npag=i.getNumeropagina();
			titolo=i.getTitoloOpera();
			update=Immagine.updatestato("in attesa supervisione", npag, titolo);
			int idruolonot=prendiid("Supervisore");
			int idutente=Utente.getIstance().getID();
			notifica=Notifica.creanotifica("E' stata caricata un'immagine!! Clicca qui o su \" Controlla \" ",0,idruolonot,idutente);
			TimeUnit.SECONDS.sleep(1);
			if(update && notifica) {
				lista.add(true);
			}else
				lista.add(false);
		}
		for(Immagine i:imgsbagliate) {
			npag=i.getNumeropagina();
			titolo=i.getTitoloOpera();
			update=Immagine.updatestato("in attesa supervisione", npag, titolo);
			int idruolonot=prendiid("Supervisore");
			int idutente=Utente.getIstance().getID();
			notifica=Notifica.creanotifica("E' stata caricata un'immagine!! Clicca qui o su \" Controlla \" ",0,idruolonot,idutente);
			TimeUnit.SECONDS.sleep(1);
			if(update && notifica) {
				lista.add(true);
			}else
				lista.add(false);
		}
		if(lista.contains("false") || notifica==false) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unlikely-arg-type")
	public static boolean completa() throws InterruptedException {
		boolean update;
		boolean notifica=false;
		ArrayList<Boolean> lista=new ArrayList<>();
		int npag;
		String titolo;
		ArrayList<Immagine> corrette=new ArrayList<>();
		ArrayList<Immagine> sbagliate=new ArrayList<>();
		for(Immagine i:imgcorrette) {
			corrette.add(i);
			npag=i.getNumeropagina();
			titolo=i.getTitoloOpera();
			update=Immagine.updatestato("in revisione acquisizione", npag, titolo);
			int idruolonot=prendiid("Manager");
			int idutente=Utente.getIstance().getID();
			notifica=Notifica.creanotifica("E' stata revisionata un'immagine acquisita!! Clicca qui o su \" Consenti pubblicazione \" ",0,idruolonot,idutente);
			TimeUnit.SECONDS.sleep(1);
			if(update && notifica) {
				lista.add(true);
			}else
				lista.add(false);
		}
		for(Immagine i:imgsbagliate) {
			sbagliate.add(i);
			npag=i.getNumeropagina();
			titolo=i.getTitoloOpera();
			update=Immagine.updatestato("eliminata", npag, titolo);
			int idruolonot=prendiid("Acquisitore");
			int idutente=Utente.getIstance().getID();
			notifica=Notifica.creanotifica("Una immagine non è stata approvata!! Clicca qui o vedere \" Elenco immagini negate \" ",0,idruolonot,idutente);
			TimeUnit.SECONDS.sleep(1);
			if(update && notifica) {
				lista.add(true);
			}else
				lista.add(false);
		}
		imgcorrette.removeAll(corrette);
		imgsbagliate.removeAll(sbagliate);
		if(lista.contains("false") && notifica==false) {
			return false;
		}
		return true;
	}

}
