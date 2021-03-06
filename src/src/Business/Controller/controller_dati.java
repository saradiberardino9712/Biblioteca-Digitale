package Business.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import Business.Model.Utente;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class controller_dati {
	
	public static String nome;
	public static String cognome;
	public static String datanascita;
	public static String indirizzo;
	public static String titolostudio;
	public static String professione;
	public static String password;
	public static String ruolo;
	public static String email;
	private static Utente utente;
	
	public static boolean visualizza() {
		utente=Utente.getIstance();
		if(utente==null)
			return false;
		email=utente.getEmail();
		nome=utente.getNome();
		cognome=utente.getCognome();
		java.util.Date data=utente.getDataNascita();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		datanascita = formatter.format(data);
		indirizzo=utente.getIndirizzo();
		titolostudio=utente.getTitoloStudio();
		professione=utente.getProfessione();
		password=utente.getPassword();
		ruolo=utente.getNomeRuolo();
		return true;
	}
	
	public static boolean modifica(TextField txtnome,TextField txtcognome,TextField txtindirizzo,TextField txttitolostudio,TextField txtprofessione,PasswordField txtpassword,PasswordField txtpassword1,DatePicker datapicker) throws Exception {
		String nome=txtnome.getText();
		String cognome=txtcognome.getText();
		String indirizzo=txtindirizzo.getText();
		String titolostudio=txttitolostudio.getText();
		String professione=txtprofessione.getText();
		String password=txtpassword.getText();
		String password1=txtpassword1.getText();
		String datamodificata =datapicker.getPromptText();
		if(nome.length()==0 && cognome.length()==0 && indirizzo.length()==0 && titolostudio.length()==0 && 
				professione.length()==0 && password.length()==0 && password1.length()==0 && datamodificata.equals(datanascita)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Modifica dati");
			alert.setHeaderText("Non sono state effettuate modifiche!!");
			alert.showAndWait();
			return false;
		}
		if(nome.length()!=0) 
			utente.setNome(nome);
		if(cognome.length()!=0)
			utente.setCognome(cognome);
		if(indirizzo.length()!=0)
			utente.setIndirizzo(indirizzo);
		if(titolostudio.length()!=0)
			utente.setTitoloStudio(titolostudio);
		if(professione.length()!=0)
			utente.setProfessione(professione);
		if(password.length()!=0 && password1.length()!=0) {
			if(password.equals(password1)) {
				utente.setPassword(password);
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Modifica dati");
				alert.setHeaderText("Password e conferma password non sono uguali ricontrollare");
				alert.showAndWait();
				visualizzaerrore(txtpassword, txtpassword1);
				return false;
			}
		}	
		Date data=utente.getDataNascita();
		if(!(datamodificata.equals(datanascita)))
			data=new SimpleDateFormat("yyyy/MM/dd").parse(datanascita);
			utente.setDataNascita(data);
		boolean modificadb=Utente.modificadatidb();
		if(modificadb) {
			modificavisualizza();
		}
		return modificadb;
	}
	
	private static void visualizzaerrore(PasswordField txtpassword,PasswordField txtpassword1) {
		txtpassword1.setText(null);
		txtpassword.setText(null);
		txtpassword.setStyle(" -fx-base: red;");
		txtpassword1.setStyle(" -fx-base: red;");
	}
	
	private static void modificavisualizza() {
		nome=utente.getNome();
		cognome=utente.getCognome();
		java.util.Date data=utente.getDataNascita();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		datanascita = formatter.format(data);
		indirizzo=utente.getIndirizzo();
		titolostudio=utente.getTitoloStudio();
		professione=utente.getProfessione();
		password=utente.getPassword();
	}
}
