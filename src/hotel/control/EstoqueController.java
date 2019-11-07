package hotel.control;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import hotel.entidades.Funcionario;
import hotel.entidades.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstoqueController {
	private Set<Produto> lista = FXCollections.observableSet();
	
	
	public void adicionar(Produto prod) {
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

	public ObservableList<Produto> getLista() {
		return (ObservableList<Produto>)lista;
	}

}
