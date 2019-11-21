package hotel.boundary;

import javax.swing.JOptionPane;

import com.sun.xml.internal.txw2.output.TXWResult;

import hotel.control.ClienteControl;
import hotel.entidades.Cliente;
import hotel.entidades.Produto;
import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ClienteBoundary implements BoundaryContent, EventHandler<ActionEvent>{
	private ClienteControl control = new ClienteControl();
	private TextField txtNome = new TextField();
	private TextField txtEmail = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEnd = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtNumero = new TextField();
	private Executor executor;
	private ComboBox<String> sexo = new ComboBox<>();
	

	
	private BorderPane painelPrincipal = new BorderPane();
	private GridPane painelCampos = new GridPane();
	private FlowPane painelBotoes = new FlowPane();	
	private VBox painelCentral = new VBox();
	
	private Button btnAdicionar = new Button(" Adicionar ");
	private Button btnAlterar = new Button(" Alterar ");
	private Button btnPesquisar = new Button(" Pesquisar Cliente ");
	private Button btnMenu = new Button(" Voltar ao Menu");
	private TableView<Produto> table;
	
	public Cliente boundaryEntidade(){
		Cliente c = new Cliente();
		c.setCPF(txtCpf.getText());
		c.setEmail(txtEmail.getText());
		c.setEndereco(txtEnd.getText());
		c.setNome(txtNome.getText());
		c.setTelefone(txtTelefone.getText());
		return c;
	}
	
	@SuppressWarnings("static-access")	
	public ClienteBoundary(Executor e) {
		this.setExecutor(e);
		
		ObservableList<String> tipoSexo = 
				FXCollections.observableArrayList("Feminino", "Masculino");
		sexo.setItems(tipoSexo);	

		painelPrincipal.setStyle("-fx-padding:20px");

		Label labtitulo = new Label("GERENCIAR CLIENTES");
		labtitulo.setUnderline(true);	
		labtitulo.setFont(Font.font("Arial", FontWeight.BLACK, 25));
		
		painelBotoes.setHgap(15);
		painelCampos.setHgap(5);
		painelCampos.setVgap(20);
		
		painelCampos.add(new Label("Nome"), 0, 0);
		painelCampos.add(txtNome, 1, 0);
		painelCampos.add(new Label("E-mail"), 0, 1);
		painelCampos.add(txtEmail, 1, 1);
		painelCampos.add(new Label("Sexo"), 0, 2);
		painelCampos.add(sexo, 1, 2);
		painelCampos.add(new Label("Telefone"), 0, 3);
		painelCampos.add(txtTelefone, 1, 3);
		painelCampos.add(new Label("Endereco"), 0, 4);
		painelCampos.add(txtEnd, 1, 4);
		painelCampos.add(new Label("CPF"), 0, 5);
		painelCampos.add(txtCpf, 1, 5);

		painelBotoes.getChildren().addAll(btnAdicionar, btnPesquisar,btnAlterar,btnMenu);
		
		painelCentral.setMargin(painelBotoes, new Insets(15));
		painelCentral.getChildren().addAll(painelCampos,painelBotoes);
		table = generateTable();
		
		
		painelPrincipal.setMargin(painelCentral, new Insets(15));
		painelPrincipal.setTop(labtitulo);
		painelPrincipal.setCenter(painelCentral);
		painelPrincipal.setBottom(table);
		
		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		btnAlterar.addEventHandler(ActionEvent.ANY, this);
		
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				executor.executar("Menu principal");
			}
		});
	}
	
	private TableView<Produto> generateTable() {
		TableView<Produto> table = new TableView<>();

	
		TableColumn<Produto, String> coluna1 = new TableColumn<>("Nome");
		coluna1.setCellValueFactory(new PropertyValueFactory<Produto, String>("Nome"));

		TableColumn<Produto, String> coluna2 = new TableColumn<>("E-mail");
		coluna2.setCellValueFactory(new PropertyValueFactory<Produto, String>("E-mail"));
		
		TableColumn<Produto, String> coluna3 = new TableColumn<>("Sexo");
		coluna3.setCellValueFactory(new PropertyValueFactory<Produto, String>("Sexo"));
		
		TableColumn<Produto, String> coluna4 = new TableColumn<>("Telefone");
		coluna4.setCellValueFactory(new PropertyValueFactory<Produto, String>("Telefone"));
		
		TableColumn<Produto, String> coluna5 = new TableColumn<>("Endereco");
		coluna5.setCellValueFactory(new PropertyValueFactory<Produto, String>("Endereco"));
		
		TableColumn<Produto, String> coluna6 = new TableColumn<>("CPF");
		coluna6.setCellValueFactory(new PropertyValueFactory<Produto, String>("CPF"));

		

		table.getColumns().addAll(coluna1, coluna2, coluna3, coluna4, coluna5, coluna6);
		table.setItems(control.getLista());
		return table;

	}

	@Override
	public void handle(ActionEvent event) {
		
		if (event.getTarget() == btnAdicionar) { 
			control.manterCliente( boundaryEntidade() );
		} else if (event.getTarget() == btnPesquisar) {
			String cpfCliente = txtCpf.getText();
			Cliente c = control.buscarCliente(cpfCliente);
			JOptionPane.showMessageDialog(null, c);
		}
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
		// TODO Auto-generated method stub
		return painelPrincipal;
	}

}
