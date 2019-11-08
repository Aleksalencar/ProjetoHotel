package hotel.boundary;

import javax.swing.JOptionPane;

import com.sun.xml.internal.txw2.output.TXWResult;

import hotel.control.ClienteControl;
import hotel.entidades.Cliente;
import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	
	private Button btnAdicionar = new Button(" Adicionar ");
	private Button btnAlterar = new Button(" Alterar ");
	private Button btnPesquisar = new Button(" Pesquisar Cliente ");
	private Button btnMenu = new Button(" Voltar ao Menu");
	
	public Cliente boundaryEntidade(){
		Cliente c = new Cliente();
		c.setCPF(txtCpf.getText());
		c.setEmail(txtEmail.getText());
		c.setEndereco(txtEnd.getText());
		c.setNome(txtNome.getText());
		c.setTelefone(txtTelefone.getText());
		return c;
	}
	
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

		painelBotoes.getChildren().addAll(btnAdicionar, btnPesquisar,btnAlterar,btnMenu);

		painelPrincipal.setTop(labtitulo);
		painelPrincipal.setCenter(painelCampos);
		painelPrincipal.setBottom(painelBotoes);
		
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
