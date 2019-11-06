package hotel.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainMenuController implements Initializable {

	@FXML
	private Label label;
	
	@FXML
	private void acaoCliente(ActionEvent event) {
		System.out.println("Cliente");	
		label.setText("OlaMundo");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
