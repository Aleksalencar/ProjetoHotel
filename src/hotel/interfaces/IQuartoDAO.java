package hotel.interfaces;

import java.util.List;

import hotel.entidades.Quarto;

public interface IQuartoDAO {
	void adicionar(Quarto quarto);
	List<Quarto> buscarQuarto(String numero);
	void apagar(String numero);
	void atualizar(Quarto Quarto, String numero);
}
