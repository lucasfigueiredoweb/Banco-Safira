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

public class TransferirBox extends GenericController {

	private static ObservableList<ContaCorrente> contasC;
	private static ObservableList<ContaPoupanca> contasP;
	private static Stage transferirBox;
	private static String tipo;

	// Creditar
	@FXML
	private TextField valorTransferir;

//	@FXML
//	private ComboBox<Conta> ;
	
	@FXML
	private TextField contaTransferencia;

	public void display(ObservableList<ContaCorrente> contas1, String tipo1, boolean corrente) {

		try {
			contasC = contas1;
			tipo = tipo1;
			transferirBox = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/TransferirBox.fxml"));/* Exception */
			transferirBox.initModality(Modality.APPLICATION_MODAL);
			Scene scene;
			scene = new Scene(root, 500, 100);

			transferirBox.setScene(scene);

			transferirBox.showAndWait();
		} catch (IOException e) {
			ab.display("Error ao abrir FXML");
			e.printStackTrace();
		}

	}

	public void display(ObservableList<ContaPoupanca> contas1, String tipo1) {

		try {
			contasP = contas1;
			tipo = tipo1;
			transferirBox = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/TransferirBox.fxml"));/* Exception */
			transferirBox.initModality(Modality.APPLICATION_MODAL);
			Scene scene;
			scene = new Scene(root, 500, 100);

			transferirBox.setScene(scene);

			transferirBox.showAndWait();
		} catch (IOException e) {
			ab.display("Error ao abrir FXML");
			e.printStackTrace();
		}

	}

	@FXML
	public void onEnter() {
		this.transferir();
	}

	public void transferir() {
		if (valorTransferir.getText() != "" && contaTransferencia != null) {
			if (tipo.equals("Conta Corrente")) {
				try {
					ContaCorrenteRepository ccRepository = new ContaCorrenteBusiness();
					ContaPoupancaRepository cpRepository = new ContaPoupancaBusiness();
					ContaCorrente contaC = contasC.get(0);
					ContaCorrente conta = null;
					ContaPoupanca conta2 = null;
					
					conta = ccRepository.getConta(contaTransferencia.getText());
					
					if(!(conta != null)){
						conta2 = cpRepository.getConta(contaTransferencia.getText());
					}
					if(conta != null){
					contaC.transferir(conta,
							Double.parseDouble(valorTransferir.getText()));
					ccRepository.atualizar(contaC);
					ccRepository.atualizar(conta);
					}else if(conta2 != null){
						contaC.transferir(conta2,
								Double.parseDouble(valorTransferir.getText()));
						ccRepository.atualizar(contaC);
						cpRepository.atualizar(conta2);
					}
					
					transferirBox.close();
				} catch (NumberFormatException e) {
					ab.display("Valor inválido");
				}

			}
			if (tipo.equals("Conta Poupança")) {
				try {
					ContaCorrenteRepository ccRepository = new ContaCorrenteBusiness();
					ContaPoupancaRepository cpRepository = new ContaPoupancaBusiness();
					ContaPoupanca contaP = contasP.get(0);
					ContaPoupanca conta = null;
					ContaCorrente conta2 = null;
					
					conta = cpRepository.getConta(contaTransferencia.getText());
					
					if(!(conta != null)){
						conta2 = ccRepository.getConta(contaTransferencia.getText());
					}
					if(conta != null){
					contaP.transferir(conta,
							Double.parseDouble(valorTransferir.getText()));
					cpRepository.atualizar(contaP);
					cpRepository.atualizar(conta);
					}else if(conta2 != null){
						contaP.transferir(conta2,
								Double.parseDouble(valorTransferir.getText()));
						cpRepository.atualizar(contaP);
						ccRepository.atualizar(conta2);
					}
				
					transferirBox.close();
				} catch (NumberFormatException e) {
					ab.display("Valor inválido");
				}
			}
		}
	}

	public void close() {
		transferirBox.close();
	}


}
