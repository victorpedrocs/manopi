package test.functional;

import java.sql.Connection;

import org.junit.BeforeClass;
import org.junit.Test;

import pedido.model.Pagamento;
import pedido.model.Pedido;
import util.ConnectionFactory;
import cliente.model.Cliente;
import cliente.model.ClienteDAO;


public class TestClienteDAO {
	
	private static Cliente cliente;
	private static Pagamento pagamento;
	private static String testeCreatePk;
	private static Pedido pedido;
	public static Connection con;
	
	@BeforeClass
	public static void before(){
		con = ConnectionFactory.getConnection();
		cliente = new ClienteDAO(con).retrieve(new Cliente(null, null, null, null, null, null));
	}
	
	@Test
	public static void testClienteCreate(){
		
	}
	
	@Test
	public static void testClienteRetrieve(){
		
	}
}
