package model;

public class PedidoPizza {
	
	private Pizza pizza;
	private Pedido pedido;
	private Integer quantidade;
	
	public PedidoPizza(Pizza pizza, Pedido pedido, Integer quantidade) {
		this.pizza = pizza;
		this.pedido = pedido;
		this.quantidade = quantidade;
	}
	public boolean validFields(){
		if(this.quantidade != null && this.pizza != null && this.pedido != null){
			if(this.pizza.getCodigo() != null && this.pedido.getCodigo() != null)
				return true;
		}
		return false;
	}
	public Double calculaTotal(){
		if(this.pizza != null && this.pizza.getPreco() != null && this.quantidade != null){
			return this.quantidade*this.pizza.getPreco();
		}
		return null;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	
	
	
	

}
