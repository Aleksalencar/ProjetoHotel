package hotel.interfaces;

import java.util.List;

import hotel.entidades.Cliente;

public interface IClienteDAO {
	void adicionar(Cliente cliente);
	List<Cliente> buscarCliente(String codigo);
	void apagar(String codigo);
	void atualizar(Cliente cliente, String codigo);
}
