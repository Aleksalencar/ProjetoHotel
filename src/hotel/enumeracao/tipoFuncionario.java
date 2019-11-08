package hotel.enumeracao;

public enum tipoFuncionario {
	GERENTE("GERENTE"), 
	ATENDETE("ATENDETE"), 
	MARKETING("MARKETING");
	
	private String descricao;
	
	tipoFuncionario(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao(){
		return this.descricao;
	}
}
