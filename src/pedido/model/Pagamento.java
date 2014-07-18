package pedido.model;

public class Pagamento {
	
	private Integer codigo;
	private String formaDePagamento;
	
	public Pagamento(Integer codigo, String formaDePagamento) {
		this.codigo = codigo;
		this.formaDePagamento = formaDePagamento;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}
	
	

}
