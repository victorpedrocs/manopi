<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page
	import="java.util.HashMap, java.util.ArrayList, java.util.Collections"%>
<%@ include file="header.jsp"%>
<%@ page import="model.*"%>


<body>
	<div class="container">
		<div>
			<h3>Pedidos anteriores</h3>
		</div>

			<%
					HashMap<Pedido, ArrayList<PedidoPizza>> historicoPedido = (HashMap<Pedido, ArrayList<PedidoPizza>>) session.getAttribute("historicoPedidos"); 
					if( historicoPedido != null){
						
					for (Pedido pedido : historicoPedido.keySet()) {
						Double valorTotal = 0d;
						ArrayList<PedidoPizza> itensPedido = historicoPedido.get(pedido);
				%>

				<div class="overflow">
					<table border="1px">
						<tr>
							<td>Data do pedido: <%= pedido.getDataHora() %></td>
							<td>
								<table>
									<% for ( PedidoPizza item : itensPedido){ %>
										<tr><td>Pizza: </td><td><%= item.getPizza().getNome() %></td></tr>
										<tr><td>Quantidade: </td><td> <%= item.getQuantidade() %></td></tr>
										<% valorTotal += item.getPizza().getPreco()*item.getQuantidade(); %>
									<%} %>
								</table>
							</td>
						</tr>
						<tr><td>Valor Total : <%= valorTotal %></td></tr>
					</table>
				</div>

				<% } %>
			<% } %>


		<div class="clear"></div>
	</div>
	</div>
</body>

<%@ include file="footer.jsp"%>