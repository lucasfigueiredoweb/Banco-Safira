package model.exceptions;

@SuppressWarnings("serial")
public class NenhumaContaCadastradaException extends Exception{
	
	@Override
	public void printStackTrace(){
		System.out.println("Nenhuma conta cadastrada!!");
	}

}
