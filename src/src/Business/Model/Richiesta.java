package Business.Model;

import java.util.ArrayList;
import DAO.RichiestaDAO;

public class Richiesta {
	private String nome;
	private String cognome;
	private String titolo_studio;
	
	public Richiesta(String nome,String cognome,String titolo_studio) {
		this.nome = nome;
		this.cognome = cognome;
		this.titolo_studio = titolo_studio;
	}
	
	public void setNome(String newnome) {
		this.nome = newnome;
	}

	public String getNome() {
		return nome;
	}

	public void setCognome(String newcognome) {
		this.cognome = newcognome;
	}

	public String getCognome() {
		return cognome;
	}
	
	public String getTitoloStudio() {
		return titolo_studio;
	}

	public void setTitoloStudio(String newtitolostudio) {
		this.titolo_studio = newtitolostudio;
	}
	
	public static boolean inseriscirichiestadb(String nome, String cognome,String titolo_studio) {
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(nome);
		lista.add(cognome);
		lista.add(titolo_studio);
		lista.add("in attesa");
		//inserimento utente nel db
		boolean inserimentodb = new RichiestaDAO().insert(lista);
		return inserimentodb;
	}
}
