package model.testes;

import junit.framework.TestCase;
import model.ContaCorrente;

public class ContaCorrenteTest extends TestCase {
	
	private ContaCorrente contac;
	
	public void setUp() {
		contac = new ContaCorrente();
		
	}

	public void tearDown() {
		contac = null;
		
	}
	
	public void testCreditar() {
		contac.creditar(10.0, false);
		assertEquals(10.0, contac.getSaldo());
	
	}

	public void testAtualiza() {
		contac.setSaldo(100.0);
		contac.atualiza(0.5);
		assertEquals(2 * 100.0, contac.getSaldo());
	}

	public void testDebitar() {
		contac.setSaldo(100.0);
		contac.debitar(50.0, false);
		assertEquals(50.0, contac.getSaldo());
	}

	public void testTransferir() {
		ContaCorrente contac2 = new ContaCorrente();
		contac.setSaldo(100.0);
		contac.transferir(contac2, 50.0);
		assertEquals(50.0, contac2.getSaldo());
		assertEquals(50.0, contac.getSaldo());
	}

}
