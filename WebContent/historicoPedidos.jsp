<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List, java.text.SimpleDateFormat"%>
<%@page
	import="java.util.TreeMap, java.util.Map, java.util.ArrayList, java.util.Collections"%>
<%@ include file="header.jsp"%>
<%@ page import="pedido.model.Pedido"%>
<%@ page import="pedido.model.PedidoPizza"%>
<%@ page import="cardapio.model.Pizza"%>

<body>
	<div class="container">
		<div>
			<h3>Pedidos anteriores</h3>
		</div>

			<%
					Map<Pedido, ArrayList<PedidoPizza>> historicoPedido = (Map<Pedido, ArrayList<PedidoPizza>>) session.getAttribute("historicoPedidos"); 
					if( historicoPedido != null){
						
					for (Pedido pedido : historicoPedido.keySet()) {
						Double valorTotal = 0d;
						ArrayList<PedidoPizza> itensPedido = historicoPedido.get(pedido);
				%>				
				<div class="overflow">
					<h4>Data do Pedido: <%=new SimpleDateFormat("dd/MM/yyyy - HH:mm	").format(pedido.getDataHora())%></h4>
					<table class="table table-hover half-page-table">
						<thead>
							<th>Pizza</th>
							<th>Quantidade</th>
							<th>Preço</th>
						</thead>
						<tbody>
						<% for ( PedidoPizza item : itensPedido){ %>
							<tr>
								<td><%= item.getPizza().getNome() %></td>
								<td><%= item.getQuantidade() %></td>
								<td><%= item.getPizza().getPrecoFormatado() %></td>
							</tr>
							<% valorTotal += item.getPizza().getPreco()*item.getQuantidade(); %>
						<%} %>
							
							<tr><td></td><td></td><td>Total: <%= valorTotal %></td></tr>
						</tbody>
					</table>
				</div>

				<% } %>
			<% } %>


		<div class="clear"></div>
	</div>
	</div>
</body>

<%@ include file="footer.jsp"%>