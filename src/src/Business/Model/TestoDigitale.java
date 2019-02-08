package Business.Model;

import java.util.ArrayList;
import DAO.TestoDigitaleDAO;

public class TestoDigitale {
	private int ID_utente;
	private int ID_immagine;
	private String text;
	private String titoloopera;
	private int num_pag;
	
	public TestoDigitale(int ID_utente, int ID_immagine, String text,String titoloopera, int num_pag) {
		this.ID_utente=ID_utente;
		this.ID_immagine=ID_immagine;
		this.text=text;
		this.titoloopera=titoloopera;
		this.num_pag=num_pag;
	}
	
	public String getTitoloOpera() {
		return titoloopera;
	}
	
	public int getNumpag() {
		return num_pag;
	}
	
	public void setIdutente(int newidutente) {
		this.ID_utente=newidutente;
	}
	
	public int getIdutente() {
		return ID_utente;
	}
	
	public void setIdimmagine(int newidimmagine) {
		this.ID_immagine=newidimmagine;
	}
	
	public int getIdimmagine () {
		return ID_immagine;
	}
	
	
	public void setText(String newtext) {
		this.text=newtext;
	}
	
	public String getText() {
		return text;
	}
	
	public static boolean caricaTesto(String testo, int idutente, int idimmagine) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(testo);
		lista.add("in trascrizione");
		lista.add(idutente);
		lista.add(idimmagine);
		boolean newtesto= new TestoDigitaleDAO().insert(lista);
		return newtesto;
	}
	
	public static boolean AggiornaTesto(String testo, int idutente, int idimmagine) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(testo);
		lista.add("in revisione");
		lista.add(idutente);
		lista.add(idimmagine);
		boolean newtesto= new TestoDigitaleDAO().insert(lista);
		return newtesto;
	}

	public static ArrayList<TestoDigitale> verifica(String stato) {
		ArrayList<Object> lista= new ArrayList<>();
		lista.add(stato);
		ArrayList<TestoDigitale> testo= new TestoDigitaleDAO().retrieve(lista);
		return testo;
	}

	public static boolean updatestato(String stato, int idimg) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(stato);
		lista.add(idimg);
		boolean update= new TestoDigitaleDAO().updatestato(lista);
		return update;
	}

	public static boolean remove(String testo, int idimg) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(testo);
		lista.add(idimg);
		boolean remove= new TestoDigitaleDAO().remove(lista);
		return remove;
	}
}
