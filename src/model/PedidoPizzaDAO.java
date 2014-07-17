package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import controller.ConnectionFactory;

public class PedidoPizzaDAO {
	
	private Connection connection;
	
	public PedidoPizzaDAO(Connection con) {
		this.connection = con;
	}
	
	public Collection<PedidoPizza> retrieve(PedidoPizza pedidoPizza){
		Statement statement;
		ResultSet result;
		
		PedidoDAO pedidoDAO = new PedidoDAO();
		PizzaDAO pizzaDAO = new PizzaDAO();
		
		try {
			statement = this.connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT pizza_fk, pedido_fk, quantidade FROM cliente WHERE 1=1 ");
			
			Pedido pedido = pedidoPizza.getPedido();
			Pizza pizza = pedidoPizza.getPizza();
			
			if (pedido != null && pedido.getCodigo() != null) {
				sql.append("AND codigo = ").append(pedido.getCodigo());
			}
			if (pizza != null && pizza.getCodigo() != null) {
				sql.append("AND pizza_fk = ").append(pizza.getCodigo());
			}
			
			
			result = statement.executeQuery(sql.toString());
			
			ArrayList<PedidoPizza> itensPedido = new ArrayList<>();
			while (result.next()) {
				pizza = pizzaDAO.retrieve( new Pizza(result.getInt("PIZZA_FK"), null, null, null)).iterator().next();
				pedido = pedidoDAO.retrieve( new Pedido(result.getInt("PEDIDO_FK"), null, null)).iterator().next();
				
				itensPedido.add(new PedidoPizza(pizza, pedido, result.getInt("QUANTIDADE")));
			}
			
			return itensPedido;
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage()));
			return null;
		}
		finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
