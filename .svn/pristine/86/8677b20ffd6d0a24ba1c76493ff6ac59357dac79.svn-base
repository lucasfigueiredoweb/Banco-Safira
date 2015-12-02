package model.testes;

import junit.framework.TestCase;
import model.ContaPoupanca;

public class ContaPoupancaTest extends TestCase {

	private ContaPoupanca contap;

	public void setUp() {
		contap = new ContaPoupanca();
	}

	public void tearDown() {
		contap = null;
	}

	public void testCreditar() {
		contap.setSaldo(10.0);
		assertEquals(10.0, contap.getSaldo());
	}

	public void testAtualiza() {
		contap.setSaldo(100.0);
		contap.atualiza(0.5);
		assertEquals(2.5 * 100, contap.getSaldo());
	}

	public void testDebitar() {
		contap.setSaldo(200.0);
		contap.debitar(75.0, false);
		assertEquals(125.0, contap.getSaldo());

	}

	public void testTransferir() {
		ContaPoupanca contap2 = new ContaPoupanca();
		contap.setSaldo(250.0);
		contap.transferir(contap2, 125.0);
		assertEquals(125.0, contap2.getSaldo());
		assertEquals(125.0, contap.getSaldo());

	}

	

}
