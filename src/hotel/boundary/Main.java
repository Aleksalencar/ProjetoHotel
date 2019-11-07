package hotel.boundary;

import java.io.IOException;
import java.util.LinkedList;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import hotel.control.MainMenuController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Main extends Application implements EventHandler<Event> {

	private MainMenuController control = new MainMenuController();

	private VBox box = new VBox();
	private Label titulo = new Label("Menu principal");
	private Button btnHosp = new Button("Hospedagens");
	private Button btnQuarto = new Button("Quartos");
	private Button btnCliente = new Button("Clientes");
	private Button btnFunc = new Button("Funcionarios");
	private Button btnPromo = new Button("Promoções");
	private Button btnEstoque = new Button("Estoque");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		box.setPrefSize(700.0, 550.0);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(20); // 2
		box.addEventHandler(ActionEvent.ANY, this);

		titulo.setTextAlignment(TextAlignment.CENTER);
		titulo.setFont(new Font(27));
		box.getChildren().add(titulo);

		DefBtn(btnHosp);
		btnHosp.addEventHandler(ActionEvent.ANY, this);
		DefBtn(btnQuarto);
		btnQuarto.addEventHandler(ActionEvent.ANY, this);
		DefBtn(btnCliente);
		btnCliente.addEventHandler(ActionEvent.ANY, this);
		DefBtn(btnFunc);
		btnFunc.addEventHandler(ActionEvent.ANY, this);
		DefBtn(btnPromo);
		btnPromo.addEventHandler(ActionEvent.ANY, this);
		DefBtn(btnEstoque);
		btnEstoque.addEventHandler(ActionEvent.ANY, this);

		Scene scn = new Scene(box);
		stage.setScene(scn);
		stage.show();

	}

	private void DefBtn(Button btn) {
		btn.setPrefSize(178, 47);
		btn.setTextAlignment(TextAlignment.CENTER);
		box.getChildren().add(btn);
	}

	@Override
	public void handle(Event event) {
		if (event.getTarget().equals(btnCliente)) {
			control.controlTelas(3);
		} else if (event.getTarget().equals(btnPromo)) {
			control.controlTelas(5);
		} else if (event.getTarget().equals(btnEstoque)) {
			control.controlTelas(6);
		}

	}

}
