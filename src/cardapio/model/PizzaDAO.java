package cardapio.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import util.ConnectionFactory;

public class PizzaDAO {
	
	private Connection connection;
	
	public PizzaDAO(Connection con) {
		this.connection = con;
	}
	
	public Collection<Pizza> retrieve(Pizza pizza){
		
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
		
		try ( Statement statement = this.connection.createStatement();){
			
			ResultSet result;
			
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
	}

}
