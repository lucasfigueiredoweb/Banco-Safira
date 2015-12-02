package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Funcionario extends Pessoa {
	

	private final DoubleProperty salario;
	private final StringProperty senha;
	
	public Funcionario(){
		this(null,null,null,null,0.0,null);
	}
	
	public Funcionario(String nome,String cpf,String email,Date dataNascimento,Double salario,String senha){
		super(nome,cpf,email,dataNascimento);
		this.senha = new SimpleStringProperty(senha);
		this.salario = new SimpleDoubleProperty(salario);
	}
	
	public String getSenha() {
		return senha.get();
	}

	public void setSenha(String senha) {
		this.senha.set(senha);
	}

	public Double getSalario() {
		return salario.get();
	}

	public void setSalario(Double salario) {
		this.salario.set(salario);
	}
	
	public String toString(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String text = "Id: "+this.getId()+" Nome: "+this.getNome() + " CPF:"+this.getCpf()
				+" Email: "+this.getEmail()+ " Data de Nascimento: "+sdf.format(this.getDataNascimento())+" Salario: "+this.getSalario()+"\n";
		return text;
	}
	
	
}
