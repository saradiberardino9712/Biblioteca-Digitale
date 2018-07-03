package Business.Model;

import java.util.ArrayList;

import DAO.RuoloDAO;

public class Ruolo {
	private Integer ID;
	private String nome_ruolo;

	public Ruolo(int ID,String nome_ruolo) {
		this.ID=ID;
		this.nome_ruolo = nome_ruolo;
	}

	public void setNomeRuolo(String newnomeruolo) {
		this.nome_ruolo = newnomeruolo;
	}

	public String getNomeRuolo() {
		return nome_ruolo;
	}
	
	public Integer getID() {
		return ID;
	}
	
	public static Ruolo cercaruolodb(Utente utente) {
		ArrayList<Object> lista= new ArrayList<>();
		lista.add(utente);
		lista.add(null);
		Ruolo ruolo= (Ruolo) new RuoloDAO().retrieve(lista);
		return ruolo;
	}
	
	public static Ruolo prendiiddb(String ruolo) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(null);
		lista.add(ruolo);
		Ruolo newruolo=(Ruolo) new RuoloDAO().retrieve(lista);
		return newruolo;
	}
}
