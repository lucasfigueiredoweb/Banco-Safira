package controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import persistence.usuarios.ClienteBusiness;
import persistence.usuarios.ClienteRepository;

public class ClienteController extends GenericController {
	
	@FXML
	private TextField clienteNome;
	
	@FXML
	private TextField clienteEmail;
	
	@FXML
	private TextField clienteCpf;
	
	@FXML
	private DatePicker clienteDataNascimento;
	
	public void criarCliente(){
		
		Cliente c = new Cliente();
		ClienteRepository crepository = new ClienteBusiness();
	
		
		c.setNome(clienteNome.getText());
		c.setEmail(clienteEmail.getText());
		c.setCpf(clienteCpf.getText());
		try {
			c.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(clienteDataNascimento.getEditor().getText()));
		} catch (ParseException e) {
			System.out.println("Erro de conversão de data!");
		}
		c.setTaxaFidelidade(0.0);
		crepository.salvar(c);
		this.voltar();
		
	}
	
	public void voltar(){
		try {
			Node node = clienteCpf;
			Stage stage = (Stage) node.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Inicial.fxml"));/* Exception */
			Scene scene = new Scene(root, 800, 600);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
