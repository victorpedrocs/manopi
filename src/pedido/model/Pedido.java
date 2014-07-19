package pedido.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

import util.ConnectionFactory;
import cliente.model.Cliente;

public class Pedido {
	
	private UUID codigo;
	private Cliente cliente;
	private Pagamento formaPagamento;
	private Timestamp dataHora;
	private Double totalPago;
	
	public Pedido(String codigo, Cliente cliente, Pagamento formaPagamento, Double totalPago) {
		this.codigo = codigo != null ? UUID.fromString(codigo) : null;
		this.cliente = cliente;
		this.formaPagamento = formaPagamento;
		this.totalPago = totalPago;
	}
	
	public Pedido(String codigo, Cliente cliente, Pagamento formaPagamento, Double totalPago, Timestamp dataHora) {
		this.codigo = codigo != null ? UUID.fromString(codigo) : null;
		this.cliente = cliente;
		this.formaPagamento = formaPagamento;
		this.totalPago = totalPago;
		this.dataHora = dataHora;
	}
	
	public Pedido(Cliente cliente, Pagamento pagamento) {
		this.codigo = UUID.randomUUID();
		this.cliente = cliente;
		this.formaPagamento = pagamento;
	}
	
	public boolean atualizarTotalPago(Double total){
		if (total != null) {
			this.totalPago = total;
			return true;
		}
		return false;
	}

	public boolean validFields(){
		if (codigo != null && cliente != null && formaPagamento != null) {
			if (cliente.getCodigo() != null && formaPagamento.getCodigo() != null) {
				return true;
			}
		}
		return false;
	}

	public Boolean alterarFormaDePagamento(Pagamento pagamento){
		if (pagamento != null && pagamento.getCodigo() != null) {
			this.formaPagamento = pagamento;
			return true;
		}
		return false;
	}
	
	public Collection<PedidoPizza> recuperarItensPedido(){
		PedidoPizzaDAO pedPizzaDAO = new PedidoPizzaDAO(ConnectionFactory.getConnection());
		
		Collection<PedidoPizza> itensPedido = pedPizzaDAO.retrieve(new PedidoPizza(null, new Pedido(this.codigo.toString(), null, null, null), null));
		
		return itensPedido;
	}
	
	public Double recuperarValorTotal() {
		double valor = 0;
		for (PedidoPizza item : recuperarItensPedido()) {
			valor +=item.getPizza().getPreco();
		}
		
		return valor;
	}
	
	public String getCodigo() {
		return codigo != null ? codigo.toString() : null;
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
	public Double getTotalPago(){
		return totalPago;
	}
	
	

}
