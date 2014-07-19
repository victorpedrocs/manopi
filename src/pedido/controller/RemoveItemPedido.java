package pedido.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pedido.model.PedidoPizza;

/**
 * Servlet implementation class RemoveItemPedido
 */
@WebServlet("/removerItem")
public class RemoveItemPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveItemPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		//recuperando parametros da sessão
		String indiceString = request.getParameter("indiceItem");
		Collection<PedidoPizza> itensPedido = (Collection<PedidoPizza>) session.getAttribute("itens");
		
		if (indiceString != null) {
			Integer indice = Integer.parseInt(indiceString);
			if (itensPedido != null) {
				Object[] array = (Object[]) itensPedido.toArray();
				itensPedido.remove(array[indice]);
				request.setAttribute("itens", itensPedido);
			}
		}
		
		Double valorTotal = 0d;
		
		for (PedidoPizza item : itensPedido) {
            valorTotal += item.calculaTotal();
        }
		
		session.setAttribute("valorTotal", valorTotal);
		request.getRequestDispatcher("/pedidos.jsp").forward(request,response);
		
		
	}

}
