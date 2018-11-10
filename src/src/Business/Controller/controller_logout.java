package Business.Controller;

import Business.Model.Utente;

public class controller_logout {
	
	public static boolean disattivautente(){
		if(Utente.aggiornautentedb(false)) {
			Utente.delete();
			if(Utente.getIstance()==null) {
				return true;
			}
		}	
		return false;
	}
}
