package controller;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ContaCorrente;
import model.ContaPoupanca;
import persistence.contas.ContaCorrenteBusiness;
import persistence.contas.ContaCorrenteRepository;
import persistence.contas.ContaPoupancaBusiness;
import persistence.contas.ContaPoupancaRepository;

public class DebitarBox extends GenericController {

	private static ObservableList<ContaCorrente> contasC;
	private static ObservableList<ContaPoupanca> contasP;
	private static Stage debitarBox;
	private static String tipo;

	@FXML
	private TextField valorDebitar;

	public void display(ObservableList<ContaCorrente> contas1, String tipo1, boolean corrente) throws IOException {

		contasC = contas1;
		tipo = tipo1;
		debitarBox = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/DebitarBox.fxml"));/* Exception */
		debitarBox.initModality(Modality.APPLICATION_MODAL);
		Scene scene;
		scene = new Scene(root, 400, 100);

		debitarBox.setScene(scene);

		debitarBox.showAndWait();

	}

	public void display(ObservableList<ContaPoupanca> contas1, String tipo1) throws IOException {

		contasP = contas1;
		tipo = tipo1;
		debitarBox = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/DebitarBox.fxml"));/* Exception */
		debitarBox.initModality(Modality.APPLICATION_MODAL);
		Scene scene;
		scene = new Scene(root, 400, 100);

		debitarBox.setScene(scene);

		debitarBox.showAndWait();

	}

	@FXML
	public void onEnter() {
		this.debitar();
	}

	public void debitar() {
		if (valorDebitar.getText() != "") {
			Double valor = 0.0;
			try {
				valor = Double.parseDouble(valorDebitar.getText());
				if (tipo.equals("Conta Corrente")) {
					ContaCorrenteRepository crepository = new ContaCorrenteBusiness();
					for (ContaCorrente t : contasC) {
						t.debitar(valor, true);
						crepository.atualizar(t);
					}
				} else if (tipo.equals("Conta Poupança")) {
					ContaPoupancaRepository crepository = new ContaPoupancaBusiness();
					for (ContaPoupanca t : contasP) {
						t.debitar(valor, true);
						crepository.atualizar(t);
					}

				}
				this.close();
			} catch (NumberFormatException e) {
				try {
					ab.display("Valor inválido");
				} catch (Exception e2) {
					System.out.println("Erro ao tentar mostrar AlertBox");
				}
			}
		}
	}

	public void close() {
		debitarBox.close();
	}

}
