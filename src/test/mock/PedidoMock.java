package test.mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import cardapio.model.Pizza;
import cliente.model.Cliente;
import pedido.model.Pagamento;
import pedido.model.Pedido;
import pedido.model.PedidoPizza;

public class PedidoMock extends Pedido{

	public PedidoMock(Cliente cliente, Pagamento pagamento){
		super(cliente, pagamento);
	}
	
	public PedidoMock(String codigo, Cliente cliente, Pagamento formaPagamento,
			Double totalPago, Timestamp dataHora) {
		super(codigo, cliente, formaPagamento, totalPago, dataHora);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean validFields() {
		return true;
	}
	
	@Override
	public Collection<PedidoPizza> recuperarItensPedido(){
		ArrayList<PedidoPizza> itensPedido = new ArrayList<PedidoPizza>();
		itensPedido.add(new PedidoPizza(new Pizza("teste", 22d), this, 1));
		return itensPedido;
	}

}
