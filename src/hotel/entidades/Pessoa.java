package hotel.entidades;

public abstract class Pessoa {
	protected String nome;
	protected String email;
	protected String telefone;
	protected String endereco;
	protected String CPF;
	protected int numero;
	protected char sexo;
	protected String getNome() {
		return nome;
	}
	protected void setNome(String nome) {
		this.nome = nome;
	}
	protected String getEmail() {
		return email;
	}
	protected void setEmail(String email) {
		this.email = email;
	}
	protected String getTelefone() {
		return telefone;
	}
	protected void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	protected String getEndereco() {
		return endereco;
	}
	protected void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	protected String getCPF() {
		return CPF;
	}
	protected void setCPF(String cPF) {
		CPF = cPF;
	}
	protected int getNumero() {
		return numero;
	}
	protected void setNumero(int numero) {
		this.numero = numero;
	}
	protected char getSexo() {
		return sexo;
	}
	protected void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
}
