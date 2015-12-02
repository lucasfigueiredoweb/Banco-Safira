package model;

import java.util.Date;

public class ContaCorrente extends Conta {
	
	public ContaCorrente(){
		super(null,0.0,null);
	}
	
	public ContaCorrente(String numero,Double saldo,Date data){
		super(numero,saldo,data);
	}

	@Override
	public void atualiza(Double taxa) {
		this.setSaldo(saldo.get() + saldo.get()*(taxa*2));
	}
	
	public String toString(){
		String texto = "Numero: "+this.getNumero()+" Saldo : "+this.getSaldo();
		return texto;
	}

}
