package test.unit;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;
import org.testng.reporters.jq.TimesPanel;

import cliente.model.Cliente;
import pedido.controller.PedidoControle;
import pedido.model.Pagamento;
import pedido.model.Pedido;
import pedido.model.PedidoDAO;
import test.mock.PedidoMock;
import util.ConnectionFactory;

public class TestPedido {
	
	@Test
	public void testlistarFormadePagamento() throws SQLException{
		PedidoControle pc = new PedidoControle();
		assertEquals(4, pc.listarFormaDePagamento().size());
	}
	
	@Test
	public void testAtualizarTotalPago(){
		Pedido pedido = new Pedido(null, null, null, null);
		
		assertEquals(true, pedido.atualizarTotalPago(100d));
		assertEquals(new Double(100), pedido.getTotalPago());
	}
	
	@Test
	public void testValidFields(){
		Cliente cliente = new Cliente(123, null, null, null);
		Pagamento pagamento = new Pagamento(123, null);
		Pedido pedido = new Pedido(cliente, pagamento);
		
		assertEquals(true, pedido.validFields());
	}

	public void testRecuperarValorTotal(){
		PedidoMock pedido = new PedidoMock(null, null);
		assertEquals(new Double(22), pedido.recuperarValorTotal());
		
	}
	
	@Test
	public void testGetters(){
		Pedido pedido = new Pedido(UUID.randomUUID().toString(), new Cliente("teste", "teste"), new Pagamento(null, null), 100d);
		
		assertNotNull(pedido.getCliente());
		assertNotNull(pedido.getCodigo());
		assertNotNull(pedido.getFormaPagamento());
	}
}
