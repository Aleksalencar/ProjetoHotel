package hotel.boundary;

import java.awt.Button;

import javax.swing.JOptionPane;

import com.sun.prism.paint.Color;

import hotel.control.PromocoesControl;
import hotel.entidades.Cliente;
import hotel.entidades.Promocoes;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PromocoesBoundary extends Application implements EventHandler<ActionEvent>{
	private PromocoesControl promoControl = new PromocoesControl();
	private TextField txtValor = new TextField();
	private TextField txtCodigoDesconto = new TextField();
	private javafx.scene.control.Button btnAplicarPromocoes = new javafx.scene.control.Button("Gerar cupom promocional");
	private javafx.scene.control.Button btnEnviarEmail = new javafx.scene.control.Button("Enviar cupom para cliente");
	
	public Promocoes boundaryEntidade(){
		Promocoes p = new Promocoes();
		p.setValorDesconto(Integer.parseInt(txtValor.getText()));
		return p;
	}
	
	
	@Override
	public void start(Stage promocoesStage) throws Exception {

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
		
		painelCampos.add(new Label("Valor da promoção: "), 0, 3);
		painelCampos.add(txtValor, 1, 3);
		painelCampos.add(new Label("Código Promocional: "), 0, 4);
		painelCampos.add(txtCodigoDesconto, 1, 4);
		
		txtValor.setTooltip(new Tooltip("Valor do desconto do cupom"));
		txtCodigoDesconto.setTooltip(new Tooltip("Cupom promocional gerado"));
		txtCodigoDesconto.setEditable(false);

		painelBotoes.getChildren().addAll(btnAplicarPromocoes, btnEnviarEmail);
		
		painelPrincipal.setTop(labtitulo);
		painelPrincipal.setCenter(painelCampos);
		painelPrincipal.setBottom(painelBotoes);
		
		btnAplicarPromocoes.addEventHandler(ActionEvent.ANY, this);
		btnEnviarEmail.addEventHandler(ActionEvent.ANY, this);
		
		Scene cena = new Scene(painelPrincipal, 500, 300);
		promocoesStage.setTitle("Promoções para clientes/quartos");
		promocoesStage.setScene(cena);
		promocoesStage.show();
	}

	@Override
	public void handle(ActionEvent event) {
		String retorno;
		if (event.getTarget() == btnAplicarPromocoes){
			retorno = promoControl.generateToken(Integer.parseInt((txtValor.getText())));
			if(retorno != null){
				txtCodigoDesconto.setText(retorno);
				JOptionPane.showMessageDialog(null, "Codigo de desconto gerado com sucesso", "AVISO",JOptionPane.INFORMATION_MESSAGE);
			}
		}else if (event.getTarget() == btnEnviarEmail){
			
		}
		
	}
	
}
