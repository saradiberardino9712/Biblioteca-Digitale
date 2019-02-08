package Business.Controller;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import Business.Model.Notifica;
import Business.Model.Ruolo;
import Business.Model.TestoDigitale;
import Business.Model.Utente;
import View.FrontController.ConsentiRevisionePageController;

public class controller_consenso_revisione {

	public static ArrayList<TestoDigitale> listatrascrizioni;
	public static TreeMap<String,TreeMap<Integer,Integer>> idimg=new TreeMap<>();
	private static ArrayList<String> lista;
	
	public static boolean verifica() {
		listatrascrizioni= TestoDigitale.verifica("in trascrizione");
		boolean controllo;
		if(listatrascrizioni.isEmpty()) {
			controllo=false;
		}else
			controllo=true;
		return controllo;
	}

	public static ArrayList<String> prenditesto(String selezione) {
		idimg.clear();
		String testo = null;
		int npag;
		String titolo;
		lista=new ArrayList<>();
		for(TestoDigitale t:listatrascrizioni) {
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
	
	public static boolean consenso(String esito) {
		boolean notifica=false;
		boolean vista=false;
		boolean remove = false;
		boolean update= false;
		int idutente=Utente.getIstance().getID();
		int idimmagine = 0;
		if(esito.equals("yes")) {
			for(Entry<String, TreeMap<Integer, Integer>> e:idimg.entrySet()) {
				if(ConsentiRevisionePageController.selezione.contains(e.getKey())){
					TreeMap<Integer,Integer> i=e.getValue();
					for(Integer elem:i.keySet()) {
						if(ConsentiRevisionePageController.selezione.contains(Integer.toString(elem))) {
							idimmagine=i.get(elem);
							update=TestoDigitale.updatestato("in attesa revisione", idimmagine);
						}
					}
				}
			}
			if(update) {
				int idruolonot=prendiid("Revisore");
				notifica=Notifica.creanotificatesto("E' stata caricata una trascrizione!! Clicca qui o su \" Revisiona \" ",0,idruolonot,idutente,idimmagine);
				vista=Notifica.updatevistadbtesto("Consenti revisione",null,idimmagine,0);
			}
		}else {
			for(Entry<String, TreeMap<Integer, Integer>> e:idimg.entrySet()) {
				if(ConsentiRevisionePageController.selezione.contains(e.getKey())){
					TreeMap<Integer,Integer> i=e.getValue();
					for(Integer elem:i.keySet()) {
						if(ConsentiRevisionePageController.selezione.contains(Integer.toString(elem))) {
							idimmagine=i.get(elem);
							update=TestoDigitale.updatestato("in attesa revisione", idimmagine);
						}
					}
				}
			}
			remove = false;
			for(String s:lista) {
				remove=TestoDigitale.remove(s,idimmagine);
			}
			vista=Notifica.updatevistadbtesto("Consenti revisione",null,idimmagine,0);
		}
		if(notifica && vista || vista && remove)
			return true;
		else 
			return false;
	}

}
