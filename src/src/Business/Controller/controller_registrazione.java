package Business.Controller;

import java.util.Date;

import Business.Model.Utente;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
//String nome, String cognome,String indirizzo,String password,Date data_nascita,String email,String titolo_studio,String professione,int ID_ruolo
public class controller_registrazione {
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static boolean inviaRegistrazione(TextField txtNome, TextField txtCognome,TextField txtIndirizzo,TextField txtPassword,TextField txtDataNascita,TextField txtEmail,TextField txtTitoloStudio,
			TextField txtProfessione,ComboBox combobox) {
		String nome =txtNome.getText();
		String cognome=txtCognome.getText();
		String indirizzo=txtIndirizzo.getText();
		String password=txtPassword.getText();
		Date data_nascita= new Date(10,12,1997);
		String email=txtEmail.getText();
		String titolo_studio=txtTitoloStudio.getText();
		String professione=txtProfessione.getText();
		int ruolo=1;
		boolean inserimento=Utente.inserisciutentedb(nome,cognome,indirizzo,password,data_nascita,email,titolo_studio,professione,ruolo);
		return inserimento;
	}
}
