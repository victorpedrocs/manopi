package pedido.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pedido.model.Pagamento;
import pedido.model.PagamentoDAO;
import pedido.model.Pedido;
import pedido.model.PedidoDAO;
import pedido.model.PedidoPizza;
import pedido.model.PedidoPizzaDAO;
import util.ConnectionFactory;
import cliente.controller.LoginControle;
import cliente.model.Cliente;

public class PedidoControle {
	
	PedidoDAO pedidoDAO;
	PagamentoDAO pagamentoDAO;
	PedidoPizzaDAO pedidoPizzaDAO;
	LoginControle loginControle;
    
    public PedidoControle() {
    	this.pedidoDAO = new PedidoDAO(ConnectionFactory.getConnection());
        this.pagamentoDAO = new PagamentoDAO(ConnectionFactory.getConnection());
        this.pedidoPizzaDAO = new PedidoPizzaDAO(ConnectionFactory.getConnection());
        this.loginControle = new LoginControle();
    }
    
    public Pagamento recuperarFormaPagamento(Pagamento pagamento) {
    	return this.pagamentoDAO.retrieve(pagamento).iterator().next();
    }
    
    public ArrayList<Pagamento> listarFormaDePagamento() throws SQLException {
          
    	ArrayList<Pagamento> formasDePagamento = (ArrayList<Pagamento>) pagamentoDAO.retrieve(new Pagamento(null, null));
    	return formasDePagamento;
    }
    
    public ArrayList<Pedido> listarPedidos() {
    		
            Cliente cliente = loginControle.retornaClienteLogado();
            
            ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
            
            Collection<Pedido> pedidosTeste= pedidoDAO.retrieve(new Pedido(null, cliente, null, null)); 
            if(pedidosTeste != null){
            	pedidos.addAll(pedidosTeste);
            	return pedidos;
            }
            
            return null;
    }
    
    public boolean fecharPedido(Pedido pedido, Pagamento pagamento, Collection<PedidoPizza> itensPedido, Double valorTroco){
    	pedido.alterarFormaDePagamento(pagamento);
    	pedido.atualizarTotalPago(valorTroco);
    	
    	Boolean pedidoSucesso = this.pedidoDAO.create(pedido);
    	Boolean itemPedidoSucesso = true;
    	for (PedidoPizza pedidoPizza : itensPedido) {
			itemPedidoSucesso = itemPedidoSucesso && this.pedidoPizzaDAO.create(pedidoPizza);
		}
    	return pedidoSucesso && itemPedidoSucesso;
    }
    
    public Map<Pedido, ArrayList<PedidoPizza>> recuperarPedidosCliente(Cliente cliente){
    	this.pedidoDAO = new PedidoDAO(ConnectionFactory.getConnection());
    	
    	PedidoDAO pedidoDAO = new PedidoDAO(ConnectionFactory.getConnection());
    	PedidoPizzaDAO pedidoPizzaDAO = new PedidoPizzaDAO(ConnectionFactory.getConnection());
    	
    	
    	Pedido pedidoQuery = new Pedido(null, cliente, null, null);
    	ArrayList<Pedido> pedidos = (ArrayList<Pedido>) pedidoDAO.retrieve(pedidoQuery);
    	HashMap<Pedido, ArrayList<PedidoPizza>> historicoPedido = new HashMap<Pedido, ArrayList<PedidoPizza>>();
    	
    	
    	for (Pedido pedido : pedidos) {
			PedidoPizza itemPedidoQuery = new PedidoPizza(null, pedido, null);
			ArrayList<PedidoPizza> itensPedido = (ArrayList<PedidoPizza>) pedidoPizzaDAO.retrieve(itemPedidoQuery);
			historicoPedido.put(pedido, itensPedido);
		}
    	
    	return historicoPedido;
    }

}
