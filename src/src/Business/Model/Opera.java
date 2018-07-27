package Business.Model;

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
}
