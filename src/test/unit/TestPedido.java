package test.unit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import pedido.controller.PedidoControle;
import pedido.model.Pedido;
import pedido.model.PedidoDAO;
import util.ConnectionFactory;

public class TestPedido {
	
	
	@Test
	public void testlistarFormadePagamento() throws SQLException{
		PedidoControle pc = new PedidoControle();
		assertEquals(4, pc.listarFormaDePagamento().size());
	}
	
	
	@Test
	public void testAtualizarTotalPago(){
		Pedido pedido = new PedidoDAO(ConnectionFactory.getConnection()).retrieve(new Pedido(null, null)).iterator().next();
		
		assertEquals(true, pedido.atualizarTotalPago(100d));
		assertEquals(new Double(100), pedido.getTotalPago());
		
	}
	
	

}
