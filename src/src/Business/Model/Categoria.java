package Business.Model;

public class Categoria {
	private String nome;

	public Categoria(String nome) {
		this.nome = nome;
	}

	public void setNome(String newnome) {
		this.nome = newnome;
	}

	public String getNome() {
		return nome;
	}
}
