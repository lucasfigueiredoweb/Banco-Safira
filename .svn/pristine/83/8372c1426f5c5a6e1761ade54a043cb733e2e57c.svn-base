package controller;

import java.io.IOException;
import java.util.Calendar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.ContaCorrente;
import model.ContaPoupanca;
import persistence.contas.ContaCorrenteBusiness;
import persistence.contas.ContaCorrenteRepository;
import persistence.contas.ContaPoupancaBusiness;
import persistence.contas.ContaPoupancaRepository;
import persistence.usuarios.ClienteBusiness;
import persistence.usuarios.ClienteRepository;

public class ContaController extends GenericController {

	@FXML
	private ComboBox<Cliente> comboCliente;

	@FXML
	private ComboBox<String> comboConta;

	@FXML
	private TextField contaSaldo;
	
	@FXML
	private TextField contaNumero;
	
	
	private ClienteRepository crepository = new ClienteBusiness();

	@FXML
	private void initialize() {
			comboCliente.getItems().addAll(crepository.lista());
			comboConta.getItems().addAll("ContaCorrente","ContaPoupança");
	}
	
	
	public void criarConta() {

		if (comboConta.getSelectionModel().getSelectedItem().equals("ContaCorrente")) {

			ContaCorrente conta = new ContaCorrente();
			ContaCorrenteRepository contacrepository = new ContaCorrenteBusiness();
			conta.setSaldo(Double.parseDouble(contaSaldo.getText()));
			conta.setNumero(contaNumero.getText());
			conta.setData(Calendar.getInstance().getTime());

			contacrepository.salvar(conta,comboCliente.getSelectionModel().getSelectedItem());

		}

		else if (comboConta.getSelectionModel().getSelectedItem().equals("ContaPoupança")) {

			ContaPoupanca conta = new ContaPoupanca();
			ContaPoupancaRepository contaprepository = new ContaPoupancaBusiness();
			conta.setSaldo(Double.parseDouble(contaSaldo.getText()));
			conta.setNumero(contaNumero.getText());
			conta.setData(Calendar.getInstance().getTime());

			contaprepository.salvar(conta,comboCliente.getSelectionModel().getSelectedItem());

		}
		
		this.voltar();
		
	}
	
	public void voltar(){
		
		try{
		  Node node=contaSaldo;
		  Stage stage=(Stage) node.getScene().getWindow();
		  Parent root = FXMLLoader.load(getClass().getResource("/application/Inicial.fxml"));/* Exception */
		  Scene scene = new Scene(root,800,600);
		  stage.setScene(scene);
		  stage.show();
		}catch(IOException e){
			ab.display("Error ao abrir FXML");
		}
	}

}
