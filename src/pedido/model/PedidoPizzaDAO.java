package pedido.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import cardapio.model.Pizza;
import cardapio.model.PizzaDAO;
import util.ConnectionFactory;

public class PedidoPizzaDAO {
	
	private Connection connection;
	
	public PedidoPizzaDAO(Connection con) {
		this.connection = con;
	}
	
	public Collection<PedidoPizza> retrieve(PedidoPizza pedidoPizza){
		ResultSet result;
		
		PedidoDAO pedidoDAO = new PedidoDAO(ConnectionFactory.getConnection());
		PizzaDAO pizzaDAO = new PizzaDAO(ConnectionFactory.getConnection());
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT pizza_fk, pedido_fk, quantidade FROM pedido_pizza WHERE 1=1 ");
		
		Pedido pedido = pedidoPizza.getPedido();
		Pizza pizza = pedidoPizza.getPizza();
		
		if (pedido != null && pedido.getCodigo() != null) {
			sql.append("AND pedido_fk = '").append(pedido.getCodigo()).append("'");
		}
		if (pizza != null && pizza.getCodigo() != null) {
			sql.append("AND pizza_fk = ").append(pizza.getCodigo());
		}
		
		try (Statement statement = this.connection.createStatement()){
			
			result = statement.executeQuery(sql.toString());
			
			ArrayList<PedidoPizza> itensPedido = new ArrayList<>();
			while (result.next()) {
				pizza = pizzaDAO.retrieve( new Pizza(result.getInt("PIZZA_FK"), null, null, null)).iterator().next();
				pedido = pedidoDAO.retrieve( new Pedido(result.getString("PEDIDO_FK"), null, null, null)).iterator().next();
				
				itensPedido.add(new PedidoPizza(pizza, pedido, result.getInt("QUANTIDADE")));
			}
			
			return itensPedido;
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage()));
			return null;
		}
	}
	
	public boolean create(PedidoPizza itemPedido){
		
		try (Statement statement = this.connection.createStatement()){
			
			
			StringBuilder sql = new StringBuilder();
			if (itemPedido.validFields()) {
				sql.append("INSERT INTO pedido_pizza (pizza_fk, pedido_fk, quantidade) VALUES(")
					.append(itemPedido.getPizza().getCodigo()).append(",")
					.append("'").append(itemPedido.getPedido().getCodigo()).append("',")
					.append(itemPedido.getQuantidade()).append(")");
			}
			
			
			statement.executeUpdate(sql.toString());
			statement.close();
			return true;
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage())); e.printStackTrace();
			return false;
		}
	}
	
	

}
