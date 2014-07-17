package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import model.Cliente;
import model.HistoricoPedido;
import model.Pagamento;
import model.PagamentoDAO;
import model.Pedido;
import model.PedidoDAO;

public class PedidoControle {
	
	PedidoDAO pedidoDAO;
	PagamentoDAO pagamentoDAO;
	LoginControle loginControle;
    
    public PedidoControle() {
    	this.pedidoDAO = new PedidoDAO();
        this.pagamentoDAO = new PagamentoDAO(ConnectionFactory.getConnection());
        this.loginControle = new LoginControle();
    }
    
    public ArrayList<Pagamento> listarFormaDePagamento() throws SQLException {
          
    	ArrayList<Pagamento> formasDePagamento = (ArrayList<Pagamento>) pagamentoDAO.retrieve(new Pagamento(null, null));
    	return formasDePagamento;
    }
    
    public ArrayList<Pedido> listarPedidos() {
    		
            Cliente cliente = loginControle.retornaClienteLogado();
            
            ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
            
            
            if(pedidoDAO.retrieve(new Pedido(null, cliente, null)) != null){
            	pedidos.addAll(pedidoDAO.retrieve(new Pedido(null, cliente, null)));
            	return pedidos;
            }
            
            return null;
    }


}
