package Business.Controller;

import java.time.LocalDate;

import Business.Model.Richiesta;
import Business.Model.Utente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class controller_domanda {
	
	public static String nome;
	public static String cognome;
	public static String ruolo;
	private static Utente utente;
	public static ObservableList<String> list;
	
	public static boolean datirichiesta(Label txtemailua, Label txtruoloua) {
		String email=txtemailua.getText();
		utente=Utente.cercautentedb(email, null);
		if(utente==null)
			return false;
		nome=utente.getNome();
		cognome=utente.getCognome();
		ruolo=txtruoloua.getText();
		return true;
	}
	
	public static boolean invia(TextField txttitolostudio) {
		String titolostudio=txttitolostudio.getText();
		if(!titolostudio.equals(utente.getTitoloStudio()))
			return false;
		boolean inseriscirichiesta=Richiesta.inseriscirichiestadb(nome, cognome, titolostudio);
		list=FXCollections.observableArrayList("E'stata effettuata una richiesta per diventare trascrittore. Vai nella sessione accetta richiesta!! " + LocalDate.now());
		return inseriscirichiesta;
	}
}
