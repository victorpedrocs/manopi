package test.functional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import util.ConnectionFactory;
import cliente.model.Cliente;
import cliente.model.ClienteDAO;


public class TestClienteDAO {
	
	private static Cliente cliente;
	public static Connection con;
	
	@BeforeClass
	public static void before(){
		con = ConnectionFactory.getConnection();
		cliente = new ClienteDAO(con).retrieve(new Cliente(null, null, null, null, null, null));
	}
	
	@Test
	public void testClienteCreate() throws SQLException{
		
		Cliente clienteCreate = new Cliente(null, "teste", "teste", "teste", "teste", "teste");
		new ClienteDAO(con).create(clienteCreate);
		
		String sql = "SELECT * FROM cliente WHERE "+preparedParameters();
		PreparedStatement statement = prepareStatement(sql);
		
		ResultSet result = statement.executeQuery();
		assertEquals(true, result.next());
		
	}
	
	@Test
	public  void testClienteRetrieve(){
		
		Cliente clienteBanco = new ClienteDAO(con).retrieve(new Cliente(null, null));
		assertEquals(clienteBanco.getCodigo(), cliente.getCodigo());
		
	}
	
	@AfterClass
	public static void after() throws SQLException{
		StringBuilder delete1 = new StringBuilder("DELETE FROM cliente WHERE ");
		delete1.append(preparedParameters());
		PreparedStatement statement = prepareStatement(delete1.toString());
		
		statement.executeUpdate();
	}
	
	
	// ===========================================================================
	private static String preparedParameters(){
		return "login = ? and senha = ? and nome = ? and telefone = ? and endereco = ?"; 
	}
	
	private static PreparedStatement prepareStatement(String sql) throws SQLException{
		PreparedStatement statement = con.prepareStatement(sql);
		
		statement.setString(1, "teste");
		statement.setString(2, "teste");
		statement.setString(3, "teste");
		statement.setString(4, "teste");
		statement.setString(5, "teste");
		
		return statement;
	}
}
