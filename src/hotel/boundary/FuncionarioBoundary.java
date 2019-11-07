package hotel.boundary;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FuncionarioBoundary extends Application implements EventHandler<ActionEvent>{
	private TextField txtNome = new TextField();
	private TextField txtEmail = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEnd = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtNumero = new TextField();
	private TextField txtLogin = new TextField();
	private TextField txtCargo = new TextField();
	private PasswordField txtSenha = new PasswordField();
	private ComboBox<String> sexo = new ComboBox<>();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnAlterar = new Button("Alterar");
	private Button btnPesquisar = new Button("Pesquisar");
	@Override
	public void start(Stage funcionarioStage) throws Exception {
		BorderPane painelPrincipal = new BorderPane();
		GridPane painelCampos = new GridPane();
		FlowPane painelBotoes = new FlowPane();	
		painelPrincipal.setStyle("-fx-padding:20px");
		
		Label labtitulo = new Label("GERENCIAR FUNCIONARIO");
		labtitulo.setUnderline(true);	
		labtitulo.setFont(Font.font("Arial", FontWeight.BLACK, 25));
		
		painelBotoes.setHgap(15);
		painelCampos.setHgap(5);
		painelCampos.setVgap(20);
		
		ObservableList<String> tipoSexo = 
				FXCollections.observableArrayList("Feminino", "Masculino");
		sexo.setItems(tipoSexo);
		
		painelCampos.add(new Label("Nome"), 1, 1);
		painelCampos.add(txtNome, 2, 1);
		painelCampos.add(new Label("E-mail"), 1, 2);
		painelCampos.add(txtEmail, 2, 2);
		painelCampos.add(new Label("Sexo"), 1, 3);
		painelCampos.add(sexo, 2, 3);
		painelCampos.add(new Label("Telefone"), 1, 4);
		painelCampos.add(txtTelefone, 2, 4);
		painelCampos.add(new Label("Endereço"), 1, 5);
		painelCampos.add(txtEnd, 2, 5);
		painelCampos.add(new Label("CPF"), 1, 6);
		painelCampos.add(txtCpf, 2, 6);
		painelCampos.add(new Label("Número"), 1, 7);
		painelCampos.add(txtNumero, 2, 7);
		painelCampos.add(new Label("Login"), 1, 8);
		painelCampos.add(txtLogin, 2, 8);
		painelCampos.add(new Label("Cargo"), 1, 9);
		painelCampos.add(txtCargo, 2, 9);
		painelCampos.add(new Label("Senha"), 1, 10);
		painelCampos.add(txtSenha, 2, 10);

		
		painelBotoes.getChildren().addAll(btnAdicionar, btnPesquisar,btnAlterar);

		painelPrincipal.setTop(labtitulo);
		painelPrincipal.setCenter(painelCampos);
		painelPrincipal.setBottom(painelBotoes);
		
		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		btnAlterar.addEventHandler(ActionEvent.ANY, this);
		
		Scene cena = new Scene(painelPrincipal, 700, 500);
		funcionarioStage.setTitle("Cadastro de Funcionario");
		funcionarioStage.setScene(cena);
		funcionarioStage.show();
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}


}
