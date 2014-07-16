package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginControle loginControle;
	private Cliente clienteLogado;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        loginControle = new LoginControle();
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
		String email = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		loginControle.verificaUsuario(email, senha);
		clienteLogado = loginControle.retornaClienteLogado();
		
		if (clienteLogado == null) {
            
            request.setAttribute("loginErro", "Dados incorretos!");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            
            rd.forward(request,response);
            
		} else {
            
            HttpSession sessao = request.getSession(true);
            
            sessao.setAttribute("clienteLogado", clienteLogado);
            sessao.setAttribute("nomeClienteLogado", clienteLogado.getNome());
            
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            
            rd.forward(request,response);
            
		}
	}

}
