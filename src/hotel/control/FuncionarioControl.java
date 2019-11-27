package hotel.control;

import java.util.Set;

import hotel.entidades.Funcionario;
import javafx.collections.FXCollections;

public class FuncionarioControl {
	private Set<Funcionario> setFuncionario = FXCollections.observableSet();
	
	public void manterFuncioario(Funcionario f) {
		setFuncionario.add(f);
	}

	public Funcionario alterarCliente(String CPF, Funcionario func) {
		for (Funcionario f : setFuncionario) {
			if (f.getCPF().equals(CPF)) {
				f = func;
				return f;
			}
		}
		return null;
	}

	public Funcionario buscarFuncionario(String CPF) {
		for (Funcionario f : setFuncionario) {
			if (f.getCPF().equals(CPF)) {
				return f;
			}
		}
		return null;
	}

	public Set<Funcionario> lerFuncionario() {
		return setFuncionario;
	}

	public void alterarCliente(Funcionario boundaryEntidade, String cpf) {
		// TODO Auto-generated method stub
		
	}
}
