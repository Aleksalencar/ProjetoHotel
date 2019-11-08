package hotel.boundary;

import javax.swing.JOptionPane;

import hotel.control.PromocoesControl;
import hotel.entidades.Promocoes;
import hotel.interfaces.BoundaryContent;
import hotel.interfaces.Executor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PromocoesBoundary implements BoundaryContent, EventHandler<ActionEvent> {
	private PromocoesControl promoControl = new PromocoesControl();
	private TextField txtValor = new TextField();
	private TextField txtCodigoDesconto = new TextField();

	private javafx.scene.control.Button btnAplicarPromocoes = new javafx.scene.control.Button(
			"Gerar cupom promocional");
	private javafx.scene.control.Button btnEnviarEmail = new javafx.scene.control.Button("Enviar cupom para cliente");
	private Button btnMenu = new Button(" Voltar ao Menu");

	private BorderPane painelPrincipal = new BorderPane();
	private GridPane painelCampos = new GridPane();
	private FlowPane painelBotoes = new FlowPane();

	private Executor executor;

	public Promocoes boundaryEntidade() {
		Promocoes p = new Promocoes();
		p.setValorDesconto(Integer.parseInt(txtValor.getText()));
		return p;
	}

	public PromocoesBoundary(Executor e) {
		this.setExecutor(e);

		painelPrincipal.setStyle("-fx-padding:20px");

		Label labtitulo = new Label("GERENCIAR PROMOCOES");
		labtitulo.setUnderline(true);
		labtitulo.setFont(Font.font("Arial", FontWeight.BLACK, 25));

		painelBotoes.setHgap(15);
		painelCampos.setHgap(5);
		painelCampos.setVgap(15);

		painelCampos.add(new Label("Valor da promoção: "), 0, 3);
		painelCampos.add(txtValor, 1, 3);
		painelCampos.add(new Label("Código Promocional: "), 0, 4);
		painelCampos.add(txtCodigoDesconto, 1, 4);

		txtValor.setTooltip(new Tooltip("Valor do desconto do cupom"));
		txtCodigoDesconto.setTooltip(new Tooltip("Cupom promocional gerado"));
		txtCodigoDesconto.setEditable(false);

		painelBotoes.getChildren().addAll(btnAplicarPromocoes, btnEnviarEmail, btnMenu);

		painelPrincipal.setTop(labtitulo);
		painelPrincipal.setCenter(painelCampos);
		painelPrincipal.setBottom(painelBotoes);

		btnAplicarPromocoes.addEventHandler(ActionEvent.ANY, this);
		btnEnviarEmail.addEventHandler(ActionEvent.ANY, this);

		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				executor.executar("Menu principal");
			}
		});
	}

	@Override
	public void handle(ActionEvent event) {
		String retorno;
		if (event.getTarget() == btnAplicarPromocoes) {
			retorno = promoControl.generateToken(Integer.parseInt((txtValor.getText())));
			if (retorno != null) {
				txtCodigoDesconto.setText(retorno);
				JOptionPane.showMessageDialog(null, "Codigo de desconto gerado com sucesso", "AVISO",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (event.getTarget() == btnEnviarEmail) {

		}

	}

	@Override
	public void setExecutor(Executor e) {
		// TODO Auto-generated method stub
		this.executor = e;

	}

	@Override
	public Executor getExecutor() {
		// TODO Auto-generated method stub
		return this.executor;
	}

	@Override
	public Pane gerarTela() {
		// TODO Auto-generated method stub
		return painelPrincipal;
	}

}
