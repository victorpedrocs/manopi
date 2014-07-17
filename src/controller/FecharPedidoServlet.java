package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import model.ItemPedido;
import model.Pagamento;
import model.PagamentoDAO;
import model.Pedido;
import model.PedidoDAO;
import model.PedidoPizza;

/**
 * Servlet implementation class FecharPedidoServlet
 */
@WebServlet("/FecharPedidoServlet")
public class FecharPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FecharPedidoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	// TODO Fazer Esse método funcionar//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		PedidoControle pedidoControle = new PedidoControle();
        
		//Recupera variáveis de sessão
        Cliente clienteLogado = (Cliente) session.getAttribute("clienteLogado");
        Double valorTotal = (Double) session.getAttribute("valorTotal");
        Pedido pedido = (Pedido) session.getAttribute("pedido");
        Collection<PedidoPizza> itensPedido = (Collection<PedidoPizza>) session.getAttribute("itens");
        
        //Recupera variáveis do form
        Integer formaPagamento = Integer.parseInt(request.getParameter("formaDePagamento"));
        Double valorTroco = Double.parseDouble(request.getParameter("valorTroco"));
        
        // TODO Pegar a forma de pagamento e o troco para setar na sessao;
        Pagamento pagamento = pedidoControle.recuperarFormaPagamento( new Pagamento(formaPagamento, null));
        pedidoControle.fecharPedido(pedido, pagamento, itensPedido, valorTroco);
        
        
        
        
	}

}
