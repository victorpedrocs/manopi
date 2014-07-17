package model;

import java.sql.Timestamp;
import java.util.Collection;

import controller.ConnectionFactory;

public class Pedido {
	
	private Integer codigo;
	private Cliente cliente;
	private Pagamento formaPagamento;
	private Timestamp dataHora;
	
	public Pedido(Integer codigo, Cliente cliente, Pagamento formaPagamento) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.formaPagamento = formaPagamento;
	}

	public boolean validFields(){
		if (codigo != null && cliente != null && formaPagamento != null) {
			if (cliente.getCodigo() != null && formaPagamento.getCodigo() != null) {
				return true;
			}
		}
		return false;
	}


	
	public Collection<PedidoPizza> recuperarItensPedido(){
		PedidoPizzaDAO pedPizzaDAO = new PedidoPizzaDAO(ConnectionFactory.getConnection());
		
		Collection<PedidoPizza> itensPedido = pedPizzaDAO.retrieve(new PedidoPizza(null, new Pedido(this.codigo, null, null), null));
		
		return itensPedido;
	}
	
	public Double recuperarValorTotal() {
		double valor = 0;
		for (PedidoPizza item : recuperarItensPedido()) {
			valor +=item.getPizza().getPreco();
		}
		
		return valor;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Pagamento getFormaPagamento() {
		return formaPagamento;
	}

	public Timestamp getDataHora() {
		return dataHora;
	}
	
	
	

}
