package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PagamentoDAO {
	private Connection connection;
	
	public PagamentoDAO(Connection con) {
		this.connection = con;
	}
	
	public Pagamento retrieve(Pagamento pagamento){
		Statement statement;
		ResultSet result;
		
		try {
			statement = this.connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT codigo, nome FROM cliente WHERE 1=1 ");
			
			Integer codigo = pagamento.getCodigo();
			String formaPagamento = pagamento.getFormaDePagamento();
			
			if (codigo != null) {
				sql.append("AND codigo = ").append(codigo);
			}
			if (formaPagamento != null && formaPagamento != "") {
				sql.append("AND nome = '").append(formaPagamento).append("'");
			}
			
			result = statement.executeQuery(sql.toString());
			
			if (result.next()) {
				return new Pagamento(Integer.parseInt(result.getString("CODIGO")), result.getString("FORMA_DE_PAGAMENTO"));
			}
			
			else return null;		
			
			
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
