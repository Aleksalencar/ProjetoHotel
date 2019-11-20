package hotel.control;

import java.util.Set;
import hotel.entidades.Quarto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class QuartosController {
	private ObservableList<Quarto> lista = FXCollections.observableArrayList();

	
	public ObservableList<Quarto> getLista() {
		return lista;
	}

	@SuppressWarnings("unlikely-arg-type")
	public Quarto adicionar(Quarto quarto) {
		System.out.println(quarto.getNumero());
		if(lista.isEmpty()){
			for(Quarto q1 : lista ){
				if(lista.contains(q1.getNumero())){
					return q1;
				}
			}
		}	
		lista.add(quarto);
		return null;
	}
	
	public void apagar(String codigo) {
		Quarto quarto = buscaQuarto(codigo);
		lista.remove(quarto);
	}
	
	public Quarto buscaQuarto(String num) {
		return lista.stream().filter(p -> p.getNumero().equals(num)).findFirst()
				.orElseThrow(() -> new Error("Quarto nao encontrado"));
	}

}
