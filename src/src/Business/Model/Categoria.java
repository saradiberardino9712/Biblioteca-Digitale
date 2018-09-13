package Business.Model;

import java.util.ArrayList;
import DAO.CategoriaDAO;



public class Categoria {
	
	private Integer ID;
	private String nome;

	public Categoria(int ID,String nome) {
		this.ID=ID;
		this.nome = nome;
	}

	public void setNome(String newnome) {
		this.nome = newnome;
	}

	public String getNome() {
		return nome;
	}
	
	public Integer getID() {
		return ID;
	}
	
	public static Categoria prendiiddb(String categoria) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(categoria);
		Categoria newcategoria=(Categoria) new CategoriaDAO().retrieve(lista);
		return newcategoria;
	}
	
	public static boolean inseriscidb(String categoria) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(categoria);
		boolean newcategoria=(boolean) new CategoriaDAO().insert(lista);
		return newcategoria;
	} 
	
	public static ArrayList<Categoria> prendicategorie() {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(null);
		ArrayList<Categoria> elencocategorie= (ArrayList<Categoria>) new CategoriaDAO().retrieveAll(lista);
		return elencocategorie;
	}
}
