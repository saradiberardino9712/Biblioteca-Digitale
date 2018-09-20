package Business.Model;

import java.util.ArrayList;
import java.util.Date;
import DAO.UtenteDAO;

public class Utente {
	private int id;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String password;
	private Date data_nascita;
	private String email;
	private String titolo_studio;
	private String professione;
	private String statodomanda;
	private int ID_ruolo;

	public Utente(int id,String nome, String cognome, String indirizzo, String password, Date data_nascita, String email,
			String titolo_studio, String professione, String statodomanda, int ID_ruolo) {
		this.id=id;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.password = password;
		this.data_nascita = data_nascita;
		this.email = email;
		this.titolo_studio = titolo_studio;
		this.professione = professione;
		this.statodomanda=statodomanda;
		this.ID_ruolo = ID_ruolo;
	}
	
	public Utente(int id, String nome, String cognome, String titolo_studio, int ID_ruolo) {
		this.id=id;
		this.nome = nome;
		this.cognome = cognome;
		this.titolo_studio = titolo_studio;
		this.ID_ruolo = ID_ruolo;
	}

	public Utente(String nome, String cognome, String indirizzo) {
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
	}
	
	public int getID() {
		return id;
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

	public void setIndirizzo(String newindirizzo) {
		this.indirizzo = newindirizzo;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setPassword(String newpassword) {
		this.password = newpassword;
	}

	public String getPassword() {
		return password;
	}

	public void setDataNascita(Date newdatanascita) {
		this.data_nascita = newdatanascita;
	}

	public Date getDataNascita() {
		return data_nascita;
	}

	public void setEmail(String newemail) {
		this.email = newemail;
	}

	public String getEmail() {
		return email;
	}

	public String getTitoloStudio() {
		return titolo_studio;
	}

	public void setTitoloStudio(String newtitolostudio) {
		this.titolo_studio = newtitolostudio;
	}

	public String getProfessione() {
		return professione;
	}

	public void setProfessione(String newprofessione) {
		this.professione = newprofessione;
	}
	
	public String getStatodomanda() {
		return statodomanda;
	}

	public int getIDruolo() {
		return ID_ruolo;
	}

	public static boolean inserisciutentedb(String nome, String cognome, String indirizzo, String password,
			Date data_nascita, String email, String titolo_studio, String professione, int ID_ruolo) {
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(nome);
		lista.add(cognome);
		lista.add(indirizzo);
		lista.add(password);
		lista.add(data_nascita);
		lista.add(email);
		lista.add(titolo_studio);
		lista.add(professione);
		lista.add(ID_ruolo);
		//inserimento utente nel db
		boolean inserimentodb = new UtenteDAO().insert(lista);
		return inserimentodb;
	}
	
	public static Utente cercautentedb(String email,String password) {
		ArrayList<Object> lista= new ArrayList<>();
		lista.add(email);
		lista.add(password);
		Utente utente=(Utente) new UtenteDAO().retrieve(lista);
		return utente;
	}
	
	public static boolean aggiornautentedb(Utente utente,boolean valore) {
		ArrayList<Object> lista =new ArrayList<>();
		lista.add(utente);
		lista.add(valore);
		boolean aggiornadb=new UtenteDAO().updatelogin(lista);
		return aggiornadb;
	}
	
	public static boolean modificadatidb(Utente utente) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(utente.getEmail());
		lista.add(utente.getNome());
		lista.add(utente.getCognome());
		lista.add(utente.getIndirizzo());
		lista.add(utente.getPassword());
		lista.add(utente.getDataNascita());
		lista.add(utente.getTitoloStudio());
		lista.add(utente.getProfessione());
		boolean modificadb=new UtenteDAO().updatedati(lista);
		return modificadb;
	}
	
	public static boolean settastato(Utente utente,String stato) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(utente.getEmail());
		lista.add(stato);
		boolean modificastato=new UtenteDAO().updatestato(lista);
		return modificastato;
	}
	
	public static ArrayList<Utente> prendiutentidomandadb(ArrayList<Integer> listaid){
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(listaid);
		ArrayList<Utente> listautenti=new UtenteDAO().retrieveutentidomanda(lista);
		return listautenti;
	} 
	
	public static boolean accettarifiutodomanda(String nome, String cognome, int idruolo, String titolostudio, String modifica) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(nome);
		lista.add(cognome);
		lista.add(idruolo);
		lista.add(titolostudio);
		lista.add(modifica);
		boolean aggiornato=new UtenteDAO().updatedomanda(lista);
		return aggiornato;
	}
}