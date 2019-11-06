package hotel.control;

import java.util.Set;

import hotel.entidades.Cliente;
import javafx.collections.FXCollections;

public class ClienteControl {
	private Set<Cliente> setCliente = FXCollections.observableSet();
	
	public void cadastrarCliente(Cliente q) { 
		setCliente.add(q);
	}
	
	public Cliente buscarCliente(String CPF){
		for (Cliente c : setCliente) {
			if(c.getCPF().equals(CPF)){
				return c;
			}
		}
		return null;
	}
	
	public Set<Cliente> lerCliente(){
		return setCliente;
	}
	
	
}
