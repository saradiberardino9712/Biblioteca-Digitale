package Business.Model;

public class TestoDigitale {
	private int ID_utente;
	private int ID_immagine;
	private String text;
	
	public TestoDigitale(int ID_utente, int ID_immagine, String text) {
		this.ID_utente=ID_utente;
		this.ID_immagine=ID_immagine;
		this.text=text;
		
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
}
