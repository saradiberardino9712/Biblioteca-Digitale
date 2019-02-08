package Business.Controller;

import Business.Model.Utente;
import java.util.ArrayList;
import Business.Model.Immagine;
import Business.Model.Opera;
import Business.Model.TestoDigitale;

public class controller_consultazione {
	
	public static String open;
	public static String ruoloutente;
	public static ArrayList<Immagine> img;
	public static ArrayList<TestoDigitale> trascrizioni;

	public static boolean avvia(String frase) {
		String titolo;
		for(Opera o: controller_ricerca.listaopere) {
			titolo=o.getTitolo();
			if(titolo.equals(frase)) {
				open=titolo;
				return true;
			}
		}
		for(TestoDigitale t: controller_ricerca.trascrizioni) {
			titolo=t.getTitoloOpera();
			if(frase.contains(titolo)) {
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

	public static ArrayList<TestoDigitale> prenditrascrizioni() {
		trascrizioni=TestoDigitale.verifica("pubblicato");
		ArrayList<TestoDigitale> lista= new ArrayList<>();
		for(TestoDigitale t:trascrizioni) {
			if(t.getTitoloOpera().equals(open))
				lista.add(t);
		}
		return lista;
	}

}
