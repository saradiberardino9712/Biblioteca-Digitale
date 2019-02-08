package Business.Model;

import java.util.ArrayList;
import DAO.OperaDAO;

public class Opera {
	private int id;
	private int ID_categoria;
	private String titolo;
	private String anno;
	private String autore;
	private int pagine;

	public Opera(int id, int ID_categoria, String titolo, String anno, String autore,int pagine) {
		this.id=id;
		this.ID_categoria = ID_categoria;
		this.titolo = titolo;
		this.anno = anno;
		this.autore = autore;
		this.pagine=pagine;
	}
	
	public int getID() {
		return id;
	}
	
	public void setIdcategoria(int newidcategoria) {
		this.ID_categoria = newidcategoria;
	}

	public int getIdcategoria() {
		return ID_categoria;
	}

	public void setTitolo(String newtitolo) {
		this.titolo = newtitolo;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setAnno(String newanno) {
		this.anno = newanno;
	}

	public String getAnno() {
		return anno;
	}

	public void setAutore(String newautore) {
		this.autore = newautore;
	}

	public String getAutore() {
		return autore;
	}
	
	public int getPagine() {
		return pagine;
	}

	public static boolean inseriscioperadb(String titolo,String anno,String autore,int npagine,int ID_categoria) {
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(titolo);
		lista.add(anno);
		lista.add(autore);
		lista.add(npagine);
		lista.add("in fase di acquisizione");
		if(ID_categoria==0)
			lista.add(null);
		else
			lista.add(ID_categoria);
		boolean inserimentodb = new OperaDAO().insert(lista);
		return inserimentodb;
	}
	
	public static ArrayList<Opera> prendiopere(String stato){
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(stato);
		ArrayList<Opera> listaopere=(ArrayList<Opera>) new OperaDAO().retrieve(lista);
		return listaopere;
	}
	
	public static boolean updatestato(String stato,String titolo) {
		ArrayList<Object> lista= new ArrayList<>();
		lista.add(stato);
		lista.add(titolo);
		boolean update= new OperaDAO().updatestato(lista);
		return update;
	}

	public static boolean remove(int id) {
		boolean remove=new OperaDAO().remove(id);
		return remove;
	}
}
