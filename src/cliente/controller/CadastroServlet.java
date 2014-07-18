package cliente.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cliente.model.Cliente;

/**
 * Servlet implementation class CadastroServlet
 */
@WebServlet("/CadastroServlet")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CadastroControle cadastroControle; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroServlet() {
        super();
        cadastroControle = new CadastroControle();
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
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String endereco = request.getParameter("endereco");
		
		Cliente clienteLogado;
		
		clienteLogado = cadastroControle.cadastrarCliente(login, senha, nome, telefone, endereco);
		
		HttpSession sessao = request.getSession(true);
        
        sessao.setAttribute("clienteLogado", clienteLogado);
        sessao.setAttribute("nomeClienteLogado", clienteLogado.getNome());
        
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        
        rd.forward(request,response);
		
		
	}

}
