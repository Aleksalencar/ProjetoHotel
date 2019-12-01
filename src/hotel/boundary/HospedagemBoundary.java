package hotel.boundary;

import hotel.control.HospedagemControl;
import hotel.entidades.Cliente;
import hotel.entidades.Produto;
import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HospedagemBoundary implements BoundaryContent, EventHandler<ActionEvent> {

	private Executor executor;
	private HospedagemControl control = new HospedagemControl();

	private GridPane painelCampos = new GridPane();
	private VBox painelCentral = new VBox();
	private BorderPane painelPrincipal = new BorderPane();
	private TableView<Cliente> table;

	Label labtitulo = new Label("Hospedagens");
	private TextField txtNomeCli = new TextField();

	public HospedagemBoundary(Executor e) {
		this.setExecutor(e);
		painelCampos.setHgap(20);

		labtitulo = titulo();
		cliente(0);

		painelCentral.getChildren().add(painelCampos);
		setPainelPrincipal(labtitulo, painelCampos);

	}

	@SuppressWarnings("static-access")
	private void setPainelPrincipal(Node top, Node central) {
		painelPrincipal.setTop(top);
		painelPrincipal.setCenter(central);
		painelPrincipal.setMargin(central, new Insets(15));
		painelPrincipal.setAlignment(top, Pos.CENTER);

	}

	private Label titulo() {
		labtitulo.setOpaqueInsets(new Insets(15));
		labtitulo.setUnderline(true);
		labtitulo.setFont(Font.font("Arial", FontWeight.BLACK, 25));
		return labtitulo;
	}

	private void cliente(int row) {
		HBox cliente = new HBox();

		Button pesquisaCli = new Button("pesquisar");
		pesquisaCli.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ClienteBoundary cliente = new ClienteBoundary();
				table = cliente.generateTable();
				painelPrincipal.setBottom(table);
			}
		});
		cliente.getChildren().addAll(txtNomeCli, pesquisaCli);

		painelCampos.add(new Label("Cliente"), 0, row);
		painelCampos.add(cliente, 1, row);
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
