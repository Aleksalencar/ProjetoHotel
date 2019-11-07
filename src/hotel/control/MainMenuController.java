package hotel.control;

import hotel.boundary.EstoqueBoundary;
import hotel.boundary.PromocoesBoundary;
import javafx.stage.Stage;

public class MainMenuController {

	public void controlTelas(int i) {
		switch (i) {
		case 3:
			/*
			 * try { PromocoesBoundary tela = new PromocoesBoundary();
			 * tela.start(new Stage()); } catch (Exception e) {
			 * e.printStackTrace(); }
			 */
			break;
		case 5:
			try {
				PromocoesBoundary tela = new PromocoesBoundary();
				tela.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 6:
			try {
				EstoqueBoundary tela = new EstoqueBoundary();
				tela.start(new Stage());
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
