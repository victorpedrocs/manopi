package cliente.controller;

import util.ConnectionFactory;
import cliente.model.Cliente;
import cliente.model.ClienteDAO;

public class LoginControle {
	
	private ClienteDAO clienteDao;
	private Cliente clienteLogado;
	
	public LoginControle() {
		// TODO Auto-generated constructor stub
		clienteDao = new ClienteDAO(ConnectionFactory.getConnection());
	}
	
	public void verificaUsuario(String login, String senha) {
		
		Cliente cliente = new Cliente(login, senha);
		
		clienteLogado = clienteDao.retrieve(cliente);
	}
	
	public Cliente retornaClienteLogado() {
		return clienteLogado;
	}

}
