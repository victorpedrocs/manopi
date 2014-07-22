package pedido.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import util.ConnectionFactory;
import cliente.model.Cliente;
import cliente.model.ClienteDAO;

public class PedidoDAO {

	private Connection connection;
	
	public PedidoDAO(Connection con) {
		this.connection = con;
	}
	
	public Collection<Pedido> retrieve(Pedido pedido){
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, cliente_fk, forma_de_pagamento_fk, total_pago, data_hora FROM pedido WHERE 1=1 ");
		
		String codigo = pedido.getCodigo();
		Cliente cliente = pedido.getCliente();
		Pagamento formaPagamento = pedido.getFormaPagamento();
		Double totalPago = pedido.getTotalPago();
		
		if (codigo != null) {
			sql.append("AND codigo = '").append(codigo).append("'");
		}
		if (cliente != null && cliente.getCodigo() != null) {
			sql.append("AND cliente_fk = ").append(cliente.getCodigo());
		}
		if (formaPagamento != null && formaPagamento.getCodigo() != null) {
			sql.append("AND forma_de_pagamento_fk = ").append(formaPagamento.getCodigo());
		}
		if (totalPago != null) {
			sql.append("AND total_pago = ").append(totalPago);
		}
		
		sql.append("ORDER BY data_hora DESC");
		
		try (Statement statement = this.connection.createStatement();
				ResultSet result = statement.executeQuery(sql.toString())){
			
			ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
			ClienteDAO clienteDAO = new ClienteDAO(ConnectionFactory.getConnection());
			
			while (result.next()) {
				Cliente clienteResultado = clienteDAO.retrieve(new Cliente(result.getInt("CLIENTE_FK"), null, null, null));
				Pagamento pagamentoResultado = new PagamentoDAO(this.connection).retrieve(new Pagamento(result.getInt("FORMA_DE_PAGAMENTO_FK"), null)).iterator().next();
				pedidos.add( new Pedido( result.getString("CODIGO"), clienteResultado , pagamentoResultado, result.getDouble("TOTAL_PAGO"), result.getTimestamp("DATA_HORA") ) );
			}
			
			return pedidos;
			
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage()));
			return null;
		}
	}
	
	public boolean create(Pedido pedido){
		
		try (Statement statement = ConnectionFactory.getConnection().createStatement()){
			
			StringBuilder sql = new StringBuilder();
			if (pedido.validFields()) {
				sql.append("INSERT INTO pedido (codigo, cliente_fk, forma_de_pagamento_fk, data_hora) VALUES(")
					.append("'").append(pedido.getCodigo()).append("',")
					.append(pedido.getCliente().getCodigo()).append(",")
					.append(pedido.getFormaPagamento().getCodigo()).append(",")
					.append("current_timestamp)");
			}
			
			
			statement.executeUpdate(sql.toString());
			return true;
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage())); e.printStackTrace();
			return false;
		}
	}
		

		

}
