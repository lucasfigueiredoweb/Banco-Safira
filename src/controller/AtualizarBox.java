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

public class AtualizarBox extends GenericController {

	private static ObservableList<ContaCorrente> contasC;
	private static ObservableList<ContaPoupanca> contasP;
	private static Stage atualizarBox;
	private static String tipo;
	
	// Creditar
	@FXML
	private TextField valorAtualizar;

	
	
	public void display(ObservableList<ContaCorrente> contas1,String tipo1,boolean corrente){
		
		try{
		contasC =  contas1;
		tipo = tipo1;
		atualizarBox = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/AtualizarBox.fxml"));/* Exception */
		atualizarBox.initModality(Modality.APPLICATION_MODAL);
		Scene scene;
		scene = new Scene(root, 400, 100);

		atualizarBox.setScene(scene);

		atualizarBox.showAndWait();
		}catch(IOException e){
			ab.display("Error ao abrir FXML");
		}
		

	}
	
	public void display(ObservableList<ContaPoupanca> contas1,String tipo1){
		
		try{
		contasP =  contas1;
		tipo = tipo1;
		atualizarBox = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/AtualizarBox.fxml"));/* Exception */
		atualizarBox.initModality(Modality.APPLICATION_MODAL);
		Scene scene;
		scene = new Scene(root, 400, 100);

		atualizarBox.setScene(scene);

		atualizarBox.showAndWait();
		}catch(IOException e ){
			ab.display("Error ao abrir FXML");
		}
		

	}
	
	@FXML
	public void onEnter(){
		this.atualizar();
	}

	
	public void atualizar(){
		if (valorAtualizar.getText() != "") {
			Double valor = 0.0;
			try{
				 valor = Double.parseDouble(valorAtualizar.getText());
				 if(tipo.equals("Conta Corrente")){
						ContaCorrenteRepository crepository =  new  ContaCorrenteBusiness();
						for (ContaCorrente t : contasC) {
							t.atualiza(valor);
							crepository.atualizar(t);
						}
					}
					else if(tipo.equals("Conta Poupança")){
						ContaPoupancaRepository crepository = new ContaPoupancaBusiness();
						for (ContaPoupanca t : contasP) {
							t.atualiza(valor);
							crepository.atualizar(t);
						}
						
					}
				 this.close();
			}catch(NumberFormatException e){
				try{
				ab.display("Valor inválido");
				}catch(Exception e2){
					System.out.println("Erro ao tentar mostrar AlertBox");
				}
			}
			
			
			
			
			
		}
		
	}
	
	public void close(){
		atualizarBox.close();
	}

}
