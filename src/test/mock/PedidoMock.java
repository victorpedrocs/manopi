package test.mock;

import java.sql.Timestamp;

import cliente.model.Cliente;
import pedido.model.Pagamento;
import pedido.model.Pedido;

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

}
