package controller;

import model.Cliente;
import model.ClienteDAO;

public class CadastroControle {
	
	private ClienteDAO clienteDao;
	
	public CadastroControle() {
		// TODO Auto-generated constructor stub
		clienteDao = new ClienteDAO(ConnectionFactory.getConnection());
	}

	public Cliente cadastrarCliente(String login, String senha, String nome,
			String telefone, String endereco) {

		Cliente cliente = new Cliente(null, login, senha, nome, telefone, endereco);
		clienteDao.create(cliente);
		
		return clienteDao.retrieve(cliente);
		
	}

}
