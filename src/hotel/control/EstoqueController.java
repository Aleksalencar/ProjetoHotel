package hotel.control;



import hotel.entidades.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class EstoqueController {
	private ObservableList<Produto> lista = FXCollections.observableArrayList();
	
	
	public ObservableList<Produto> adicionar(Produto prod) {
		System.out.println(prod.getCodigo());
		if(lista.isEmpty()){
			for(Produto p1 : lista ){
				if(lista.contains(p1.getCodigo())){
					return lista;
				}
			}
		}	
		lista.add(prod);
		return lista;
	}

	public Produto buscarProduto(String cod) {
		return lista.stream().filter(p -> p.getCodigo() == cod).findFirst()
				.orElseThrow(() -> new Error("Conta não encontrada"));
	}
	
	public void SetQtd(Produto p,int q) {
		p.setQtd(q);
	}

	public ObservableList<Produto> getLista() {
		return lista;
	}

	public void apagar(String codigo) {
		Produto produto = buscarProduto(codigo);
		lista.remove(produto.equals(produto));
	}
	


}
