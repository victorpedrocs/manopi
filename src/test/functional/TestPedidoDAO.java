package test.functional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import pedido.model.Pagamento;
import pedido.model.PagamentoDAO;
import pedido.model.Pedido;
import pedido.model.PedidoDAO;
import util.ConnectionFactory;
import cliente.model.Cliente;
import cliente.model.ClienteDAO;

public class TestPedidoDAO {
	private static Cliente cliente;
	private static Pagamento pagamento;
	private static String testeCreatePk;
	private static Pedido pedido;
	public static Connection con;
	
	@BeforeClass
	public static void beforeTests() throws SQLException{
		
		con = ConnectionFactory.getConnection();
		
		cliente = new ClienteDAO(con).retrieve(new Cliente(null, null, null, null, null, null));
		pagamento = new PagamentoDAO(con).retrieve(new Pagamento(null, null)).iterator().next();
		pedido = new Pedido(cliente,pagamento);
		
		String sql = "INSERT INTO pedido (codigo, cliente_fk, forma_de_pagamento_fk, data_hora) values (?,?,?,?)";
		PreparedStatement statement = con.prepareStatement(sql);
		
		statement.setString(1, pedido.getCodigo());
		statement.setInt(2, cliente.getCodigo());
		statement.setInt(3, pagamento.getCodigo());
		statement.setTimestamp(4, pedido.getDataHora());
		
		statement.executeUpdate();
		
	}

	@Test
	public static void testPedidoCreate() throws SQLException{
		
		Cliente clienteCreate = new Cliente(null, "teste", "teste", "teste", "teste", "teste");
		Pagamento pagamentoCreate = new Pagamento(null, "Dinheiro");
		Pedido pedidoTest = new Pedido(clienteCreate, pagamentoCreate);
		new PedidoDAO(con).create(pedidoTest);
		
		String sql = "SELECT * FROM pedido WHERE codigo = ?";
		PreparedStatement pStatement = con.prepareStatement(sql);
		pStatement.setString(1, pedidoTest.getCodigo());
		ResultSet result = pStatement.executeQuery();
		
		
		assertEquals(true, result.next());
		
	}
	
	public static void testPedidoRetrieve(){
		
		Pedido pedidoBanco = new PedidoDAO(con).retrieve(pedido).iterator().next();
		
		assertEquals(pedidoBanco.getCodigo(), pedido.getCodigo());
		
	}
}
