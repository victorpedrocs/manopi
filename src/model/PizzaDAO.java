package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import controller.ConnectionFactory;

public class PizzaDAO {
	
	//private Connection connection;
	
	public PizzaDAO() {
		
	}
	
	public Collection<Pizza> retrieve(Pizza pizza){
		Statement statement;
		ResultSet result;
		
		try {
			statement = ConnectionFactory.getConnection().createStatement();
			StringBuilder sql = new StringBuilder();
			
			Integer codigo = pizza.getCodigo();
			String nome = pizza.getNome();
			String ingredientes = pizza.getIngredientes();
			Double preco = pizza.getPreco();
			
			sql.append("SELECT * FROM pizza WHERE 1=1 ");
			
			if (codigo != null) {
				sql.append("AND codigo = ").append(codigo);
			}
			if (nome != null) {
				sql.append("AND nome = '").append(nome).append("'");
			}
			if (ingredientes != null) {
				sql.append("AND ingredientes = '").append(ingredientes).append("'");
			}
			if (preco != null) {
				sql.append("AND preco = ").append(ingredientes);
			}
			
			result = statement.executeQuery(sql.toString());
			
			ArrayList<Pizza> pizzas = new ArrayList<>();
			while (result.next()) {
				pizzas.add(new Pizza(result.getInt("CODIGO"), result.getString("NOME"), result.getString("INGREDIENTES"), result.getDouble("PRECO")));
			}
			
			return pizzas;
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage()));
			return null;
		}
		finally {
			try {
				ConnectionFactory.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
        
        String sql = "SELECT codigo, ingredientes, preco FROM pizza WHERE nome = '" + nome + "'";
        
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
