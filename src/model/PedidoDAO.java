package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import controller.ConnectionFactory;

public class PedidoDAO {

	//private Connection connection;
	
	public PedidoDAO() {
		
	}
	
	public Collection<Pedido> retrieve(Pedido pedido){
		Statement statement;
		ResultSet result;
		
		try {
			statement = ConnectionFactory.getConnection().createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT codigo, cliente_fk, forma_de_pagamento_fk FROM cliente WHERE 1=1 ");
			
			Integer codigo = pedido.getCodigo();
			Cliente cliente = pedido.getCliente();
			Pagamento formaPagamento = pedido.getFormaPagamento();
			
			if (codigo != null) {
				sql.append("AND codigo = ").append(codigo);
			}
			if (cliente != null && cliente.getCodigo() != null) {
				sql.append("AND cliente_fk = ").append(cliente.getCodigo());
			}
			if (formaPagamento != null && formaPagamento.getCodigo() != null) {
				sql.append("AND forma_de_pagamento_fk = ").append(pedido);
			}
			
			sql.append("ORDER BY data_hora DESC");
			
			result = statement.executeQuery(sql.toString());
			
			
			ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
			while (result.next()) {
				pedidos.add(new Pedido(result.getInt("CODIGO"), new Cliente(result.getInt("CLIENTE_FK"), null, null, null, null) , new Pagamento(result.getInt("FORMA_DE_PAGAMENTO_FK"), null)));
			}
			
			return pedidos;
			
			
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
	
	public boolean create(Pedido pedido){
		Statement statement;
		
		try {
			
			statement = ConnectionFactory.getConnection().createStatement();
			StringBuilder sql = new StringBuilder();
			if (pedido.validFields()) {
				sql.append("INSERT INTO pedido (cliente_fk, forma_de_pagamento_fk, data_hora) VALUES(")
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
		finally {
			try {
				ConnectionFactory.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public void CadastrarNovoItem(ItemPedido item) {

        String sql = "INSERT INTO pedido_pizza (cod_pizza, cod_pedido, quantidade) values(?, ?, ?)";

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

        String sql = "DELETE FROM pedido WHERE cod_pedido = ?";

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

		public Collection<HistoricoPedido> recuperarPedidos(Cliente cliente) {

        String sql = "SELECT "
                        + "pedido.cod_pedido, "
                        + "pedido.data_pedido, "
                        + "pizza.nome, "
                        + "pizza.preco, "
                        + "item_pedido.quantidade, "
                        + "pedido.valor_total, "
                        + "pedido.valor_troco, "
                        + "pagamento.tipo_pagamento "
                        + "FROM pedido "
                        + "JOIN item_pedido "
                        + "ON pedido.cod_pedido = item_pedido.cod_pedido "
                        + "JOIN pizza "
                        + "ON item_pedido.cod_pizza = pizza.cod_pizza "
                        + "JOIN pagamento "
                        + "ON pedido.cod_pagamento = pagamento.cod_pagamento "
                        + "WHERE pedido.cpf_cliente = ? "
                        + "ORDER BY pedido.data_pedido";

        PreparedStatement pstm = null;
        
        HashMap<Integer, HistoricoPedido> pedidos = new HashMap<>();

        try {

                pstm = ConnectionFactory.getConnection().prepareStatement(sql);

                pstm.setString(1, ""+cliente.getCodigo());

                ResultSet resultado = pstm.executeQuery();

                while (resultado.next()) {
                        
                        int cod_pedido = resultado.getInt(1);
                        
                        if (pedidos.containsKey(cod_pedido)) {
                                
                                HistoricoPedido temp = pedidos.get(cod_pedido);
                                
                                String nome_pizza = resultado.getString(3); 
                                double valor_pizza = resultado.getDouble(4);
                                int quantidade = resultado.getInt(5); 
                                
                                temp.adicionarNovoItem(nome_pizza, valor_pizza, quantidade);
                                
                        } else {
                                
                                Timestamp data_pedido = resultado.getTimestamp(2);
                                String nome_pizza = resultado.getString(3); 
                                double valor_pizza = resultado.getDouble(4);
                                int quantidade = resultado.getInt(5);
                                double valor_total = resultado.getDouble(6);
                                String pagamento = resultado.getString(8);
                                
                                HistoricoPedido temp = new HistoricoPedido(data_pedido, valor_total, pagamento);
                                
                                temp.adicionarNovoItem(nome_pizza, valor_pizza, quantidade);
                                
                                pedidos.put(cod_pedido, temp);
                                
                        }
                }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        return pedidos.values();

}

		public ArrayList<Pagamento> listarFormaDePagamento() throws SQLException {
        
        String sql = "SELECT * FROM pagamento";

        PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql);

        ResultSet resultado = pstm.executeQuery();

        int codPagamento;
        String tipoPagamento;
        
        ArrayList<Pagamento> formasDePagamento = new ArrayList<Pagamento>();

        while(resultado.next()) {
                
                codPagamento = resultado.getInt(1);
                tipoPagamento = resultado.getString(2);
                
                Pagamento pagamento = new Pagamento(codPagamento, tipoPagamento);
                
                formasDePagamento.add(pagamento);
        }

        return formasDePagamento;
}

		public Pagamento recuperarPagamento(String tipo_pagamento) {
        
        String sql = "SELECT cod_pagamento FROM pagamento WHERE tipo_pagamento = ?";

        PreparedStatement pstm = null;
        
        try {
                
                pstm = ConnectionFactory.getConnection().prepareStatement(sql);
                
                pstm.setString(1, tipo_pagamento);

                ResultSet resultado = pstm.executeQuery();

                if(resultado.next()) {
                        
                        int cod_pagamento = resultado.getInt(1);
                        
                        Pagamento pagamento = new Pagamento(cod_pagamento, tipo_pagamento);
                        
                        return pagamento;
                }
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        return null;
}

}
