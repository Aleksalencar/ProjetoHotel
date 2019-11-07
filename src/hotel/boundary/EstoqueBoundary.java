package hotel.boundary;

import java.text.SimpleDateFormat;

import hotel.control.EstoqueController;
import hotel.entidades.Produto;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EstoqueBoundary extends Application implements EventHandler<Event> {

	private EstoqueController control = new EstoqueController();
	private TextField txtCod = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtDesc = new TextField();
	private TextField txtValor = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private TableView<Produto> table = new TableView<>();

	public Produto boundaryParaEntity(Produto p) {
		if (p != null) {
			txtCod.setText(p.getCodigo());
			txtNome.setText(p.getNome());
			txtDesc.setText(p.getDescricao());
			txtValor.setText(String.valueOf(p.getValor()));
		}
		return p;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane painelPrincipal = new BorderPane();
		GridPane painelCampos = new GridPane();
		FlowPane painelBotoes = new FlowPane();

		painelPrincipal.setStyle("-fx-padding:15px");

		ColumnConstraints col0 = new ColumnConstraints();
		col0.setPercentWidth(30);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(70);
		painelCampos.getColumnConstraints().addAll(col0, col1);

		painelBotoes.setHgap(15);

		painelCampos.add(new Label("Código: "), 0, 0);
		painelCampos.add(txtCod, 1, 0);
		painelCampos.add(new Label("Nome:"), 0, 1);
		painelCampos.add(txtNome, 1, 1);
		painelCampos.add(new Label("Descrição: "), 0, 2);
		painelCampos.add(txtDesc, 1, 2);
		painelCampos.add(new Label("Valor: "), 0, 3);
		painelCampos.add(txtCod, 1, 3);

		painelBotoes.getChildren().addAll(btnAdicionar, btnPesquisar);
		generateTableColumns();

		painelPrincipal.setTop(painelCampos);
		painelPrincipal.setCenter(table);
		painelPrincipal.setBottom(painelBotoes);

		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);

		Scene scn = new Scene(painelPrincipal, 800, 600);
		primaryStage.setScene(scn);
		primaryStage.setTitle("Estoque");
		primaryStage.show();
	}

	private void generateTableColumns() {
		TableColumn<Produto, String> col1 = new TableColumn<>("Código");
		col1.setCellValueFactory(new PropertyValueFactory<Produto, String>("Código"));

		TableColumn<Produto, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<Produto, String>("Nome"));
		
		TableColumn<Produto, String> col3 = new TableColumn<>("Descrição");
		col3.setCellValueFactory(new PropertyValueFactory<Produto, String>("Descrição"));

		TableColumn<Produto, Double> col4 = new TableColumn<>("Valor:");
		col4.setCellValueFactory(new PropertyValueFactory<Produto, Double>("Valor"));

		table.getColumns().addAll(col1, col2, col3, col4);
		table.setItems(control.getLista());
	
	}

	@Override
	public void handle(Event event) {
		if (event.getTarget() == btnAdicionar) { 
			control.adicionar( boundaryParaEntity(new Produto()) );
		} else if (event.getTarget() == btnPesquisar) {
			String cod = txtCod.getText();
			Produto q = control.buscarProduto(cod);
			//entidadeParaBoundary(q);			
		}
	}

}
