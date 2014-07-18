package test.unit;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import pedido.controller.PedidoControle;

public class TestPedidoController {
	
	
	@Test
	public static void testlistarFormadePagamento() throws SQLException{
		PedidoControle pc = new PedidoControle();
		assertEquals(4, pc.listarFormaDePagamento().size());
	}
	

}
