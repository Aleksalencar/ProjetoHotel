package hotel.control;

import java.util.HashSet;
import java.util.Set;

import hotel.entidades.Produto;

public class EstoqueController {
	Set<Produto> lista = new HashSet<Produto>();

	public void AddProduto(Produto prod) {
		lista.add(prod);
	}

	public Produto buscarProduto(String cod) {
		for (Produto prod : lista) {
			if (cod.equals(prod.getCodigo())) {
				return prod;
			}
		}
		return null;
	}
	
	public void SetQtd(Produto p,int q) {
		p.setQtd(q);
	}

}
