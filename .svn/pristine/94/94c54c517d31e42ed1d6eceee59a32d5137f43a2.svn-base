package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Funcionario;
import persistence.usuarios.FuncionarioBusiness;
import persistence.usuarios.FuncionarioRepository;

public class LoginController extends GenericController {

	@FXML
	private TextField userField;

	@FXML
	private PasswordField passField;

	@FXML
	public void onEnter() throws Exception {
		this.handleLoginButton();
	}

	public void handleLoginButton() throws Exception {
		FuncionarioRepository fRepository = new FuncionarioBusiness();

		Funcionario func = fRepository.getFuncionario(userField.getText());

		if (func != null) {
			if (func.getSenha().equals(passField.getText())) {
				funcSessao = func;
				Node node = userField;
				Stage stage = (Stage) node.getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getResource("/application/Inicial.fxml"));/* Exception */
				Scene scene = new Scene(root, 800, 600);
				stage.setScene(scene);
				stage.show();
			}

		}
	}

}
