package hotel.entidades;

public class Produto {
	 private String codigo;
	 private String nome;
	 private String descricao;
	 private double valor;
	 private int qtd;
	 
	 public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) throws Exception {
		if (!codigo.isEmpty()) {
			this.codigo = codigo;
		}else {
			throw new Exception("Codigo vazio");
		}
				
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) throws Exception {
		if (valor >= 0) {
			this.valor = valor;
		} else {
			throw new Exception("Valor invalidade");
		}
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) throws Exception {
		if (qtd >= 0) {
			this.qtd = qtd;
		} else {
			throw new Exception("Quantidade invalida");
		}			
	}
	 
}
