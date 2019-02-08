package Business.Controller;

import java.util.ArrayList;

import Business.Model.Opera;
import Business.Model.TestoDigitale;
import javafx.scene.control.TextField;

public class controller_ricerca {
	
	public static ArrayList<Opera> listaopere;
	public static ArrayList<TestoDigitale> trascrizioni;
	public static ArrayList<String> lista=new ArrayList<>();
	
	public static boolean verifica() {
		listaopere= Opera.prendiopere("pubblicata");
		boolean controllo;
		if(listaopere.isEmpty()) {
			controllo=false;
		}else
			controllo=true;
		return controllo;
	}
	
	public static boolean cerca(boolean catalogo, boolean testo,TextField txttesto) {
		lista.clear();
		String frase=txttesto.getText().toLowerCase();
		if(catalogo) {
			String titolo;
			String t;
			for(Opera o:listaopere) {
				titolo=o.getTitolo();
				t=titolo.toLowerCase();
				if(t.indexOf(frase)!=-1) {
					lista.add(titolo);
				}
			}
		}
		if(testo) {
			String titolo;
			int npag;
			String text;
			String te;
			String stringa;
			trascrizioni= TestoDigitale.verifica("pubblicato");
			for(TestoDigitale t: trascrizioni) {
				text=t.getText();
				te=text.toLowerCase();
				if(te.contains(frase)) {
					titolo=t.getTitoloOpera();
					npag=t.getNumpag();
					stringa="Opera: "+ titolo + ", pagina: " + npag;
					lista.add(stringa);
				}
			}
		}
		if(lista.isEmpty()) {
			return false;
		}else
			return true;
	}
}
