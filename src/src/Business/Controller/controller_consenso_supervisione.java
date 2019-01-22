package Business.Controller;

import java.util.ArrayList;
import Business.Model.Immagine;
import Business.Model.Notifica;
import Business.Model.Ruolo;
import Business.Model.Utente;
import View.FrontController.ManagerPageController;

public class controller_consenso_supervisione {
	
	public static ArrayList<Immagine> listaimg;
	public static ArrayList<Immagine> imgeliminate;
	private static int npag;
	private static String titolo;
	
	public static boolean verifica() {
		listaimg= Immagine.verifica("in acquisizione");
		boolean controllo;
		if(listaimg.isEmpty()) {
			controllo=false;
		}else
			controllo=true;
		return controllo;
	}
	
	public static String prendiurl(String selezione) {
		String url = null;
		for(Immagine i:controller_consenso_supervisione.listaimg) {
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
	
	public static boolean consenso(String esito) {
		boolean notifica=false;
		boolean vista=false;
		int idutente=Utente.getIstance().getID();
		if(esito.equals("yes")) {
			boolean update=Immagine.updatestato("in attesa supervisione", npag, titolo);
			if(update) {
				int idutentenot=prendiid("Supervisore");
				notifica=Notifica.creanotifica("E' stata caricata un'immagine!! Clicca qui o su \" Controlla \" ",idutentenot,idutente);
				String not=ManagerPageController.notifica;
				String desc="Consenso supervisione \" ";
				int d=not.indexOf(desc);
				String orario=not.substring(d+desc.length());
				vista=Notifica.updatevistadb("Consenso supervisione",orario);
			}
		}else {
			boolean update=Immagine.updatestato("eliminata", npag, titolo);
			if(update) {
				int idutentenot=prendiid("Acquisitore");
				notifica=Notifica.creanotifica("Una immagine non è stata approvata!! Clicca qui o vedere \" Elenco immagini negate \" ",idutentenot,idutente);
				String not=ManagerPageController.notifica;
				String desc="Consenso supervisione \" ";
				int d=not.indexOf(desc);
				String orario=not.substring(d+desc.length());
				vista=Notifica.updatevistadb("Consenso supervisione",orario);
			}
		}
		if(notifica && vista)
			return true;
		else 
			return false;
	}
	
	public static void eliminate() {
		imgeliminate= Immagine.verifica("eliminata");
	}
	
	@SuppressWarnings("unused")
	public static void updatenot(String desc, String orario) {
		boolean vista=Notifica.updatevistadb("Elenco immagini negate",orario);
	}
}
