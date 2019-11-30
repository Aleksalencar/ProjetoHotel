package hotel.boundary;

import hotel.control.HospedagemControl;
import hotel.entidades.Cliente;
import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HospedagemBoundary implements BoundaryContent, EventHandler<ActionEvent>{
	private Executor executor;
	private HospedagemControl control = new HospedagemControl(); 
	private TextField txtNome = new TextField();	
	
	private BorderPane painelPrincipal = new BorderPane();
	private GridPane painelCampos = new GridPane();
	private VBox painelCentral = new VBox();
	
	private TableView<Object> table;
	
	
	
	
	

	@SuppressWarnings("static-access")
	public HospedagemBoundary(Executor e) {
		this.setExecutor(e);
		
		Label labtitulo = new Label("Controle de Hospedagens");
		labtitulo.setOpaqueInsets( new Insets(15));
		labtitulo.setUnderline(true);	
		labtitulo.setFont(Font.font("Arial", FontWeight.BLACK, 25));
		
		painelCampos.add(new Label("Cliente"), 0, 0);
		txtNome.setDisable(true);
		painelCampos.add(txtNome, 1, 0);
		
		painelCentral.getChildren().add(painelCampos);
		
		painelPrincipal.setTop(labtitulo);
		painelPrincipal.setCenter(painelCentral);
		painelPrincipal.setBottom(table);
		painelPrincipal.getChildren().add(painelCampos);
		
//		painelPrincipal.setMargin(painelCentral, new Insets(15));
		painelPrincipal.setMargin(labtitulo, new Insets(15));
		
		
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
	public Pane gerarTela() {
		return painelPrincipal;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
