package hotel.boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class MenuPrincipalBoundary implements BoundaryContent, EventHandler<ActionEvent>{
	private Label titulo = new Label("Menu principal");
	private Button btnHosp = new Button("Hospedagens");
	private Button btnQuarto = new Button("Quartos");
	private Button btnCliente = new Button("Clientes");
	private Button btnFunc = new Button("Funcionarios");
	private Button btnPromo = new Button("Promoções");
	private Button btnEstoque = new Button("Estoque");
	private VBox box = new VBox();
	private Executor executor;

	public MenuPrincipalBoundary(Executor e) throws FileNotFoundException {
		this.setExecutor(e);
		
		box.setAlignment(Pos.CENTER);
		box.setSpacing(20); // 2
		box.addEventHandler(ActionEvent.ANY, this);

		titulo.setTextAlignment(TextAlignment.CENTER);
		titulo.setFont(Font.font("Arial", FontWeight.BLACK, 30));
		titulo.setTextFill(Color.CHARTREUSE);
		titulo.setUnderline(true);
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
		
		
	}
	

	
	@Override
	public Pane gerarTela() {
		// TODO Auto-generated method stub
		return box;
	}

	private void DefBtn(Button btn) {
		btn.setPrefSize(178, 47);
		btn.setTextAlignment(TextAlignment.CENTER);
		box.getChildren().add(btn);
	}
	
	@Override
	public void setExecutor(Executor e) {
		this.executor = e;
	}

	@Override
	public Executor getExecutor() {
		return this.executor;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
