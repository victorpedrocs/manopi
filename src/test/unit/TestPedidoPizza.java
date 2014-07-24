package test.unit;

import org.junit.Test;

import pedido.model.Pedido;
import pedido.model.PedidoPizza;
import cardapio.model.Pizza;
import static org.junit.Assert.*;

public class TestPedidoPizza {
	
	@Test
	public void testValidFields(){
		Pizza pizza = new Pizza(123, null, null, null);
		Pedido pedido = new Pedido(null, null);
		PedidoPizza pedidoPizza = new PedidoPizza(pizza, pedido, 2);
		
		assertEquals(true, pedidoPizza.validFields());
	}
	
	@Test
	public void testCalculaTotal(){
		Double preco = 22d;
		Integer quantidade = 2;
		Pizza pizza = new Pizza("pizza", preco);
		Pedido pedido = new Pedido(null, null);
		PedidoPizza pedidoPizza = new PedidoPizza(pizza, pedido, quantidade);
		assertEquals(new Double(quantidade * preco), pedidoPizza.calculaTotal());
	}

}
