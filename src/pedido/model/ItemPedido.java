package pedido.model;

import java.text.DecimalFormat;

import cardapio.model.Pizza;

public class ItemPedido {

	private int cod_pedido;
    private Pizza pizza;
    private int quantidade;
    private double total;
    
    public ItemPedido(Pizza pizza, int quantidade) {
            
            this.pizza = pizza;
            this.quantidade = quantidade;
            
            this.total = calcularTotal();

    }
    
    private double calcularTotal() {
            
            return (this.quantidade * this.pizza.getPreco());
    }
    
    public int getCod_pizza() {
            return this.pizza.getCodigo();
    }
    
    public int getQuantidade() {
            return quantidade;
    }

    public double getTotal() {
            return total;
    }
    
    public String getTotal_formatado() {
            
            DecimalFormat formatter = new DecimalFormat("#,###.00");
            String valor_total_formatado = formatter.format(total);
            return valor_total_formatado;
    }

    
    public double getValor_pizza() {
            return this.pizza.getPreco();
    }
    
    public String getValor_pizza_formatado() {
            
            return this.pizza.getPrecoFormatado();
    }
    
    public String getNome_pizza() {
            return this.pizza.getNome();
    }

    public int getCod_pedido() {
            return cod_pedido;
    }

    public void setCod_pedido(int cod_pedido) {
            this.cod_pedido = cod_pedido;
    }

}

