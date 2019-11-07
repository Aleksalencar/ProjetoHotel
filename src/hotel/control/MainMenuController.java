package hotel.control;

import hotel.boundary.ClienteBoundary;
import hotel.boundary.EstoqueBoundary;
import hotel.boundary.FuncionarioBoundary;
import hotel.boundary.PromocoesBoundary;
import javafx.stage.Stage;

public class MainMenuController {

	public void controlTelas(int i) {
		Stage stage = new Stage();
		switch (i) {
		case 3:
			ClienteBoundary tela = new ClienteBoundary();
			try {
				tela.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 4:
			FuncionarioBoundary tela1 = new FuncionarioBoundary();
			try {
				tela1.start(stage);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case 5:
			try {
				PromocoesBoundary tela2 = new PromocoesBoundary();
				tela2.start(stage);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			break;
		case 6:
			try {
				EstoqueBoundary tela4 = new EstoqueBoundary();
				tela4.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("valor incorreto");
			break;
		}
	}
}
