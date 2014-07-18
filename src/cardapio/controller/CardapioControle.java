package cardapio.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import util.ConnectionFactory;
import cardapio.model.Pizza;
import cardapio.model.PizzaDAO;

public class CardapioControle {
	
	public ArrayList<Pizza> listarPizzas() throws SQLException {
        PizzaDAO pDAO = new PizzaDAO(ConnectionFactory.getConnection());

        ArrayList<Pizza> cardapio = pDAO.listarPizzas();
        
        return cardapio;
	}
}
