package hotel.boundary;

import java.util.Set;

import javax.swing.JOptionPane;

import hotel.control.FuncionarioControl;
import hotel.entidades.Cliente;
import hotel.entidades.Funcionario;
import hotel.enumeracao.tipoFuncionario;
import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

public class FuncionarioBoundary implements BoundaryContent, EventHandler<ActionEvent>{
	private FuncionarioControl control = new FuncionarioControl();
	
	private BorderPane painelPrincipal = new BorderPane();
	private GridPane painelCampos = new GridPane();
	private FlowPane painelBotoes = new FlowPane();	
	private VBox painelCentral = new VBox();

	private TextField txtNome = new TextField();
	private TextField txtEmail = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEnd = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtNumero = new TextField();
	private TextField txtLogin = new TextField();
	private PasswordField txtSenha = new PasswordField();
	private ComboBox<String> sexo = new ComboBox<>();
	private ComboBox<tipoFuncionario> cargo = new ComboBox<>();
	
	private Button btnAdicionar = new Button(" Adicionar ");
	private Button btnEditar = new Button(" Alterar ");
	private Button btnPesquisar = new Button(" Pesquisar ");
	private Button btnApagar = new Button(" Apagar ");
	private Button btnMenu = new Button(" Voltar ao Menu");
	private Executor executor;
	
	private TableView<Funcionario> table;

	
	public Funcionario boundaryEntidade(){
		Funcionario func = new Funcionario();
		func.setCPF(txtCpf.getText());
		func.setEmail(txtEmail.getText());
		func.setEndereco(txtEnd.getText());
		func.setNome(txtNome.getText());
		func.setTelefone(txtTelefone.getText());
		func.setCargo(cargo.getValue());
		return func;
	}
	
	@SuppressWarnings("static-access")	
	public FuncionarioBoundary(Executor e) {
		this.setExecutor(e);
		
		painelPrincipal.setStyle("-fx-padding:5px");
		
		Label labtitulo = new Label("GERENCIAR FUNCIONARIO");
		labtitulo.setUnderline(true);	
		labtitulo.setFont(Font.font("Arial", FontWeight.BLACK, 25));
		
		painelBotoes.setHgap(15);
		painelCampos.setHgap(5);
		painelCampos.setVgap(25);
		
		ObservableList<String> tipoSexo = 
				FXCollections.observableArrayList("Feminino", "Masculino");
		ObservableList<tipoFuncionario> tipoFunc = 
				FXCollections.observableArrayList(tipoFuncionario.values());
		sexo.setItems(tipoSexo);
		cargo.setItems(tipoFunc);
		
		painelCampos.add(new Label("Nome"),0 ,0 );
		painelCampos.add(txtNome,1 ,0 );
		painelCampos.add(new Label("Sexo"), 0, 1);
		painelCampos.add(sexo,1 ,1 );
		painelCampos.add(new Label("CPF"), 0, 2);
		painelCampos.add(txtCpf, 1, 2);
		painelCampos.add(new Label("Telefone"), 2, 0);
		painelCampos.add(txtTelefone, 3, 0);
		painelCampos.add(new Label("Endereco"), 2, 1);
		painelCampos.add(txtEnd, 3, 1);
		painelCampos.add(new Label("E-mail"), 2, 2);
		painelCampos.add(txtEmail, 3, 2);
		painelCampos.add(new Label("Cargo"), 4, 0);
		painelCampos.add(cargo, 5, 0);
		painelCampos.add(new Label("Login"), 4, 1);
		painelCampos.add(txtLogin, 5, 1);
		painelCampos.add(new Label("Senha"), 4, 2);
		painelCampos.add(txtSenha, 5, 2);
//		painelCampos.add(new Label("Numero"), , );
//		painelCampos.add(txtNumero, , );
		
		painelBotoes.getChildren().addAll(btnAdicionar, btnPesquisar,btnEditar,btnMenu);
		
		painelCentral.setMargin(painelBotoes, new Insets(15));
		painelCentral.getChildren().addAll(painelCampos,painelBotoes);
		table = generateTable();
		
		painelPrincipal.setMargin(painelBotoes, new Insets(15));
		painelPrincipal.setTop(labtitulo);
		painelPrincipal.setCenter(painelCentral);
		painelPrincipal.setBottom(table);
		
		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		btnEditar.addEventHandler(ActionEvent.ANY, this);
		
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				executor.executar("Menu principal");
			}
		});

	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getTarget() == btnAdicionar) { 
			control.manterFuncioario( boundaryEntidade() );
		} else if (event.getTarget() == btnPesquisar) {
			String cpfFuncionario = txtCpf.getText();}
