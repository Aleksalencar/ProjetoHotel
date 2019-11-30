package hotel.control;

import java.util.List;
import java.util.Set;

import hotel.dao.ClienteDAO;
import hotel.dao.QuartoDAO;
import hotel.entidades.Cliente;
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
	private QuartoDAO cDAO = new QuartoDAO();

	
	public ObservableList<Quarto> getLista() {
		return lista;
	}

	public void adicionar(Quarto quarto) {
		cDAO.adicionar(quarto);
		atualizarTabela();
	}
	
	public void apagar(String numero) {
		cDAO.apagar(numero);
	}
	
	public void alterarQuarto(Quarto quartoselect, String numero) {
		cDAO.atualizar(quartoselect,numero);
	}

	public void atualizarTabela() {
		lista.clear();
		List<Quarto> resultado = cDAO.atualizarTabelas();
		lista.addAll(resultado);
	}
	
	public void buscaQuarto(String numero) {
		List<Quarto> resultado = cDAO.buscarQuarto(numero);
		lista.clear();
		lista.addAll(resultado);
	}

}
