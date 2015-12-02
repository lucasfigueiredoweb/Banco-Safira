package model.exceptions;

@SuppressWarnings("serial")
public class ContaJaCadastradaException extends Exception {
	
	@Override
	public void printStackTrace(){
		System.out.println("Conta já Cadastrada!");
	}

}
