package view;
	
import java.io.IOException;
import java.util.LinkedList;

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


public class Main extends Application implements EventHandler<Event>{
	
	VBox box = new VBox();
	Label titulo = new Label("Menu principal");
	LinkedList<String> btnsName = new LinkedList<String>();

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		
		
		
		box.setPrefSize(700.0,550.0);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(20); // 2
		box.addEventHandler(ActionEvent.ANY, this);
		//box.setTranslateX(10);
		//box.setTranslateX(20);
		
		
		//Label Titulo
		titulo.setTextAlignment(TextAlignment.CENTER);
		titulo.setFont(new Font(27));
		box.getChildren().add(titulo);
		
		
		//Adicionando botões
		btnsName.add("Aluguel clientes");
		btnsName.add("Aluguel clientes");
		btnsName.add("Clientes");
		btnsName.add("Funcionario");
		
		for (String string : btnsName) {
			Button btn = new Button();
			btn.setPrefSize(178, 47);
			btn.setText(string);
			btn.setTextAlignment(TextAlignment.CENTER);
			box.getChildren().add(btn);
		}
		
		//Invocendo Scene
		Scene scn = new Scene(box);
		primaryStage.setScene(scn);
		primaryStage.show();

	}

	@Override
	public void handle(Event event) {
		/*if (event.getTarget() == box) {
			ObservableList<Node> elements = box.getChildren();
			scaleX = box.getScaleX();
			for (Node element : elements) {
				element.setScaleX();
			}
		}*/
		
	}
	
}
