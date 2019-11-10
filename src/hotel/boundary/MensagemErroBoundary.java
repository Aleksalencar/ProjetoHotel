package hotel.boundary;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MensagemErroBoundary implements EventHandler<ActionEvent> {
	Button btnOK = new Button("OK");
	Stage palco;

	public void start(String s) throws Exception {
		Stage palco = new Stage();
		btnOK.addEventHandler(ActionEvent.ANY, this);

		VBox raiz = new VBox();
		Label lblMensagem = new Label(s);

		raiz.setAlignment(Pos.BASELINE_CENTER);
		raiz.setSpacing(20);
		raiz.getChildren().add(lblMensagem);
		raiz.getChildren().add(btnOK);

		Scene cena = new Scene(raiz, 300, 100);
		palco.setTitle("Mensagem erro");
		palco.setScene(cena);
		palco.show();

		this.palco = palco;
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getTarget() == btnOK) {
			palco.close();
		}
	}

}
