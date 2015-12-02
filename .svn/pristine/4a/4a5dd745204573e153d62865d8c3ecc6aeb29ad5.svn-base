package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import controller.AlertBox;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Conta extends EntidadeAbstrata  {

	private final StringProperty numero;
	protected final DoubleProperty saldo;
	private final ObjectProperty<Date> data;

	public Conta() {
		this(null, null, null);
	}

	public Conta(String numero, Double saldo, Date data) {
		this.numero = new SimpleStringProperty(numero);
		this.saldo = new SimpleDoubleProperty(saldo);
		this.data = new SimpleObjectProperty<Date>(data);
	}

	public StringProperty numeroProperty() {
		return numero;
	}

	public DoubleProperty saldoProperty() {
		return saldo;
	}

	public StringProperty dataProperty() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return new SimpleStringProperty(sdf.format(data.get()));
	}

	public void creditar(Double valor, Boolean msg) {
		setSaldo(this.saldo.get() + valor);
		if (msg)
			System.out.println("Sucesso! Novo Saldo:" + saldo);
	}

	public void atualiza(Double taxa) {
		setSaldo(this.saldo.get() + this.saldo.get() * taxa);
	}

	public Boolean debitar(Double valor, Boolean msg) {
		double aux = saldo.get();
		if (this.saldo.get() - valor < 0) {
			new AlertBox().display("Saldo Insuficiente!");
			saldo.set(aux);
			return false;
		} else {
			this.setSaldo(saldo.get() - valor);
			if (msg)
				System.out.println("Sucesso! Novo Saldo:" + saldo);
			return true;
		}
	}

	public void transferir(Conta c, Double valor) {
		if (c != null) {
			if (debitar(valor, false)) {
				c.creditar(valor, false);
				System.out.println("Sucesso!");
				System.out.println("Seu Saldo: " + saldo);
			}

		}

	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String texto = "ID: " + this.getId() + " Numero: " + this.getNumero() + " Saldo : " + this.getSaldo()
				+ " Data de Cadastro: " + sdf.format(this.getData()) + "\n";
		return texto;
	}

	public void extrato() {
		System.out.println("Seu Saldo é :" + getSaldo());
	}

	public Double getSaldo() {
		return saldo.get();
	}

	public String getNumero() {
		return numero.get();
	}

	public void setNumero(String numero) {
		this.numero.set(numero);
		;
	}

	public void setSaldo(Double saldo) {
		this.saldo.set(saldo);
	}

	public Date getData() {
		return data.get();
	}

	public void setData(Date data) {
		this.data.set(data);
	}
	

}
