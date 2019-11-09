package hotel.control;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import hotel.entidades.Funcionario;
import hotel.entidades.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstoqueController {
	private ObservableList<Produto> lista = FXCollections.observableArrayList();
	
	
	public void adicionar(Produto prod) {
		getLista().add(prod);
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
		return lista;
	}

}
