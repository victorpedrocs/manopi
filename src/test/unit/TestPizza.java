package test.unit;

import org.junit.Test;
import static org.junit.Assert.*;
import cardapio.model.Pizza;

public class TestPizza {
	
	@Test
	public void testPrecoFormatado(){
		Pizza pizza = new Pizza("teste", 22d);
		assertEquals("22.00", pizza.getPrecoFormatado());
	}

}
