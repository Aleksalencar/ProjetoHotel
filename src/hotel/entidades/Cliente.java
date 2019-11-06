package hotel.entidades;

public class Cliente {
	private String nome;
	private String email;
	private String telefone;
	private String endereco;
	private String CPF;
	private int numero;
	private char sexo;
	private int qtdCliente;
	
	public Cliente(String nome, String email, String telefone, String endereco, String cPF, int numero, char sexo,
			int qtdCliente) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
		this.CPF = CPF;
		this.numero = numero;
		this.sexo = sexo;
		this.qtdCliente = qtdCliente;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public int getQtdCliente() {
		return qtdCliente;
	}
	public void setQtdCliente(int qtdCliente) {
		this.qtdCliente = qtdCliente;
	}
	
}
