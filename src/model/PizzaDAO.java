package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ConnectionFactory;

public class PizzaDAO {
	
	
	
	public PizzaDAO() {
		
	}
	
	public ArrayList<Pizza> listarPizzas() throws SQLException {
		
		
        String sql = "SELECT * FROM pizza";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet resultado = pstm.executeQuery();

        String nome = null, ingredientes = null; 
        
        double preco;
        
        int cod_pizza;
        
        ArrayList<Pizza> cardapio = new ArrayList<Pizza>();

        while(resultado.next()) {
                
                cod_pizza = resultado.getInt(1);
                nome = resultado.getString(2);
                ingredientes = resultado.getString(3);
                preco = resultado.getDouble(4);
                
                Pizza pizza = new Pizza(cod_pizza, nome, ingredientes, preco);
                
                cardapio.add(pizza);
        }

        return cardapio;

}

public Pizza recuperarPizza(String nome) throws SQLException {
        
        String sql = "SELECT cod_pizza, ingredientes, preco FROM pizza WHERE nome = '" + nome + "'";
        
        PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql);
        
        ResultSet resultado = pstm.executeQuery();
        
        if (resultado.next()) {
                
                
                int cod_pizza = resultado.getInt(1);
                String ingredientes = resultado.getString(2);
                double preco = resultado.getDouble(3);
                
                Pizza pizza = new Pizza(cod_pizza, nome, ingredientes, preco);
                
                return pizza;
                
        }

        return null;
}

}
