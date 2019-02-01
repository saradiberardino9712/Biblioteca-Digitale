package Business.Controller;

import java.util.ArrayList;

import Business.Model.Opera;

public class controller_ricerca {
	
	private static ArrayList<Opera> listaopere;
	
	public static boolean verifica() {
		listaopere= Opera.prendiopere("pubblicata");
		boolean controllo;
		if(listaopere.isEmpty()) {
			controllo=false;
		}else
			controllo=true;
		return controllo;
	}
}
