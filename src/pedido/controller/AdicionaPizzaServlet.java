package pedido.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import pedido.model.ItemPedido;
import pedido.model.Pedido;
import pedido.model.PedidoPizza;
import util.ConnectionFactory;
import cardapio.model.Pizza;
import cardapio.model.PizzaDAO;
import cliente.model.Cliente;

/**
 * Servlet implementation class AdicionaPizzaServlet
 */
@WebServlet("/AdicionaPizzaServlet")
public class AdicionaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionaPizzaServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
		/*
		 * Atributos da sessão
		 * Pedido
		 * Itens
		 * clienteLogado
		 * detalhesPizza
		 * valorTotal
		 * */
        String sabor = request.getParameter("nomePizza");
        String quantidadeString = request.getParameter("quantidade");
        HttpSession session = request.getSession(true);
        
        if (quantidadeString != null && quantidadeString != "") {
        	Integer quantidade = Integer.parseInt(quantidadeString );

            PizzaDAO pizzaDAO = new PizzaDAO(ConnectionFactory.getConnection());
            
            try {
                    
        		Pedido pedido = (Pedido) session.getAttribute("pedido");
        		Pizza pizza  = pizzaDAO.retrieve(new Pizza(sabor, null)).iterator().next();
        		PedidoPizza itemPedido;
        		
        		if (pedido != null) {
        			itemPedido = new PedidoPizza(pizza, pedido, quantidade);
    			}
        		else {
        			Cliente cliente = (Cliente) session.getAttribute("clienteLogado");
        			pedido = new Pedido(cliente, null);
        			session.setAttribute("pedido", pedido);
        			itemPedido = new PedidoPizza(pizza, pedido, quantidade);
        		}
                
                
                if (session.getAttribute("itens") == null) {
                        
            		ArrayList<PedidoPizza> itens = new ArrayList<PedidoPizza>();
                    
                    itens.add(itemPedido);
                    
                    session.setAttribute("itens", itens);
                        
    	        } else {
    	              
    	            ArrayList<PedidoPizza> itens = (ArrayList<PedidoPizza>) session.getAttribute("itens");
    	            
    	            itens.add(itemPedido);
    	            
    	            session.setAttribute("itens", itens);
                        
                }
                    
            } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
		}        
        
        ArrayList<PedidoPizza> itens = (ArrayList<PedidoPizza>) session.getAttribute("itens");
        
        double valorTotal = 0;
        if (itens != null) {
        	for (PedidoPizza item : itens) {
                valorTotal += item.calculaTotal();
            }
		}
        
        session.setAttribute("valorTotal", valorTotal);
        
        RequestDispatcher rd = request.getRequestDispatcher("/pedidos.jsp");
        
        rd.forward(request,response);
	}
}