//			Funcionario func = control.buscarFuncionario(cpfFuncionario);
//		} else if (event.getTarget() == btnEditar){
//			Funcionario FuncSelect = table.getSelectionModel().getSelectedItem();
//			control.alterarCliente(boundaryEntidade(),FuncSelect.getCPF());
//			ajustartabela();
//		} else if (event.getTarget() == btnApagar) {
//			Funcionario clienteSelect = table.getSelectionModel().getSelectedItem();
////			control.apagar(FuncSelect.getCPF());
//			ajustartabela();
		
	}
	
	private TableView<Funcionario> generateTable() {
		TableView<Funcionario> table = new TableView<>();

	
		TableColumn<Funcionario, String> coluna1 = new TableColumn<>("Nome");
		coluna1.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Nome"));

		TableColumn<Funcionario, String> coluna2 = new TableColumn<>("Email");
		coluna2.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Email"));
		
		TableColumn<Funcionario, String> coluna3 = new TableColumn<>("Sexo");
		coluna3.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Sexo"));
		
		TableColumn<Funcionario, String> coluna4 = new TableColumn<>("Telefone");
		coluna4.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Telefone"));

		TableColumn<Funcionario, String> coluna5 = new TableColumn<>("CPF");
		coluna4.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("CPF"));
		
		TableColumn<Funcionario, String> coluna6 = new TableColumn<>("Cargo");
		coluna4.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Cargo"));


		table.getColumns().addAll(coluna1, coluna2, coluna3, coluna4, coluna5, coluna6);
		//table.setItems(control.getLista());
		return table;

	}
	
//	private void configuraTabela() {
//		BooleanBinding camposPreenchidos = txtNome.textProperty().isEmpty()
//				.or(txtEmail.textProperty().isEmpty())
//				.or(txtEnd.textProperty().isEmpty())
//				.or(txtCpf.textProperty().isEmpty())
//				.or(txtTelefone.textProperty().isEmpty());
//		BooleanBinding algoSelecionado = table.getSelectionModel().selectedItemProperty().isNull();
//		BooleanBinding cpfpreenchido = txtCpf.textProperty().isEmpty();
//		
//		btnApagar.disableProperty().bind(algoSelecionado);
//		btnEditar.disableProperty().bind(algoSelecionado);
//		btnAdicionar.disableProperty().bind(camposPreenchidos);
//		btnPesquisar.disableProperty().bind(cpfpreenchido);
//
//		
//		table.getSelectionModel().selectedItemProperty().addListener((observable, old, n) -> {
//			if (n != null) {
//				txtNome.setText(n.getNome());
//				txtEmail.setText(n.getEmail());
//				txtEnd.setText(n.getEndereco());
//				txtCpf.setText(n.getCPF());
//				txtTelefone.setText(n.getTelefone());
//			}
//		});
//	}
//	
//	private void ajustartabela() {
//		control.atualizarTabela();
//	}	
	

	@Override
	public Pane gerarTela() {
		// TODO Auto-generated method stub
		return painelPrincipal;
	}


	@Override
	public void setExecutor(Executor e) {
		this.executor = e;
	}
	
	@Override
	public Executor getExecutor() {
		return this.executor;
	}


}
