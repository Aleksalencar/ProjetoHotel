package hotel.entidades;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class Quarto{
	private String andar;
	private String numero;
	private String checkBox;
	private String tipo ;
	
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getAndar() {
		return andar;
	}
	public void setAndar(String andar) {
		this.andar = andar;
	}
	public String getAlugado() {
		return checkBox;
	}
	public void setAlugado(String alugado) {
		this.checkBox = alugado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
