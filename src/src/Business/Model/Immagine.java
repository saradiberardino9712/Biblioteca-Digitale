package Business.Model;
import java.util.ArrayList;
import DAO.ImmagineDAO;

public class Immagine {
	private int numero_pagina;
	private String url;
	private Opera opera;
	
	
	public Immagine(int numero_pagina, String url, String titolo) {
		this.numero_pagina=numero_pagina;
		this.url=url;
		this.opera=new Opera(0,0,titolo,null,null,0);
	}
	
	public Immagine(String titolo, int numero_pagina,int pagtot) {
		this.numero_pagina=numero_pagina;
		this.opera=new Opera(0,0,titolo,null,null,pagtot);
	}
	
	public void setNumeropagina(int newnumeropagina) {
		this.numero_pagina=newnumeropagina;
	}
	
	public int getNumeropagina() {
		return numero_pagina;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getTitoloOpera() {
		return opera.getTitolo();
	}
	
	public int getPagineOpera() {
		return opera.getPagine();
	}
	
	public static boolean caricaimmagine(String url, int n, String stato, int idopera, int idutente) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(n);
		lista.add(stato);
		lista.add(url);
		lista.add(idutente);
		lista.add(idopera);
		boolean newimmagine= new ImmagineDAO().insert(lista);
		return newimmagine;
	}
	
	public static ArrayList<Immagine> prendiimg(int idutente) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(idutente);
		ArrayList<Immagine> listaimg= new ImmagineDAO().retrieve(lista);
		return listaimg;
	}
	
	public static ArrayList<Immagine> immaginiacquisite(){
		ArrayList<Object> lista=new ArrayList<>();
		ArrayList<Immagine> listaimg= new ImmagineDAO().retrieveacquisite(lista);
		return listaimg;
	}
	
	public static boolean updatestato(String stato, int npag, String titolo) {
		ArrayList<Object> lista=new ArrayList<>();
		lista.add(stato);
		lista.add(titolo);
		lista.add(npag);
		boolean update= new ImmagineDAO().updatestato(lista);
		return update;
	}
	
	@SuppressWarnings("unused")
	public static void remove(ArrayList<Immagine> caricamento) {
		ArrayList<Object> lista= new ArrayList<>();
		for(Immagine i: caricamento) {
			lista.add(i);
		}
		boolean remove= new ImmagineDAO().delete(lista);
	}
}