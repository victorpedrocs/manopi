package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Pizza;
import model.PizzaDAO;

public class CardapioControle {
	
	public ArrayList<Pizza> listarPizzas() throws SQLException {
        PizzaDAO pDAO = new PizzaDAO(ConnectionFactory.getConnection());

        ArrayList<Pizza> cardapio = pDAO.listarPizzas();
        
        return cardapio;
	}
}
