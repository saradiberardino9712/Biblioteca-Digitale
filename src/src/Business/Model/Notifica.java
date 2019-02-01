package Business.Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import DAO.NotificaDAO;

public class Notifica {
	private Timestamp orario;
	private String descrizione;
	private int idutente;
	private int id;
	
	public Notifica(int id,Timestamp orario,String descrizione, int idutente) {
		this.id=id;
		this.orario=orario;
		this.descrizione=descrizione;
		this.idutente=idutente;
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
	
	public static boolean creanotifica(String descrizione,int idutentenot,int idruolonot,int idutente) {
		Timestamp orario=new Timestamp(System.currentTimeMillis());
		ArrayList<Object> lista= new ArrayList<>();
		lista.add(orario);
		lista.add(descrizione);
		lista.add(idutentenot);
		lista.add(idruolonot);
		lista.add(idutente);
		boolean crea= new NotificaDAO().insert(lista);
		return crea;
	}
	
	public static ArrayList<Notifica> prendinotifiche(int idutentenot,int idruolonot,String tipo) {
		ArrayList<Object> lista= new ArrayList<>();
		lista.add(idutentenot);
		lista.add(idruolonot);
		lista.add(tipo);
		ArrayList<Notifica> elenconotifiche= new NotificaDAO().retrieve(lista);
		return elenconotifiche;
	}
	
	public static boolean updatevistadb(String descrizione,String orario) {
		ArrayList<Object> lista= new ArrayList<>();
		lista.add(descrizione);
		lista.add(orario);
		boolean update=new NotificaDAO().update(lista);
		return update;
	}
}
