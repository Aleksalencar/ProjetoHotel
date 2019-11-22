package hotel.control;

import java.util.Set;

import hotel.entidades.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

public class ClienteControl {
	private ObservableList<Cliente> lista = FXCollections.observableArrayList();

	public void manterCliente(Cliente q) {
		lista.add(q);
	}

	public Cliente alterarCliente(String CPF, Cliente cli) {
		for (Cliente c : lista) {
			if (c.getCPF().equals(CPF)) {
				c = cli;
				return c;
			}
		}
		return null;
	}

	public Cliente buscarCliente(String CPF) {
		for (Cliente c : lista) {
			if (c.getCPF().equals(CPF)) {
				return c;
			}
		}
		return null;
	}

	public ObservableList<Cliente> getLista() {
		// TODO Auto-generated method stub
		return lista;
	}


}
