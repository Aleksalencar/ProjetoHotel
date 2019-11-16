package hotel.boundary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;

public class emailBoundary implements BoundaryContent, EventHandler<ActionEvent>  {
	private Button btnMenu = new Button("Voltar");
	private HBox raiz = new HBox(20);
	private Executor executor;
	final String[] sites = {"javafx.com", "java.com",
			"google.com" };

	public emailBoundary(Executor e) {
		this.setExecutor(e);
		
		Label labtitulo = new Label("Navegador hotel");
		labtitulo.setUnderline(true);
		labtitulo.setFont(Font.font("Arial", FontWeight.BLACK, 25));
		
		ListView<String> listaSites = new ListView<>();
		WebView webView = new WebView();
		Stream.of(sites).forEach(listaSites.getItems()::add);
	
		listaSites.getSelectionModel().selectedItemProperty().addListener(
				(obs, o, n) -> {
					if(n != null) webView.getEngine().load("http://" + n);
		});
		
		listaSites.disableProperty().bind(webView.getEngine().getLoadWorker().runningProperty());
	
		raiz.getChildren().addAll(listaSites, webView,btnMenu);
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				executor.executar("Promocoes");
			}
		});
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setExecutor(Executor e) {
		// TODO Auto-generated method stub
		this.executor = e;
	}

	@Override
	public Executor getExecutor() {
		// TODO Auto-generated method stub
		return this.executor;
	}

	@Override
	public Pane gerarTela() {
		// TODO Auto-generated method stub
		return raiz;
	}


}