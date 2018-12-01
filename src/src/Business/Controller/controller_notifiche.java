package Business.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import Business.Model.Notifica;
import Business.Model.Utente;

public class controller_notifiche {
	
	public static ArrayList<String> notifiche;
	public static ArrayList<String> viste;
	public static ArrayList<Notifica> elenco;
	
	public static boolean prendinotificheutente(){
		Utente utente=Utente.getIstance();
		elenco= Notifica.prendinotifiche(utente.getIDruolo()," ");
		Collections.sort(elenco, new Ordinamentodecrescente());
		String descrizione=null;
		String orario=null;
		String stringa=null;
		notifiche=new ArrayList<>();
		for(Notifica n:elenco) {
			descrizione=n.getdescrizione();
			orario=(n.getorario()).toString();
			stringa=descrizione.concat(orario);
			if(notifiche.contains(stringa))
				notifiche.remove(stringa);
			else
				notifiche.add(stringa);
		}	
		if(notifiche.isEmpty())
			return false;
		else
			return true;
	}
}

	class Ordinamentodecrescente implements Comparator<Notifica>{
		public int compare(Notifica not1,Notifica not2) {
			return not2.getid()-not1.getid();
		}
}
