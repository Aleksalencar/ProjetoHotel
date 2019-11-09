package hotel.boundary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import hotel.control.EstoqueController;
import hotel.entidades.Produto;
import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EstoqueBoundary implements BoundaryContent, EventHandler<ActionEvent> {

	private EstoqueController control = new EstoqueController();
	private TextField txtCod = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtDesc = new TextField();
	private TextField txtValor = new TextField();
	private TextField txtQtd = new TextField();

	private Button btnAdicionar = new Button("Adicionar/Alterar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnMenu = new Button("Voltar");

	GridPane grid = new GridPane();
	private int rowIndex;

	private VBox box = new VBox();
	private TableView table = new TableView();

	private Executor executor;

	public EstoqueBoundary(Executor e) {
		this.setExecutor(e);
		box.setSpacing(5);

		Label l = new Label("Estoque");
		l.setFont(Font.font(30));
		l.setAlignment(Pos.CENTER);
		box.getChildren().add(l);

		grid.setHgap(50);
		grid.setVgap(5);
		AddLabel("Codigo: ", txtCod);
		AddLabel("Nome: ", txtNome);
		AddLabel("Descrição: ", txtDesc);
		AddLabel("Valor: ", txtValor);
		AddLabel("Quantidade: ", txtQtd);
		grid.add(btnAdicionar, 0, rowIndex);
		grid.add(btnPesquisar, 1, rowIndex);
		grid.add(btnMenu, 2, rowIndex);
		box.getChildren().add(grid);

		generateTable();
		box.getChildren().add(table);
		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				executor.executar("Menu principal");
			}
		});
	}

	private void AddLabel(String s, TextField t) {
		t.setMinWidth(200);
		Label l = new Label(s);
		l.setFont(Font.font(15));
		t.setAlignment(Pos.CENTER_LEFT);
		grid.add(l, 0, rowIndex);
		grid.add(t, 1, rowIndex);
		rowIndex++;

	}

	private void generateTable() {
		TableColumn<Produto, String> columnCodigo = new TableColumn<>("Código");
		columnCodigo.setCellValueFactory(new PropertyValueFactory<Produto, String>("Código"));

		TableColumn<Produto, String> columnNome = new TableColumn<>("Nome");
		columnNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("Nome"));

		TableColumn<Produto, String> columnDescricao = new TableColumn<>("Descricao");
		columnDescricao.setCellValueFactory(new PropertyValueFactory<Produto, String>("Descricao"));

		TableColumn<Produto, String> columnValor = new TableColumn<>("Valor");
		columnValor.setCellValueFactory(new PropertyValueFactory<Produto, String>("Valor"));

		TableColumn<Produto, String> columnQtd = new TableColumn<>("Quantidade");
		columnQtd.setCellValueFactory(new PropertyValueFactory<Produto, String>("Quantidade"));

		table.getColumns().addAll(columnCodigo, columnNome, columnDescricao, columnValor, columnQtd);
		table.setItems(control.getLista());
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getTarget() == btnAdicionar) {
			control.adicionar(boundaryParaEntidade());
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
		return box;
	}

	// mover da entidade para a tela
	private void entidadeParaBoundary(Produto p) {
		if (p != null) {
			txtCod.setText(p.getCodigo());
			txtNome.setText(p.getNome());
			txtDesc.setText(p.getDescricao());
			txtValor.setText(String.valueOf(p.getValor()));
			txtQtd.setText(String.valueOf(p.getQtd()));
			System.out.println("cod ="+txtCod.getText());
			System.out.println("qtd ="+txtQtd.getText());
		}

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
}
