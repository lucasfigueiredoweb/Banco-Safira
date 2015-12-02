package util.testes;

import java.sql.Connection;

import junit.framework.TestCase;
import util.ConnectionFactory;

public class ConnectionFactoryTest extends TestCase {
	
	private Connection con;
	
	public void setUp(){
		con = ConnectionFactory.getConnection();
	}
	
	public void tearDown(){
		con = null;
	}
	
	public void testGetConnection(){
		assertNotNull(con);
	}
	
}
