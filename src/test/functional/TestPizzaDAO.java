package test.functional;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import cardapio.model.Pizza;
import cardapio.model.PizzaDAO;

public class TestPizzaDAO {
	private static Connection conn;
	
	
	@BeforeClass
	public static void prepararTeste() throws SQLException{
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/web_pizza", "postgres",  "postgres");
		
	}
	
	@Test
	public void testPizzaRetrieve(){
		PizzaDAO pizzaDAO = new PizzaDAO(conn);
		Collection<Pizza> pizzas = pizzaDAO.retrieve(new Pizza(null, null));
		
		assertNotNull(pizzas);
	}

}
