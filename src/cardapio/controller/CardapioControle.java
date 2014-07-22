package cardapio.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import util.ConnectionFactory;
import cardapio.model.Pizza;
import cardapio.model.PizzaDAO;

public class CardapioControle {
	
	public ArrayList<Pizza> listarPizzas() throws SQLException {
        PizzaDAO pDAO = new PizzaDAO(ConnectionFactory.getConnection());

        ArrayList<Pizza> cardapio = (ArrayList<Pizza>) pDAO.retrieve(new Pizza(null, null, null, null));
        
        return cardapio;
	}
}
