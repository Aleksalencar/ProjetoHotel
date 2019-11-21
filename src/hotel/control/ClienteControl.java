package hotel.control;

import java.util.Set;

import hotel.entidades.Cliente;
import hotel.entidades.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteControl {
	private Set<Cliente> setCliente = FXCollections.observableSet();

	public void manterCliente(Cliente q) {
		setCliente.add(q);
	}

	public Cliente alterarCliente(String CPF, Cliente cli) {
		for (Cliente c : setCliente) {
			if (c.getCPF().equals(CPF)) {
				c = cli;
				return c;
			}
		}
		return null;
	}

	public Cliente buscarCliente(String CPF) {
		for (Cliente c : setCliente) {
			if (c.getCPF().equals(CPF)) {
				return c;
			}
		}
		return null;
	}

	public Set<Cliente> lerCliente() {
		return setCliente;
	}

	public ObservableList<Produto> getLista() {
		// TODO Auto-generated method stub
		return null;
	}

}
