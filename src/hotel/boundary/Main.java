package hotel.boundary;

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
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class Main extends Application implements EventHandler<Event>, Executor{

	private VBox box = new VBox();
	private Label titulo = new Label("Menu principal");
<<<<<<< Updated upstream
	private Button btnHosp = new Button("Hospedagens");
	private Button btnQuarto = new Button("Quartos");
	private Button btnCliente = new Button("Clientes");
	private Button btnFunc = new Button("Funcionarios");
	private Button btnPromo = new Button("Promo��es");
	private Button btnEstoque = new Button("Estoque");
	//private Button btnMenu = new Button("Menu principal");
	private Map<String, BoundaryContent> telas = new HashMap<>();
=======
	private String btnHosp = "Hospedagens";
	private String btnQuarto = "Quartos";
	private String btnCliente = "Clientes";
	private String btnFunc = "Funcionarios";
	private String btnPromo = "Promo��es";
	private String btnEstoque = "Estoque";

>>>>>>> Stashed changes

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws FileNotFoundException {
		box.setPrefSize(700.0, 650.0);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(20); // 2
		box.addEventHandler(ActionEvent.ANY, this);
		titulo.setTextAlignment(TextAlignment.CENTER);
		titulo.setFont(new Font(27));
		box.getChildren().add(titulo);
	
		box.setStyle("-fx-background-color: linear-gradient( from 0% 0% to 100% 100%, white 0%, silver 100%);");
		gerartelas();
		Scene scn = new Scene(box);
		stage.setScene(scn);
		stage.show();
		executar("Menu principal");

	}
	public void gerartelas() throws FileNotFoundException {

<<<<<<< Updated upstream
		// gerar as telas 
	    telas.put("Menu principal", new MenuPrincipalBoundary(this));
		telas.put(btnFunc.getText(), new FuncionarioBoundary(this));
		telas.put(btnCliente.getText(), new ClienteBoundary(this));
		telas.put(btnPromo.getText(), new PromocoesBoundary(this));
		telas.put(btnEstoque.getText(), new EstoqueBoundary(this));
	}


=======
	
>>>>>>> Stashed changes
	@Override
	public void handle(Event event) {
		if (event.getTarget() instanceof Button) {
			Button n = (Button)event.getTarget();
			String comando = n.getText();
			executar(comando);
		}

	}

<<<<<<< Updated upstream
	@Override
	public void executar(String cmd) {
		System.out.println("Executando comando " + cmd);
		BoundaryContent tela = telas.get(cmd);
		if (tela != null) { 
			box.getChildren().clear();
			box.getChildren().add(tela.gerarTela());
		}
=======

	}

	@Override
	public void executar(String cmd) {
	
>>>>>>> Stashed changes
	}



}
