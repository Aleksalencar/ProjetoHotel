package hotel.control;



import java.util.List;

import hotel.dao.EstoqueDAO;
import hotel.entidades.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class EstoqueController {
	private ObservableList<Produto> lista = FXCollections.observableArrayList();
	private EstoqueDAO pDao = new EstoqueDAO();
	
	public void adicionar(Produto prod) {	
		pDao.adicionar(prod);
		atualizarTabela();
	}

	public void buscarProduto(String cod) {
		List<Produto> resultado = pDao.buscarProduto(cod);
		lista.clear();
		lista.addAll(resultado);
	}
	

	public ObservableList<Produto> getLista() {
		return lista;
	}

	public void atualizarTabela(){
		lista.clear();
		List<Produto> resultado = pDao.atualizarTabelas();
		lista.addAll(resultado);
	}
	public void apagar(String codigo) {
		pDao.apagar(codigo);
	}

	public void editar(Produto prodselect, String codigo) {
		pDao.atualizar(prodselect,codigo);
	}
	


}
