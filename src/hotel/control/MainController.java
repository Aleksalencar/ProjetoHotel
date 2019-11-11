package hotel.control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import hotel.boundary.ClienteBoundary;
import hotel.boundary.EstoqueBoundary;
import hotel.boundary.FuncionarioBoundary;
import hotel.boundary.MenuPrincipalBoundary;
import hotel.boundary.PromocoesBoundary;
import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class MainController implements Executor {
	private Map<String, BoundaryContent> telas = new HashMap<>();
	public void comandoTelas() throws FileNotFoundException {

		// gerar as telas
		telas.put("Menu principal", new MenuPrincipalBoundary(this));
		telas.put(btnFunc, new FuncionarioBoundary(this));
		telas.put(btnCliente, new ClienteBoundary(this));
		telas.put(btnPromo, new PromocoesBoundary(this));
		telas.put(btnEstoque, new EstoqueBoundary(this));
	}

	@Override
	public void executar(String cmd) {
		System.out.println("Executando comando " + cmd);
		BoundaryContent tela = telas.get(cmd);
		if (tela != null) {
			box.getChildren().clear();
			try {
				definirBackground(cmd);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			box.getChildren().add(tela.gerarTela());
		}		
	}
	public void definirBackground(String imageName) throws FileNotFoundException {
		String path = "src/hotel/images/" + imageName + ".jpg";
		System.out.println(path);
		FileInputStream imagem = new FileInputStream(path);
		System.out.println("path:" + imagem);
		Image image = new Image(imagem);
		BackgroundSize size = new BackgroundSize(box.getWidth(), box.getHeight(), true, true, true, true);
		BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
		Background background = new Background(backgroundimage);
		pane.setBackground(background);

}
