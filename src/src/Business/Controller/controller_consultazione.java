package Business.Controller;

import Business.Model.Utente;
import java.util.ArrayList;
import Business.Model.Immagine;
import Business.Model.Opera;

public class controller_consultazione {
	
	public static String open;
	public static String ruoloutente;
	public static ArrayList<Immagine> img;

	public static boolean avvia(String frase) {
		String titolo;
		for(Opera o: controller_ricerca.listaopere) {
			titolo=o.getTitolo();
			if(titolo.equals(frase)) {
				open=titolo;
				return true;
			}
		}
		return false;
	}
	
	public static void impostaruolo() {
		ruoloutente=Utente.getIstance().getNomeRuolo();
	}

	public static ArrayList<Immagine> prendiimmagini() {
		img=Immagine.prendiimgopera(open);
		return img;
	}

}
