package pt.upacademy.appsenhas;

public class Problema {
	private String nomeUser;
	private String problema;
	private int numSenha;
	
	/**
	 * Construtor com parametros
	 * @param nomeUser
	 * @param problema
	 * @param numSenha
	 */
	public Problema(String nomeUser, String problema, int numSenha) {
		this.nomeUser = nomeUser;
		this.problema = problema;
		this.numSenha = numSenha;
	}

	// region Getters
	public String getNomeUser() {
		return nomeUser;
	}

	public String getProblema() {
		return problema;
	}

	public int getNumSenha() {
		return numSenha;
	}
	// endregion

	// region Setters
	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public void setNumSenha(int numSenha) {
		this.numSenha = numSenha;
	}
	// endregion

	public void print() {
		System.out.println(">> Senha nº: " + this.numSenha);
		System.out.println(">> Nome: " + this.nomeUser);
		System.out.println(">> Problema: " + this.problema);
		System.out.println("===================================");		
	}
}
