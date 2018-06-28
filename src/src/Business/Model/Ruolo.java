package Business.Model;

public class Ruolo {
	private String nome_ruolo;
	
	public Ruolo(String nome_ruolo) {
		this.nome_ruolo=nome_ruolo;
	}
	
	public void setNomeRuolo(String newnomeruolo) {
		this.nome_ruolo=newnomeruolo;
	}
	
	public String getNome () {
		return nome_ruolo;
	}
}
