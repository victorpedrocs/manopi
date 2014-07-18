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
		Statement statement;
		ResultSet result;
		
		try {
			statement = this.connection.createStatement();
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
			
			result = statement.executeQuery(sql.toString());
			
			
			ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
			ClienteDAO clienteDAO = new ClienteDAO(ConnectionFactory.getConnection());
			while (result.next()) {
				Cliente clienteResultado = clienteDAO.retrieve(new Cliente(result.getInt("CLIENTE_FK"), null, null, null));
				Pagamento pagamentoResultado = new PagamentoDAO(this.connection).retrieve(new Pagamento(result.getInt("FORMA_DE_PAGAMENTO_FK"), null)).iterator().next();
				pedidos.add(new Pedido(result.getString("CODIGO"), clienteResultado , pagamentoResultado, result.getDouble("TOTAL_PAGO")));
			}
			
			return pedidos;
			
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage()));
			return null;
		}
		/*finally {
			try {
				ConnectionFactory.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	public boolean create(Pedido pedido){
		Statement statement;
		
		try {
			
			statement = ConnectionFactory.getConnection().createStatement();
			StringBuilder sql = new StringBuilder();
			if (pedido.validFields()) {
				sql.append("INSERT INTO pedido (codigo, cliente_fk, forma_de_pagamento_fk, data_hora) VALUES(")
					.append("'").append(pedido.getCodigo()).append("',")
					.append(pedido.getCliente().getCodigo()).append(",")
					.append(pedido.getFormaPagamento().getCodigo()).append(",")
					.append("current_timestamp)");
			}
			
			
			statement.executeUpdate(sql.toString());
			statement.close();
			return true;
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage())); e.printStackTrace();
			return false;
		}
		/*finally {
			try {
				ConnectionFactory.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}

	
	public void CadastrarNovoItem(ItemPedido item) {

        String sql = "INSERT INTO pedido_pizza (pizza_fk, pedido_fk, quantidade) values(?, ?, ?)";

        PreparedStatement pstm = null;

        try {

                pstm = ConnectionFactory.getConnection().prepareStatement(sql);

                pstm.setInt(1, item.getCod_pizza());
                pstm.setInt(2, item.getCod_pedido());
                pstm.setInt(3, item.getQuantidade());

                pstm.executeUpdate();

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
	}

		public void finalizarPedido(int codPedido, double valorTotal) {

        String sql = "UPDATE pedido SET 'valor_total' = ? WHERE cod_pedido = ?";

        PreparedStatement pstm = null;

        try {

                pstm = ConnectionFactory.getConnection().prepareStatement(sql);

                pstm.setDouble(1, valorTotal);
                pstm.setInt(2, codPedido);

                pstm.executeUpdate();

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
}

		public void cancelarPedido(int codPedido) {

	        String sql = "DELETE FROM pedido WHERE codigo = ?";
	
	        PreparedStatement pstm = null;
	
	        try {
	
	                pstm = ConnectionFactory.getConnection().prepareStatement(sql);
	
	                pstm.setDouble(1, codPedido);
	
	                pstm.executeUpdate();
	 
	        } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	        }

		}
		

		

}
