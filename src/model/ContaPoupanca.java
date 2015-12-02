package model;

import java.util.Date;

public class ContaPoupanca extends Conta {
	
	public ContaPoupanca(){
		super(null,0.0,null);
	}
	
	public ContaPoupanca(String numero,Double saldo,Date data){
		super(numero,saldo,data);
	}
	
	@Override
	public void atualiza(Double taxa){
		this.setSaldo(saldo.get() + saldo.get()*(taxa*3));
	}
	
	

}
