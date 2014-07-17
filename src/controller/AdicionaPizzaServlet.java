package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemPedido;
import model.Pizza;
import model.PizzaDAO;

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
        
        String sabor = request.getParameter("sabor");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        
        PizzaDAO pizzaDAO = new PizzaDAO();
        
        HttpSession sessao = request.getSession(true);
        
        try {
                
                Pizza pizza  = pizzaDAO.recuperarPizza(sabor);
                ItemPedido item = new ItemPedido(pizza, quantidade);
                
                if (sessao.getAttribute("itens") == null) {
                        
                        HashMap<String, ItemPedido> itens = new HashMap<>();
                        
                        itens.put(sabor, item);
                        
                        sessao.setAttribute("itens", itens);
                        
                } else {
                        
                        @SuppressWarnings("unchecked")
                        HashMap<String, ItemPedido> itens = (HashMap<String, ItemPedido>) sessao.getAttribute("itens");
                        
                        itens.put(sabor, item);
                        
                        sessao.setAttribute("itens", itens);
                        
                }
                
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        
        
        @SuppressWarnings("unchecked")
        HashMap<String, ItemPedido> itens = (HashMap<String, ItemPedido>) sessao.getAttribute("itens");
        
        String detalhesPizza = "";
        double valorTotal = 0;
        
        for (ItemPedido item : itens.values()) {
                
                String imgRemover = "<form action='removePizzaPedido'><input type='hidden' name='nomePizzaRemover' value='" + item.getNome_pizza() + "'><input type='image' src='images/delete.png' alt='Remover'></form>";
                
                detalhesPizza += "<tr><td>Sabor: " + item.getNome_pizza() + " (" + item.getQuantidade() + ") - R$ " + item.getTotal() + "</td><td>" + imgRemover + "</td></tr>";
                valorTotal += item.getTotal();
                
        }
        
        sessao.setAttribute("detalhesPizza", detalhesPizza);
        sessao.setAttribute("valorTotal", valorTotal);
        
        RequestDispatcher rd = request.getRequestDispatcher("/pedidos.jsp");
        
        rd.forward(request,response);
}
}


