package Business.Model;

import java.sql.Timestamp;
import java.util.ArrayList;

import DAO.NotificaDAO;

public class Notifica {
	private Timestamp orario;
	private String descrizione;
	private int idutente;
	private int id;
	
	public Notifica(int id,Timestamp orario,String descrizione) {
		this.id=id;
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
	
	public int getid() {
		return this.id;
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
	
	public static ArrayList<Notifica> prendinotifiche(int idutentenot,String tipo) {
		ArrayList<Object> lista= new ArrayList<>();
		lista.add(idutentenot);
		lista.add(tipo);
		ArrayList<Notifica> elenconotifiche= new NotificaDAO().retrieve(lista);
		return elenconotifiche;
	}
	
	public static boolean updatevistadb(ArrayList<Notifica> lista) {
		ArrayList<Object> listadata= new ArrayList<>();
		listadata.add(lista);
		boolean update=new NotificaDAO().update(listadata);
		return update;
	}
}
