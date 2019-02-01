package Business.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import Business.Model.Notifica;
import Business.Model.Utente;
import View.FrontController.UtenteBasePageController;
import View.FrontController.UtentePrivilegiatoPageController;

public class controller_notifiche {
	
	public static ArrayList<String> notifiche;
	public static ArrayList<Notifica> elenco;
	
	public static boolean prendinotificheutente(){
		Utente utente=Utente.getIstance();
		elenco= Notifica.prendinotifiche(utente.getID(),utente.getIDruolo(),null);
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
	
	public static boolean vista() {
		String not=UtenteBasePageController.notifica;
		boolean vista=false;
		if(not.contains("accettata")) {
			String desc="accettata!!";
			int d=not.indexOf(desc);
			String orario=not.substring(d+desc.length());
			vista=Notifica.updatevistadb("accettata",orario);
		}else {
			String desc="rifiutata!!";
			int d=not.indexOf(desc);
			String orario=not.substring(d+desc.length());
			vista=Notifica.updatevistadb("rifiutata",orario);
		}
		return vista;
	}
	
	public static boolean vista1() {
		String not=UtentePrivilegiatoPageController.notifica;
		boolean vista=false;
		if(not.contains("accettata")) {
			String desc="accettata!!";
			int d=not.indexOf(desc);
			String orario=not.substring(d+desc.length());
			vista=Notifica.updatevistadb("accettata",orario);
		}else {
			String desc="rifiutata!!";
			int d=not.indexOf(desc);
			String orario=not.substring(d+desc.length());
			vista=Notifica.updatevistadb("rifiutata",orario);
		}
		return vista;
	}
	
	public static ArrayList<Notifica> esistenza(){
		ArrayList<Notifica> lista= Notifica.prendinotifiche(0,Utente.getIstance().getIDruolo(), "Elenco immagini negate");
		return lista;
	}
}

	class Ordinamentodecrescente implements Comparator<Notifica>{
		public int compare(Notifica not1,Notifica not2) {
			return not2.getid()-not1.getid();
		}
}
