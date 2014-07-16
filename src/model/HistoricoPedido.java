package model;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoricoPedido implements Comparable<HistoricoPedido> {

    private Timestamp data_pedido;
    private double valor_total;
    private String pagamento;
    private ArrayList<ItemPedido> items;
    
    public HistoricoPedido(Timestamp data_pedido, double valor_total, String pagamento) {
            
            this.data_pedido = data_pedido;
            this.valor_total = valor_total;
            this.pagamento = pagamento;
            
            this.items = new ArrayList<>();
    }
    
    public void adicionarNovoItem (String nome_pizza, double valor_pizza, int quantidade) {
            
            Pizza pizza = new Pizza(nome_pizza, valor_pizza);
            
            ItemPedido item = new ItemPedido(pizza, quantidade);
            
            items.add(item);
            
    }

    public Timestamp getData_pedido() {
            return data_pedido;
    }
    
    public String getData_formatada() {
            
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy - hh:mm:ss");
        String data = simpleDateFormat.format(new Date(data_pedido.getTime()));
            return data;
    }
    
    public String getValor_total_formatado() {
            
            DecimalFormat formatter = new DecimalFormat("#,###.00");
            String valor_total_formatado = formatter.format(valor_total);
            return valor_total_formatado;
    }

    public double getValor_total() {
            return valor_total;
    }

    public String getPagamento() {
            return pagamento;
    }

    public ArrayList<ItemPedido> getItems() {
            return items;
    }

    @Override
    public int compareTo(HistoricoPedido outroPedido) {

            if (this.data_pedido.getTime() < outroPedido.getData_pedido().getTime()) {
                    return -1;
            }
            if (this.data_pedido.getTime() > outroPedido.getData_pedido().getTime()) {
                    return 1;
            }

            return 0;
    }

}
