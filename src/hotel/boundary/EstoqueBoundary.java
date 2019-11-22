package hotel.boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.sun.prism.paint.Color;

import hotel.control.EstoqueController;
import hotel.entidades.Produto;
import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.ListAdapter;

public class EstoqueBoundary implements BoundaryContent, EventHandler<ActionEvent> {

	private EstoqueController control = new EstoqueController();

	private VBox box = new VBox();
	GridPane grid = new GridPane();
	
	private TextField txtCod = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtDesc = new TextField();
	private TextField txtValor = new TextField();
	private TextField txtQtd = new TextField();

	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnMenu = new Button("Voltar");
	private Button btnApagar = new Button("Apagar");
	private Button btnEditar = new Button("Editar");

	private TableView<Produto> table = new TableView<>();

	private int rowIndex;

	private Executor executor;

	private boolean addAll;

	public EstoqueBoundary(Executor e) {
		this.setExecutor(e);
		box.setSpacing(5);

		Label labtitulo = new Label("Estoque");
		labtitulo.setUnderline(true);
		labtitulo.setFont(Font.font("Arial", FontWeight.BLACK, 25));
		box.getChildren().add(labtitulo);

		grid.setHgap(50);
		grid.setVgap(5);
		AddLabel("*Codigo: ", txtCod);
		AddLabel("Nome: ", txtNome);
		AddLabel("Descrição: ", txtDesc);
		AddLabel("*Valor: ", txtValor);
		AddLabel("*Quantidade: ", txtQtd);
		grid.add(btnAdicionar, 0, rowIndex);
		grid.add(btnPesquisar, 1, rowIndex);
		grid.add(btnApagar, 2, rowIndex);
		grid.add(btnEditar, 3, rowIndex);
		grid.add(btnMenu, 4, rowIndex);
		box.getChildren().add(grid);

		generateTable();
		configuraTabela();
		
		box.getChildren().add(table);
		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		btnApagar.addEventHandler(ActionEvent.ANY, this);
		btnEditar.addEventHandler(ActionEvent.ANY, this);
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				executor.executar("Menu principal");
			}
		});
	}

	private void ajustartabela() {
		control.atualizarTabela();
	}

	private void configuraTabela() {
		BooleanBinding camposPreenchidos = txtCod.textProperty().isEmpty().or(txtDesc.textProperty().isEmpty())
				.or(txtQtd.textProperty().isNull().or(txtQtd.textProperty().isNull()));
		BooleanBinding codigoPreenchido = txtCod.textProperty().isEmpty();
		BooleanBinding algoSelecionado = table.getSelectionModel().selectedItemProperty().isNull();

		btnApagar.disableProperty().bind(algoSelecionado);
		btnEditar.disableProperty().bind(algoSelecionado);
		btnAdicionar.disableProperty().bind(camposPreenchidos);
		btnPesquisar.disableProperty().bind(codigoPreenchido);
		
		txtCod.disableProperty().bind(algoSelecionado.not());
		
		table.getSelectionModel().selectedItemProperty().addListener((observable, old, n) -> {
			if (n != null) {
				txtCod.setText(n.getCodigo());
				txtDesc.setText(n.getDescricao());
				txtNome.setText(n.getNome());
				txtValor.setText(String.valueOf(n.getValor()));
				txtQtd.setText(String.valueOf(n.getQtd()));
			}
		});
	}

	private void AddLabel(String s, TextField t) {
		t.setMinWidth(200);
		Label l = new Label(s);
		l.setFont(Font.font("Arial", FontWeight.BLACK, 12));
		//l.setTextFill(Color.CHARTREUSE);
		l.setFont(Font.font(15));
		t.setAlignment(Pos.CENTER_LEFT);
		grid.add(l, 0, rowIndex);
		grid.add(t, 1, rowIndex);
		rowIndex++;

	}

	private void generateTable() {

		TableColumn<Produto, String> columnCodigo = new TableColumn<>("Codigo");
		columnCodigo.setCellValueFactory(new PropertyValueFactory<Produto, String>("Codigo"));

		TableColumn<Produto, String> columnNome = new TableColumn<>("Nome");
		columnNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("Nome"));

		TableColumn<Produto, String> columnDescricao = new TableColumn<>("Descricao");
		columnDescricao.setCellValueFactory(new PropertyValueFactory<Produto, String>("Descricao"));

		TableColumn<Produto, String> columnValor = new TableColumn<>("Valor");
		columnValor.setCellValueFactory(new PropertyValueFactory<Produto, String>("Valor"));


		TableColumn<Produto, String> columnQtd = new TableColumn<>("Quantidade");
		columnQtd.setCellValueFactory(new PropertyValueFactory<Produto, String>("qtd"));

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		addAll = table.getColumns().addAll(columnCodigo, columnNome, columnDescricao, columnValor, columnQtd);
		table.setItems(control.getLista());
		
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getTarget() == btnAdicionar) {
			control.adicionar(boundaryParaEntidade());
			limpar();
		} else if (event.getTarget() == btnApagar) {
			Produto prodselect = table.getSelectionModel().getSelectedItem();
			control.apagar(prodselect.getCodigo());
			ajustartabela();
		} else if (event.getTarget() == btnEditar){
			Produto prodselect = table.getSelectionModel().getSelectedItem();
			control.editar(boundaryParaEntidade(),prodselect.getCodigo());
			ajustartabela();
			limpar();
		} else if (event.getTarget() == btnPesquisar) {
			String codProduto = txtCod.getText();
			control.buscarProduto(codProduto);		
		}
	}

	@Override
	public void setExecutor(Executor e) {
		// TODO Auto-generated method stub
		this.executor = e;
	}

	@Override
	public Executor getExecutor() {
		return this.executor;
	}

	@Override
	public Pane gerarTela() {
		// TODO Auto-generated method stub
		limpar();
		ajustartabela();
		return box;
	}

	private void limpar() {
		table.getSelectionModel().select(null);
		txtCod.setText(null);
		txtDesc.setText(null);
		txtNome.setText(null);
		txtQtd.setText(null);
		txtValor.setText(null);

	}

	// mover da tela para a entidade
	private Produto boundaryParaEntidade() {
		Produto p = new Produto();
		try {
			p.setCodigo(txtCod.getText());
			p.setNome(txtNome.getText());
			p.setDescricao(txtDesc.getText());
			p.setValor(Double.parseDouble(txtValor.getText()));
			p.setQtd(Integer.parseInt(txtQtd.getText()));
			System.out.println("Produto adicionado");
			return p;
		} catch (Exception e) {
			Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
			dialogoErro.setTitle("Erro ao adicionar");
			dialogoErro.setHeaderText(e.getMessage());
			dialogoErro.showAndWait();
		}
		return null;
	}


	public void definirBackground() throws FileNotFoundException{
		FileInputStream imagem = new FileInputStream("src/hotel/images/estoque.jpg");
		Image image = new Image(imagem); 
	    
		BackgroundImage backgroundimage = new BackgroundImage(image,
	    		BackgroundRepeat.NO_REPEAT,  
	    		BackgroundRepeat.NO_REPEAT,  
	    		BackgroundPosition.DEFAULT,  
	    		BackgroundSize.DEFAULT); 
        
	    Background background = new Background(backgroundimage); 
        box.setBackground(background);
	}
}
