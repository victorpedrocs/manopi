package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import model.Cliente;
import model.HistoricoPedido;
import model.Pagamento;
import model.PagamentoDAO;
import model.Pedido;
import model.PedidoDAO;
import model.PedidoPizza;
import model.PedidoPizzaDAO;

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


}
