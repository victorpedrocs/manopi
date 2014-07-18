package cardapio.model;

import java.text.DecimalFormat;

public class Pizza {
	
	private Integer codigo;
	private String nome;
	private String ingredientes;
	private Double preco;
	
	public Pizza  (Integer codigo, String nome, String ingredientes, Double preco) {
		this.codigo = codigo;
		this.nome = nome;
		this.ingredientes = ingredientes;
		this.preco = preco;
	}
	
	public Pizza(String nome, Double preco) {
		// TODO Auto-generated constructor stub
		this.nome = nome;
		this.preco = preco;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public String getPrecoFormatado() {
        
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        String valor_total_formatado = formatter.format(preco);
        return valor_total_formatado;
}
	
	
	
}
