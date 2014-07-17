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
	
	//Fazer Esse método funcionar//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(true);
        
        Cliente clienteLogado = (Cliente) sessao.getAttribute("clienteLogado");
        
        double valorTotal = (double) sessao.getAttribute("valorTotal");
        
        // TODO Pegar a forma de pagamento e o troco para setar na sessao;
        
        PedidoDAO pDAO = new PedidoDAO();
        PagamentoDAO pagamentoDao = new PagamentoDAO(ConnectionFactory.getConnection());
        
        //Pagamento p = new Pagamento(null,request.getParameter("formaDePagamento"));
        double valorTroco = 0;
        
        if(request.getParameter("valorTroco") != "") {
                valorTroco = Double.parseDouble(request.getParameter("valorTroco"));
        }
        
        /*GAMBIARRA*/
        
       /* Pagamento pagamento = pDAO.recuperarPagamento(tipo_pagamento);
        
        Pedido novo_pedido = new Pedido(clienteLogado, pagamento, valorTroco, valor_total);

        int cod_pedido = pDAO.CadastrarNovoPedido(novo_pedido);
        
        HashMap<String, ItemPedido> itens = (HashMap<String, ItemPedido>) sessao.getAttribute("itens");
        
        for (ItemPedido item : itens.values()) {
                item.setCod_pedido(cod_pedido);
                pDAO.CadastrarNovoItem(item);
        }
        
        sessao.removeAttribute("itens");
        sessao.removeAttribute("detalhesPizza");
        sessao.removeAttribute("valorTotal");
        
        RequestDispatcher rd = request.getRequestDispatcher("/pedidos.jsp");
        
        rd.forward(request,response);

	}*/
        
	}

}
