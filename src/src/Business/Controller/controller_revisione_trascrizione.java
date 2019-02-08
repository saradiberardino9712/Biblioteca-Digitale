package Business.Controller;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map.Entry;
import Business.Model.Notifica;
import Business.Model.Ruolo;
import Business.Model.TestoDigitale;
import Business.Model.Utente;
import View.FrontController.RevisionaTrascrizioniPageController;

public class controller_revisione_trascrizione {

	public static ArrayList<TestoDigitale> traesaminare;
	public static TreeMap<String,TreeMap<Integer,Integer>> idimg=new TreeMap<>();
	private static ArrayList<String> lista;
	
	public static boolean verifica() {
		traesaminare=TestoDigitale.verifica("in attesa revisione");
		if(traesaminare.isEmpty())
			return false;
		return true;
	}

	public static ArrayList<String> prenditesto(String selezione) {
		idimg.clear();
		String testo = null;
		lista=new ArrayList<>();
		int npag;
		String titolo;
		for(TestoDigitale t:traesaminare) {
			npag=t.getNumpag();
			titolo=t.getTitoloOpera();
			TreeMap<Integer,Integer> id=new TreeMap<>();
			id.put(npag, t.getIdimmagine());
			idimg.put(titolo, id);
    		if(selezione.contains(titolo) && selezione.contains(Integer.toString(npag))) {
    			testo=t.getText();
    			lista.add(testo);
    		}
    	}
		return lista;
	}
	
	public static int prendiid(String r) {
		Ruolo ruolo=Ruolo.prendiiddb(r);
		int idruolo=0;
		if(!(ruolo==null))
			idruolo=ruolo.getID();
		return idruolo;
	}

	public static boolean conferma(String testo) {
		boolean r;
		boolean vista = false;
		int idimmagine = 0;
		ArrayList<Boolean> remove= new ArrayList<>();
		for(Entry<String, TreeMap<Integer, Integer>> e:idimg.entrySet()) {
			if(RevisionaTrascrizioniPageController.selezione.contains(e.getKey())){
				TreeMap<Integer,Integer> i=e.getValue();
				for(Integer elem:i.keySet()) {
					if(RevisionaTrascrizioniPageController.selezione.contains(Integer.toString(elem))) {
						idimmagine=i.get(elem);
						for(String s: lista) {
							r=TestoDigitale.remove(s, idimmagine);
							vista=Notifica.updatevistadbtesto("Revisiona", null, idimmagine, 0);
							remove.add(r);
						}
					}
				}
			}
		}
		if(!(remove.contains(false)) && vista) {
			int idruolonot=prendiid("Manager");
			boolean add=TestoDigitale.AggiornaTesto(testo, Utente.getIstance().getID(), idimmagine);
			boolean notifica=Notifica.creanotificatesto("E' stata revisionata una trascrizione!! Clicca qui o su \" Consenti pubblicazione \"", 0, idruolonot,  Utente.getIstance().getID(), idimmagine);
			if(add && notifica)
				return true;
			else
				return false;
		}else
			return false;
	}

}
