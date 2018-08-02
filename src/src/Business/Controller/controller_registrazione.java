package Business.Controller;

import java.sql.Date;
import java.time.LocalDate;
import Business.Model.Ruolo;
import Business.Model.Utente;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class controller_registrazione {
	@SuppressWarnings({ "rawtypes"})
	public static boolean inviaRegistrazione(TextField txtNome, TextField txtCognome, TextField txtIndirizzo,
			TextField txtPassword, DatePicker datapicker, TextField txtEmail, TextField txtTitoloStudio,
			TextField txtProfessione, ComboBox combobox) throws Exception {
		String nome = txtNome.getText();
		String cognome = txtCognome.getText();
		String indirizzo = txtIndirizzo.getText();
		String password = txtPassword.getText();
		LocalDate data= datapicker.getValue();
		Date data_nascita=Date.valueOf(data);
		String email = txtEmail.getText();
		String titolo_studio = txtTitoloStudio.getText();
		String professione = txtProfessione.getText();
		String ruolo = (String) combobox.getValue();
		//associa l'id che il ruolo ha nel db con il ruolo scelto dall'utente nella combobox
		int ID_ruolo = associaid(ruolo);
		// controllo campi che nel database sono not null
		if (!(controllonotnull(nome, cognome, password, email, titolo_studio, ID_ruolo)))
			return false;
		
		boolean inserimento;
		//inserimento dell'utente nel db tramite il model
		inserimento = Utente.inserisciutentedb(nome, cognome, indirizzo, password, data_nascita, email, titolo_studio,
				professione, ID_ruolo);
		return inserimento;
	}

	private static boolean controllonotnull(String nome, String cognome, String password,String email, String titolo_studio, int ID_ruolo) {
		if (nome.length() == 0 || cognome.length() == 0 || password.length() == 0 || email.length() == 0 || titolo_studio.length() == 0 || ID_ruolo == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore Registrazione");
			alert.setHeaderText("Compilare tutti i campi obbligatori(*)");
			alert.showAndWait();
			return false;
		}
		return true;
	}
	
	private static int associaid(String ruolo) {
		Ruolo prendi=Ruolo.prendiiddb(ruolo);
		int idruolo=0;
		if(!(prendi==null))
			idruolo=prendi.getID();
		return idruolo;
	}
}
