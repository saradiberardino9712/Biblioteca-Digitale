package Business.Controller;

import Business.Model.Utente;
import javafx.scene.control.Label;

public class controller_logout {
	public static boolean disattivautente(Label txtemailua){
		String email=txtemailua.getText();
		Utente utente=Utente.cercautentedb(email, null);
		if(Utente.aggiornautentedb(utente, false))
			return true;
		return false;
	}
}
