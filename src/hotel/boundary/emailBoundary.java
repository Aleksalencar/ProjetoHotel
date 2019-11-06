package hotel.boundary;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class emailBoundary extends Application{
	private TextField txtHostServer = new TextField();
	private TextField txtUsername = new TextField();
	private PasswordField txtSenha = new PasswordField();
	private TextField txtEmail = new TextField();
	private TextField txtPara = new TextField();
	private TextField txtDe = new TextField();
	private TextField txtAssunto = new TextField();
	private TextArea  txtMensagem = new TextArea();
	private javafx.scene.control.Button btnEnviarEmail = new javafx.scene.control.Button("Enviar");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane painelPrincipal = new BorderPane();
		GridPane painelCampos = new GridPane();
		FlowPane painelBotoes = new FlowPane();	
		painelPrincipal.setStyle("-fx-padding:20px");
		
		Label labtitulo = new Label("GERENCIAR PROMOCOES");
		labtitulo.setUnderline(true);	
		labtitulo.setFont(Font.font("Arial", FontWeight.BLACK, 25));
		
		painelBotoes.setHgap(15);
		painelCampos.setHgap(5);
		painelCampos.setVgap(15);
		
		painelCampos.add(new Label("Assunto"), 0, 3);
		painelCampos.add(txtAssunto, 0, 4);
		painelCampos.add(new Label("Email de destino"), 0, 5);
		painelCampos.add(txtEmail, 0, 6);
		painelCampos.add(new Label("Senha"), 0, 7);
		
		
	}
	
}
