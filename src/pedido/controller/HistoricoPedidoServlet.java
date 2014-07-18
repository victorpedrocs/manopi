package pedido.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pedido.model.Pedido;
import pedido.model.PedidoPizza;
import cliente.model.Cliente;

/**
 * Servlet implementation class HistoricoPedidoServelet
 */
@WebServlet("/HistoricoPedido")
public class HistoricoPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoricoPedidoServlet() {
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
		
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		PedidoControle pedidoControle = new PedidoControle();
		
		Cliente cliente = (Cliente) session.getAttribute("clienteLogado");
		if (cliente != null) {
			HashMap<Pedido, ArrayList<PedidoPizza>> historico = (HashMap<Pedido, ArrayList<PedidoPizza>>) pedidoControle.recuperarPedidosCliente(cliente);
			
			session.setAttribute("historicoPedidos", historico);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("historicoPedidos.jsp");
		rd.forward(request,response);
	}

}
