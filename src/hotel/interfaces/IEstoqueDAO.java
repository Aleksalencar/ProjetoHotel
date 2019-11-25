package hotel.interfaces;

import java.util.List;

import hotel.entidades.Produto;

public interface IEstoqueDAO {
	void adicionar(Produto prod);
	List<Produto> buscarProduto(String cod);
	void apagar(String codigo);
	void atualizar(Produto prod, String codigo);
}
