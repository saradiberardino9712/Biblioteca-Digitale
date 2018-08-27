package Business.Model;

import java.sql.Timestamp;
import java.util.ArrayList;

import DAO.NotificaDAO;

public class Notifica {
	private Timestamp orario;
	private String descrizione;
	private int idutente;
	
	public Notifica(Timestamp orario,String descrizione) {
		this.orario=orario;
		this.descrizione=descrizione;
	}
	
	public Timestamp getorario() {
		return this.orario;
	}
	
	public String getdescrizione() {
		return this.descrizione;
	}
	
	public int getidutente() {
		return this.idutente;
	}
	
	public static boolean creanotifica(String descrizione,int idutentenot,int idutente) {
		Timestamp orario=new Timestamp(System.currentTimeMillis());
		ArrayList<Object> lista= new ArrayList<>();
		lista.add(orario);
		lista.add(descrizione);
		lista.add(idutentenot);
		lista.add(idutente);
		boolean crea= new NotificaDAO().insert(lista);
		return crea;
	}
	
	public static ArrayList<Notifica> prendinotifiche(int idutentenot) {
		ArrayList<Notifica> elenconotifiche= new NotificaDAO().retrieve(idutentenot);
		return elenconotifiche;
	}
}
