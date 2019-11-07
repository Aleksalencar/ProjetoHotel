package hotel.enumeracao;

public enum tipoFuncionario {
	GERENTE, ATENDETE, MARKETING;
	public String cargoFunc;
	void cargosEnum(String cargo){
		cargoFunc = cargo;
	}
}
