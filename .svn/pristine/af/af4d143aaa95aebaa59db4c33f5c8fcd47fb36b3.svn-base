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
import persistence.usuarios.ClienteBusiness;
import persistence.usuarios.ClienteRepository;

public class EditController extends GenericController {
	
	@FXML
	private TextField cliNomeBox;
	
	@FXML
	private TextField cliCpfBox;
	
	@FXML
	private TextField cliEmailBox;
	
	@FXML
	private DatePicker cliDataBox;
	
	@FXML
	public void initialize(){
		cliNomeBox.setText(editableCliente.getNome());
		cliCpfBox.setText(editableCliente.getCpf());
		cliEmailBox.setText(editableCliente.getEmail());
		
	}
	
	public void onEnter() throws ParseException{
		this.editCliente();
	}
	
	public void voltar(){
		try {
			Node node = cliCpfBox;
			Stage stage = (Stage) node.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Inicial.fxml"));/* Exception */
			Scene scene = new Scene(root, 800, 600);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editCliente() throws ParseException{
		editableCliente.setNome(cliNomeBox.getText());
		editableCliente.setCpf(cliCpfBox.getText());
		editableCliente.setEmail(cliEmailBox.getText());
		System.out.println(cliDataBox.getEditor().getText());
		editableCliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(cliDataBox.getEditor().getText()));
		ClienteRepository cr = new ClienteBusiness();
		
		cr.atualizar(editableCliente);
		this.voltar();
	}

}
