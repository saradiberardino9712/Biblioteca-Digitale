package Business.Controller;

import Business.Model.Ruolo;
import Business.Model.Utente;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class controller_login {
	public static String ruolo;
	public static String email;
	
	public static boolean verificacredenziali(TextField txtEmail, PasswordField txtPassword) {
		String email=txtEmail.getText();
		String password= txtPassword.getText();
		if (email.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore login");
			alert.setHeaderText("Compilare tutti i campi!!");
			alert.showAndWait();
			txtEmail.setStyle(" -fx-base: red;");
			return false;
		}
		if(password.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore login");
			alert.setHeaderText("Compilare tutti i campi!!");
			alert.showAndWait();
			txtPassword.setStyle(" -fx-base: red;");
			return false;
		}
		//cerca utente nel db con le credenziali inserite dall'utente
		Utente utente=Utente.cercautentedb(email,password);
		Ruolo cercaruolo=null;
		if(utente==null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore login");
			alert.setHeaderText("Email e/o password errati");
			alert.showAndWait();
			txtEmail.setText(null);
			txtPassword.setText(null);
			txtEmail.setStyle(" -fx-base: red;");
			txtPassword.setStyle(" -fx-base: red;");
			return false; 
		}
		else {
			//attraverso l'id cerca il ruolo dell'utente che sta accedendo
			cercaruolo=Ruolo.cercaruolodb(utente);
			if(!(cercaruolo==null)) {
				ruolo=cercaruolo.getNomeRuolo();
				utente.setRuolo(ruolo);
			}	
			if(!(attiva(utente)))
				return false;
		}
		return true;	
	}
	
	public static boolean attiva(Utente utente) {
		if(Utente.aggiornautentedb(true)) {
			email=utente.getEmail();
			return true;
		}	
		return false;
	}
}
