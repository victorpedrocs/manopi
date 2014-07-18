package cliente.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.ConnectionFactory;

public class ClienteDAO {

	private Connection connection;
	
	public ClienteDAO(Connection con){
		this.connection = con;
	}
	
	public Cliente retrieve(Cliente cliente){
		Statement statement;
		ResultSet result;
		
		try {
			statement = this.connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT codigo, telefone, nome, endereco FROM cliente WHERE 1=1 ");
			
			Integer codigo = cliente.getCodigo();
			String login = cliente.getLogin();
			String senha = cliente.getSenha();
			String telefone = cliente.getTelefone();
			String nome = cliente.getNome();
			String endereco = cliente.getEndereco();
			
			
			if (codigo != null) {
				sql.append("AND codigo = ").append(codigo);
			}
			if (login != null && login != "") {
				sql.append("AND login = '").append(login).append("' ");
			}
			if (senha != null && senha != "") {
				sql.append("AND senha = '").append(senha).append("' ");
			}
			if (telefone != null && telefone != "") {
				sql.append("AND telefone = '").append(telefone).append("' ");
			}
			if (nome != null && nome != "") {
				sql.append("AND nome = '").append(nome).append("' ");
			}
			if (endereco != null && endereco != "") {
				sql.append("AND endereco = '").append(endereco).append("' ");
			}
			
			result = statement.executeQuery(sql.toString());
			if (result.next()) {
				return new Cliente(Integer.parseInt(result.getString("codigo")),  result.getString("NOME"), result.getString("TELEFONE"), result.getString("ENDERECO"));
			}
			
			else return null;		
			
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage()));
			return null;
		}/*
		finally {
			try {
				ConnectionFactory.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	public boolean create(Cliente cliente){
		
		Statement statement;
		
		try {
			
			statement = ConnectionFactory.getConnection().createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cliente (login, senha, nome, telefone, endereco) VALUES(")
				.append("'").append(cliente.getLogin()).append("',")
				.append("'").append(cliente.getSenha()).append("',")
				.append("'").append(cliente.getNome()).append("',")
				.append("'").append(cliente.getTelefone()).append("',")
				.append("'").append(cliente.getEndereco()).append("')");
			
			statement.executeUpdate(sql.toString());
			statement.close();
			return true;
			
		} catch (SQLException e) {
			System.err.println("ERRO de SQL, tente novamente");
			System.err.println(new StringBuilder("Motivo: ").append(e.getMessage())); e.printStackTrace();
			return false;
		}/*
		finally {
			try {
				ConnectionFactory.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}
}
