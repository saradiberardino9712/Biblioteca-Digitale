package Business.Model;

public class Immagine {
	private int numero_pagina;
	private int ID_utente;
	
	
	public Immagine(int numero_pagina, int ID_utente) {
		this.numero_pagina=numero_pagina;
		this.ID_utente=ID_utente;
		
	}
	
	public void setNumeropagina(int newnumeropagina) {
		this.numero_pagina=newnumeropagina;
	}
	
	public int getNumeropagina() {
		return numero_pagina;
	}
	
	public void setIdutente(int newIDutente) {
		this.ID_utente=newIDutente;
	}
	
	public int getIdutente () {
		return ID_utente;
	}
	
}