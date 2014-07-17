package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import controller.ConnectionFactory;

public class PagamentoDAO {
	private Connection connection;
	
	public PagamentoDAO(Connection con) {
		this.connection = con;
	}
	
	public Pagamento recuperarPagamento(String tipo_pagamento) {
        
        String sql = "SELECT codigo FROM forma_de_pagamento WHERE nome = ?";

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
	
	public Collection<Pagamento> retrieve(Pagamento pagamento){
		Statement statement;
		ResultSet result;
		
		try {
			statement = this.connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT codigo, nome FROM forma_de_pagamento WHERE 1=1 ");
			
			Integer codigo = pagamento.getCodigo();
			String formaPagamento = pagamento.getFormaDePagamento();
			
			if (codigo != null) {
				sql.append("AND codigo = ").append(codigo);
			}
			if (formaPagamento != null && formaPagamento != "") {
				sql.append("AND nome = '").append(formaPagamento).append("'");
			}
			
			result = statement.executeQuery(sql.toString());
			
			ArrayList<Pagamento> pagamentos = new ArrayList<>();
			while (result.next()) {
				pagamentos.add(new Pagamento(result.getInt("CODIGO"), result.getString("nome")));
			}
			
			return pagamentos;
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage()));
			return null;
		}/*
		finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}
}
