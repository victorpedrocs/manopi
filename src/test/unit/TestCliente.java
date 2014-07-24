package test.unit;

import org.junit.Test;

import com.sun.security.ntlm.Client;

import cliente.model.Cliente;
import static org.junit.Assert.*;

public class TestCliente {
	
	@Test
	public void testLogin(){
		Cliente cliente = new Cliente("teste", "teste");
		assertEquals(true, cliente.validaLogin());
	}
	
	@Test
	public void testGetters(){
		
		Cliente cliente = new Cliente(0, "teste", "teste", "Teste", "teste", "teste");
		assertNotNull(cliente.getCodigo());
		assertNotNull(cliente.getLogin());
		assertNotNull(cliente.getSenha());
		assertNotNull(cliente.getNome());
		assertNotNull(cliente.getTelefone());
		assertNotNull(cliente.getEndereco());
	}

}
