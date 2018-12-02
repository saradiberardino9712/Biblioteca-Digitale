package Business.Model;

import java.util.Date;
import java.util.TreeMap;
import java.util.ArrayList;
import DAO.UtenteDAO;

public class Utente {
	
	private static Utente instance;
	
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
	private Ruolo ruolo;
	
	private Utente(int id, String nome, String cognome, String indirizzo, String password, Date data_nascita, String email,
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
		this.statodomanda = statodomanda;
		this.ruolo = new Ruolo(ID_ruolo,null);
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

	public String getEmail() {
		return email;
	}
	
	public void setTitoloStudio(String newtitolostudio) {
		this.titolo_studio = newtitolostudio;
	}
	
	public String getTitoloStudio() {
		return titolo_studio;
	}
	
	public void setProfessione(String newprofessione) {
		this.professione = newprofessione;
	}
	
	public String getProfessione() {
		return professione;
	}

	public int getIDruolo() {
		return ruolo.getID();
	}
	
	public void setRuolo(String newruolo) {
		ruolo.setNomeRuolo(newruolo);
	}
	
	public String getNomeRuolo() {
		return ruolo.getNomeRuolo();
	}
	
	public static boolean inserisciutentedb(String nome, String cognome, String indirizzo, String password, Date data_nascita, String email,
			String titolo_studio, String professione,int ID_ruolo) {
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
	
	public static Utente cercautentedb(String email, String password) {
		ArrayList<Object> lista= new ArrayList<>();
		lista.add(email);
		lista.add(password);
		Utente utente=(Utente) new UtenteDAO().retrieve(lista);
		return utente;
	}
	
	public static boolean aggiornautentedb(boolean valore) {
		ArrayList<Object> lista =new ArrayList<>();
		lista.add(instance);
		lista.add(valore);
		boolean aggiornadb=new UtenteDAO().updatelogin(lista);
		return aggiornadb;
	}
	
	public static boolean modificadatidb() {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(instance.getEmail());
		lista.add(instance.getNome());
		lista.add(instance.getCognome());
		lista.add(instance.getIndirizzo());
		lista.add(instance.getPassword());
		lista.add(instance.getDataNascita());
		lista.add(instance.getTitoloStudio());
		lista.add(instance.getProfessione());
		boolean modificadb=new UtenteDAO().updatedati(lista);
		return modificadb;
	}

	public static boolean settastato(String stato) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(instance.getEmail());
		lista.add(stato);
		boolean modificastato=new UtenteDAO().updatestato(lista);
		return modificastato;
	}
	
	public static TreeMap<String,String> prendiutentidomandadb(ArrayList<Integer> listaid){
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(listaid);
		TreeMap<String,String> listautenti=new UtenteDAO().retrieveutentidomanda(lista);
		return listautenti;
	} 
	
	public static boolean accettarifiutadomanda(String email,String stato) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(email);
		lista.add(stato);
		boolean modificastato=new UtenteDAO().updatestato(lista);
		return modificastato;
	}
	
	public static int prendiidutente(String email,int idruolo) {
		ArrayList<Object>lista=new ArrayList<>();
		lista.add(email);
		lista.add(idruolo);
		int id=new UtenteDAO().retrieveid(lista);
		return id;
	}

	public static boolean cambiaruoloutente(int id) {
		ArrayList<Object>lista=new ArrayList<>();
		lista.add(instance.getEmail());
		lista.add(id);
		boolean cambia=new UtenteDAO().updateid(lista);
		return cambia;
	}
	
	public static Utente delete() {
		instance = null;
		return instance;
	}

	public static final synchronized Utente setInstance(int id, String nome, String cognome, String indirizzo, String password, Date data_nascita, String email,
			String titolo_studio, String professione, String statodomanda, int ID_ruolo) {
		if ( instance == null ) {
			instance = new Utente(id, nome, cognome, indirizzo, password, data_nascita, email, titolo_studio, professione, statodomanda, ID_ruolo);
		}
		return instance;
	}
	
	public static final Utente getIstance() {
		return instance;
	}
	
}
