package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import model.Cliente;
import model.HistoricoPedido;
import model.Pagamento;
import model.PedidoDAO;

public class PedidoControle {
	
	PedidoDAO pDAO;
    
    public PedidoControle() {
            this.pDAO = new PedidoDAO();
    }
    
    public ArrayList<Pagamento> listarFormaDePagamento() throws SQLException {
           
    	ArrayList<Pagamento> formasDePagamento = pDAO.listarFormaDePagamento();
            
            return formasDePagamento;
    }
    
    public ArrayList<HistoricoPedido> listarPedidos(HttpSession sessao) {
            Cliente cliente = (Cliente) sessao.getAttribute("cliente_logado");
            
            ArrayList<HistoricoPedido> pedidos = new ArrayList<HistoricoPedido>();
            pedidos.addAll(pDAO.recuperarPedidos(cliente));
            
            Collections.sort(pedidos);
            
            return pedidos;
    }


}
