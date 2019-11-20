package hotel.boundary;

import java.awt.Checkbox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import hotel.control.QuartosController;
import hotel.entidades.Quarto;
import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class QuartoBoundary implements BoundaryContent, EventHandler<ActionEvent> {

	private QuartosController control = new QuartosController();

	private VBox box = new VBox();
	GridPane grid = new GridPane();
	
	private TextField txtAndar = new TextField();
	private TextField txtNumero = new TextField();
	CheckBox chkAlugado = new CheckBox("Alugado");
	private TextField txtTipo = new TextField();
	Button btnAdd = new Button("Add");
	
	private ComboBox<String> combo = new ComboBox<>();

	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnMenu = new Button("Voltar");
	private Button btnApagar = new Button("Apagar");
	private Button btnAddItem = new Button("Adicionar Item");

	private TableView<Quarto> table = new TableView<>();

	private int rowIndex;

	private Executor executor;

	private boolean addAll;
	
	



	public QuartoBoundary(Executor e) {
		this.setExecutor(e);
		box.setSpacing(5);
		
		ObservableList<String> tipoQuarto = 
				FXCollections.observableArrayList("Suite Comum", "Suite Luxo",
						"Suite com Hidromassagem", "Suite Real");
		combo.setItems(tipoQuarto);
		combo.setEditable(true);
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tipoQuarto.add(combo.getValue());
			}
		});


		Label labtitulo = new Label("Quartos");
		labtitulo.setUnderline(true);
		labtitulo.setFont(Font.font("Arial", FontWeight.BLACK, 25));
		box.getChildren().add(labtitulo);

		grid.setHgap(50);
		grid.setVgap(5);
		AddLabel("*Andar:", txtAndar);
		AddLabel("Numero:", txtNumero);
		grid.add(combo, 2,1);
		grid.add(btnAdd, 3, 1);
		grid.add(chkAlugado, 2, 0);
		grid.add(btnAdicionar, 0, rowIndex);
		grid.add(btnAddItem, 1, rowIndex);
		grid.add(btnPesquisar, 2, rowIndex);
		grid.add(btnApagar, 3, rowIndex);
		grid.add(btnMenu, 4, rowIndex);
		box.getChildren().add(grid);

		generateTable();
		configuraTabela();
		
		box.getChildren().add(table);
		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		btnApagar.addEventHandler(ActionEvent.ANY, this);
		btnAddItem.addEventHandler(ActionEvent.ANY, this);
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				executor.executar("Menu principal");
			}
		});
	}

	private void configuraTabela() {
		BooleanBinding camposPreenchidos = txtAndar.textProperty().isEmpty().or(txtNumero.textProperty().isEmpty())
				.or(txtTipo.textProperty().isNull().or(chkAlugado.textProperty().isNotEmpty()));
		// indica se há algo selecionado na tabela
		BooleanBinding algoSelecionado = table.getSelectionModel().selectedItemProperty().isNull();
		// alguns botões só são habilitados se algo foi selecionado na tabela
		btnApagar.disableProperty().bind(algoSelecionado);

	}

	private void AddLabel(String s, TextField t) {
		t.setMinWidth(0);
		Label l = new Label(s);
		l.setFont(Font.font(15));
		t.setAlignment(Pos.CENTER_LEFT);
		grid.add(l, 0, rowIndex);
		grid.add(t, 1, rowIndex);
		rowIndex++;

	}
	
	
	private void generateTable() {

		TableColumn<Quarto, String> columnAndar = new TableColumn<>("Andar");
		columnAndar.setCellValueFactory(new PropertyValueFactory<Quarto, String>("Andar"));

		TableColumn<Quarto, String> columnNumero = new TableColumn<>("Numero");
		columnNumero.setCellValueFactory(new PropertyValueFactory<Quarto, String>("Numero"));

		TableColumn<Quarto, String> columnTipo = new TableColumn<>("Tipo");
		columnTipo.setCellValueFactory(new PropertyValueFactory<Quarto, String>("Tipo"));
		
		TableColumn<Quarto, CheckBox> combo = new TableColumn<>("Alugado");
		combo.setCellValueFactory(new PropertyValueFactory<Quarto, CheckBox>("Alugado"));



		addAll = table.getColumns().addAll(columnAndar, columnNumero, columnTipo, combo);
		table.setItems(control.getLista());
		
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getTarget() == btnAdicionar) {
			control.adicionar(boundaryParaEntidade());
		
		} else if (event.getTarget() == btnApagar) {
			Quarto prodselect = table.getSelectionModel().getSelectedItem();
			control.apagar(prodselect.getNumero());
		
		} else if (event.getTarget() == btnPesquisar) {
			String codNumero = txtNumero.getText();
			Quarto num = control.buscaQuarto(codNumero);
			entidadeParaBoundary(num);
		
		} else if (event.getTarget() == btnAddItem) {
			//control.addItem();
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

	private void limpar() {
		table.getSelectionModel().select(null);
		txtNumero.setText(null);
		txtTipo.setText(null);


	}

	// mover da tela para a entidade
	private Quarto boundaryParaEntidade() {
		Quarto p = new Quarto();
		String alugado;
		boolean check = chkAlugado.isSelected();
		if(check == true) {
			alugado = "SIM";
		}else {
			alugado = "Nâo";
		}
		String output = combo.getSelectionModel().getSelectedItem().toString();

		try {
			p.setAndar(txtAndar.getText());
			p.setNumero(txtNumero.getText());
			p.setAlugado(alugado);
			p.setTipo(output);
			System.out.println("Quarto adicionado");
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			MensagemErroBoundary erro = new MensagemErroBoundary();
			try {
			
				erro.start("Erro ao adicionar");
			} catch (Exception e1) {
			}
		}
		return null;
	}

	private void entidadeParaBoundary(Quarto q) {
		if (txtNumero != null) {
			txtAndar.setText(q.getAndar());
			txtNumero.setText(q.getNumero());
		}
	}
	
}
