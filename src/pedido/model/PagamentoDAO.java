package pedido.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import util.ConnectionFactory;

public class PagamentoDAO {
	private Connection connection;
	
	public PagamentoDAO(Connection con) {
		this.connection = con;
	}
	
	public Collection<Pagamento> retrieve(Pagamento pagamento){
		
		
		try (Statement statement = this.connection.createStatement()){
			ResultSet result;
			
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
		}
	}
}
