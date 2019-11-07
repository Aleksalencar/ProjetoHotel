package hotel.control;

import hotel.boundary.ClienteBoundary;
import hotel.boundary.EstoqueBoundary;
import hotel.boundary.PromocoesBoundary;
import javafx.stage.Stage;

public class MainMenuController {

	public void controlTelas(int i) {
		switch (i) {
		case 3:
			ClienteBoundary tela = new ClienteBoundary();
			try {
				tela.start(new Stage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 5:
			try {
				PromocoesBoundary tela1 = new PromocoesBoundary();
				tela1.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 6:
			try {
				EstoqueBoundary tela2 = new EstoqueBoundary();
			//	tela.start(new Stage());
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
