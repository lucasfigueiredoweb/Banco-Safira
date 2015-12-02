package model.exceptions;

@SuppressWarnings("serial")
public class ContaNaoEncontradaException extends Exception{
	
	@Override
	public void printStackTrace(){
		System.out.println("Conta Não Encontrada!");
	}

}
