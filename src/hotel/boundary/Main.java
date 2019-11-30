package hotel.boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<Event>, Executor {

	private TilePane window = new TilePane();
	private Pane pane = new Pane();
	private Label titulo = new Label("Menu principal");
	private String btnHosp = "Hospedagens";
	private String btnQuarto = "Quartos";
	private String btnCliente = "Clientes";
	private String btnFunc = "Funcionarios";
	private String btnPromo = "Promocoes";
	private String btnEstoque = "Estoque";
	private Map<String, BoundaryContent> telas = new HashMap<>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws FileNotFoundException {
		window.setPrefSize(850.0, 690.0);
		window.setAlignment(Pos.CENTER);
		window.addEventHandler(ActionEvent.ANY, this);

		comandoTelas();

		definirBackgroundCampos();

		window.getChildren().add(pane);
		Scene scn = new Scene(window);
		stage.setTitle("Controle de hotel");
		stage.setScene(scn);
		stage.show();
		executar("Menu principal");

	}

	private void definirBackgroundCampos() {
		pane.setStyle(
				"-fx-background-color:rgba(241, 238, 238, 0.6);-fx-background-radius: 10;-fx-background-insets: -10px;");

	}

	public void comandoTelas() throws FileNotFoundException {
		// gerar as telas
		telas.put("Menu principal", new MenuPrincipalBoundary(this));
		telas.put(btnHosp, new HospedagemBoundary(this));
		telas.put(btnFunc, new FuncionarioBoundary(this));
		telas.put(btnCliente, new ClienteBoundary(this));
		telas.put(btnPromo, new PromocoesBoundary(this));
		telas.put(btnEstoque, new EstoqueBoundary(this));
		telas.put("Email Enviar", new emailBoundary(this));
		telas.put(btnQuarto, new quartoBoundary(this));
	}

	@Override
	public void handle(Event event) {
		if (event.getTarget() instanceof Button) {
			Button n = (Button) event.getTarget();
			String comando = n.getText();
			executar(comando);
		}

	}

	public void definirBackground(String imageName) throws FileNotFoundException {
		String path = "src/hotel/images/" + imageName + ".jpg";
		System.out.println(path);
		FileInputStream imagem = new FileInputStream(path);
		Image image = new Image(imagem);
		BackgroundSize size = new BackgroundSize(pane.getWidth(), pane.getHeight(), true, true, true, true);
		BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
		Background background = new Background(backgroundimage);
		window.setBackground(background);
	}

	@Override
	public void executar(String cmd) {
		System.out.println("Executando comando " + cmd);
		BoundaryContent tela = telas.get(cmd);
		if (tela != null) {
			pane.getChildren().clear();
			try {
				definirBackground(cmd);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			pane.getChildren().add(tela.gerarTela());
		}
	}
}
