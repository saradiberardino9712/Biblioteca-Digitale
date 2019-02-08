package Business.Controller;

import java.util.ArrayList;
import Business.Model.Opera;
import Business.Model.Utente;
import View.FrontController.EliminaOperaPageController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class controller_eliminazione {

	public static ArrayList<Opera> opere;
	public static ArrayList<String> utenti;
	
	public static ArrayList<String> prendi() {
		opere=Opera.prendiopere("pubblicata");
		ArrayList<String> lista=new ArrayList<>();
		if(opere.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Elimina Opera");
			alert.setHeaderText("Non ci sono opere da eliminare!!");
			alert.showAndWait();
		}else {
			for(Opera o:opere) {
				lista.add(o.getTitolo());
			}
		}
		return lista;
	}

	public static boolean elimina() {
		int id = 0;
		for(Opera o:opere) {
			if(EliminaOperaPageController.selezione.equals(o.getTitolo())) {
				id=o.getID();
			}
		}
		boolean elimina=Opera.remove(id);
		return elimina;
	}

	public static ArrayList<String> prendiu() {
		utenti=Utente.prendiutentiAll();
		if(utenti.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Elimina Utenti");
			alert.setHeaderText("Non ci sono utenti da eliminare!!");
			alert.showAndWait();
		}
		return utenti;
	}

	public static boolean eliminau(TextField lblNomeOpera) {
		String email=lblNomeOpera.getText();
		boolean elimina = false;
		for(String s:utenti) {
			if(s.contains(email)) {
				elimina=Utente.remove(email);
			}
		}
		return elimina;
	}
}
