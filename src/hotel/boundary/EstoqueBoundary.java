package hotel.boundary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import hotel.control.EstoqueController;
import hotel.entidades.Produto;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EstoqueBoundary extends Application implements EventHandler<Event> {

	private EstoqueController control = new EstoqueController();
	private TextField txtCod = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtDesc = new TextField();
	private TextField txtValor = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	
	GridPane grid = new GridPane();
	private int rowIndex;

	private VBox box = new VBox();
	private TableView<Produto> table = new TableView<>();
	ArrayList<Produto> lista = new ArrayList<Produto>();
	ObservableList<Produto> dados = FXCollections.observableList(lista);
	
	@Override
	public void start(Stage stage) throws Exception {
		box.setPrefSize(500,300);
		box.setSpacing(20);
		//box.setAlignment(Pos.CENTER);
		
		grid.setHgap(50);
		grid.setVgap(5);
		AddLabel("Codigo: ",txtCod);
		AddLabel("Nome: ",txtNome);
		AddLabel("Descrição: ",txtDesc);
		Button btAdd = new Button("Adicionar/Alterar");
		Button btRemove = new Button("Remover");
		//btAdd.setMinWidth(30);
		btRemove.setPrefWidth(70);
		grid.add(btAdd, 0, rowIndex);
		grid.add(btRemove, 1, rowIndex);
						
		
		generateTable();
		box.getChildren().add(grid);
		Scene scn = new Scene(box);
		stage.setScene(scn);
		stage.show();
	}
	
	private void generateTable() {
        TableColumn colunaNome = new TableColumn<>("Nome");
        TableColumn colunaIdade = new TableColumn<>("Idade");
        TableColumn colunaEmail = new TableColumn<>("E-mail");

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(FXCollections.observableArrayList(control.getLista()));
        table.getColumns().addAll(colunaNome, colunaIdade, colunaEmail);
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

	@Override
	public void handle(Event event) {
		if (event.getTarget() == btnAdicionar) { 
			//control.adicionar( boundaryParaEntity(new Produto()) );
		} else if (event.getTarget() == btnPesquisar) {
			String cod = txtCod.getText();
			Produto q = control.buscarProduto(cod);
			//entidadeParaBoundary(q);			
		}
	}


}
