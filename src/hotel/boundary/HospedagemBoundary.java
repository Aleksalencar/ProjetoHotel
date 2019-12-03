package hotel.boundary;

import hotel.control.ClienteControl;
import hotel.control.HospedagemControl;
import hotel.entidades.Cliente;
import hotel.entidades.Produto;
import hotel.entidades.Quarto;
import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HospedagemBoundary implements BoundaryContent, EventHandler<ActionEvent> {

	private Executor executor;
	private HospedagemControl controlHosp = new HospedagemControl();
	private ClienteControl clienteControl = new ClienteControl();

	private GridPane painelCampos = new GridPane();
	private FlowPane painelBtns = new FlowPane();
	private VBox painelCentral = new VBox();
	private BorderPane painelPrincipal = new BorderPane();
	private TableView<Cliente> table;

	Label labtitulo = new Label("Hospedagens");
	private TextField txtNomeCli = new TextField();
	private TextField txtNQuarto = new TextField();
	private Button add = new Button("Adicionar");
	private Button edit= new Button("Editar");
	private Button remove = new Button("Remover");
	private Button btnMenu = new Button("Menu");
	
	public HospedagemBoundary(Executor e) {
		this.setExecutor(e);
		painelCampos.setHgap(20);

		labtitulo = titulo();
		cliente(0);
		quarto(1);
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				executor.executar("Menu principal");
			}
		});
		painelBtns.getChildren().addAll(add, edit, remove, btnMenu);
		painelBtns.setVgap(10);
		painelBtns.setHgap(15);
		painelCentral.getChildren().addAll(painelCampos, painelBtns);
		setPainelPrincipal(labtitulo, painelCentral);

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
				TableView<Cliente> clientetabela = generateTableCliente();
				painelPrincipal.setBottom(clientetabela);
				
			}
		});
		cliente.getChildren().addAll(txtNomeCli, pesquisaCli);

		painelCampos.add(new Label("Cliente"), 0, row);
		painelCampos.add(cliente, 1, row);
	}

	private void quarto(int row) {
		HBox quarto = new HBox();

		Button pesquisaCli = new Button("pesquisar");
		pesquisaCli.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TableView<Quarto> quartotabela = generateTablequarto();
				painelPrincipal.setBottom(quartotabela);
				
			}
		});
		quarto.getChildren().addAll(txtNQuarto, pesquisaCli);

		painelCampos.add(new Label("quarto"), 0, row);
		painelCampos.add(quarto, 1, row);
		
	}
	

	private TableView<Quarto> generateTablequarto() {
		
		TableView<Quarto> table = new TableView<>();
		TableColumn<Quarto, String> columnAndar = new TableColumn<>("Andar");
		columnAndar.setCellValueFactory(new PropertyValueFactory<Quarto, String>("Andar"));

		TableColumn<Quarto, String> columnNumero = new TableColumn<>("Numero");
		columnNumero.setCellValueFactory(new PropertyValueFactory<Quarto, String>("Numero"));

		TableColumn<Quarto, String> columnTipo = new TableColumn<>("Tipo");
		columnTipo.setCellValueFactory(new PropertyValueFactory<Quarto, String>("Tipo"));
		
		TableColumn<Quarto, CheckBox> combo = new TableColumn<>("Alugado");
		combo.setCellValueFactory(new PropertyValueFactory<Quarto, CheckBox>("Alugado"));



		table.getColumns().addAll(columnAndar, columnNumero, columnTipo, combo);
		return table;
		
	}
	
	TableView<Cliente> generateTableCliente() {
		TableView<Cliente> table = new TableView<>();

	
		TableColumn<Cliente, String> coluna1 = new TableColumn<>("Nome");
		coluna1.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nome"));

		TableColumn<Cliente, String> coluna2 = new TableColumn<>("Email");
		coluna2.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Email"));
		
		TableColumn<Cliente, String> coluna3 = new TableColumn<>("Sexo");
		coluna3.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Sexo"));
		
		TableColumn<Cliente, String> coluna4 = new TableColumn<>("Telefone");
		coluna4.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Telefone"));
		
		TableColumn<Cliente, String> coluna5 = new TableColumn<>("Endereco");
		coluna5.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Endereco"));
		
		TableColumn<Cliente, String> coluna6 = new TableColumn<>("CPF");
		coluna6.setCellValueFactory(new PropertyValueFactory<Cliente, String>("CPF"));

		

		table.getColumns().addAll(coluna1, coluna2, coluna3, coluna4, coluna5, coluna6);
		table.setItems(clienteControl.getLista());
		return table;

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
