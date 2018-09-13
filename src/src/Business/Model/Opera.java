package Business.Model;

import java.util.ArrayList;
import DAO.OperaDAO;

public class Opera {
	private int ID_categoria;
	private String titolo;
	private int anno;
	private String autore;

	public Opera(int ID_categoria, String titolo, int anno, String autore) {
		this.ID_categoria = ID_categoria;
		this.titolo = titolo;
		this.anno = anno;
		this.autore = autore;
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

	public void setAnno(int newanno) {
		this.anno = newanno;
	}

	public int getAnno() {
		return anno;
	}

	public void setAutore(String newautore) {
		this.autore = newautore;
	}

	public String getAutore() {
		return autore;
	}

	public static boolean inseriscioperadb(String titolo,int anno,String autore,int npagine,int ID_categoria) {
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(titolo);
		lista.add(anno);
		lista.add(autore);
		lista.add(npagine);
		if(ID_categoria==0)
			lista.add(null);
		else
			lista.add(ID_categoria);
		boolean inserimentodb = new OperaDAO().insert(lista);
		return inserimentodb;
	}
}
