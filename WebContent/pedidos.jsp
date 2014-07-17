<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="controller.PedidoControle"%>
<%@page import="controller.CardapioControle"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap, java.util.ArrayList, java.util.Collections" %>
<%@ include file="header.jsp"%>
<%@ page import="model.*"%>


<body>
	<div class="container">
			<h3>Novo pedido</h3>
			<table>
		
					<tr>
						<% if(session.getAttribute("valorTotal") != null/*  && sessao.getAttribute("valorTotal") != (Object) 0.0 */) { %>
							<td>TOTAL: R$ ${valorTotal}</td>
						<% } %>
					</tr>
			</table>
			
			
			
			<a href="#modal" id="linkAdicionarPizzas">Adicionar pizzas</a>
			
			<% if(session.getAttribute("itens") != null) {%>
				<table>
					<%for(PedidoPizza itemPedido : (ArrayList<PedidoPizza>) session.getAttribute("itens")){%>
							<tr>
								<td>Sabor : <%itemPedido.getPizza().getNome(); %></td>
								<td>
									<table>
										<tr>
											<td>Quantidade : <%itemPedido.getQuantidade(); %></td>
										</tr>
										<tr>
											<td>Valor: <%itemPedido.calculaTotal(); %></td>
										</tr>
									</table>
								</td>
							</tr>
						<%}%>
				</table>
				<input type="submit" value="Forma de Pagamento" id="inputFecharPedido">
			<%
			}
			%>
			
			<form action="FecharPedido" id="formFecharPedido" method="POST">
				<select name="formaDePagamento" id="selectFormaDePagamento">
					<%
						PedidoControle pc = new PedidoControle();
						ArrayList<Pagamento> formasDePagamento = pc.listarFormaDePagamento();

						for (Pagamento pagamento : formasDePagamento) {
					%>
							<option value="<%pagamento.getCodigo();%>"><%=pagamento.getFormaDePagamento() %></option>
					<%
						}
					%>
				</select>
				
				<div id="divValorTroco">
					Troco: R$ <input type="text" name="valorTroco" style="padding-top: 5px;">
				</div>
				
				<input type="submit" value="Finalizar Pedido" >
			</form>
		
		
		
			<div><h3>Pedidos anteriores</h3></div>
			
			<ul>

				<%
					ArrayList<Pedido> pedidos = pc.listarPedidos();
					
					for (Pedido pedido : pedidos) {
				%>

				<li>
					
					<div class="overflow">
						<span>DATA:</span>
						<%=pedido.getDataHora() %><br /> <span>TOTAL:</span> R$
						<%=pedido.recuperarValorTotal()%><br /> <span>TIPO PAGAMENTO:</span>
						<%=pedido.getFormaPagamento()%><br />


					</div>
					
					<div ></div>
				</li>

				<% } %>

			</ul>
		
		
		<div class="clear"></div>
		</div>
	</body>

	<div id="modal">
		<div >
			<form action="AdicionaPizzaServlet">
				<div>
					<p>Sabor:</p><select name="nomePizza">
						<%
						CardapioControle cc = new CardapioControle();
						ArrayList<Pizza> cardapio = cc.listarPizzas();
							
							for(Pizza pizza: cardapio) {
						%>
								<option><%=pizza.getNome()%></option>
						<%
							}
						%>
					</select>
				</div>
				<div>
					<p>Quantidade:</p><input type="text" name="quantidade"/>
				</div>
				<div>
					<input type="submit" value="Adicionar" />
					<a href="#" class="button">Cancelar</a>
				</div>
			</form>
		</div>
	</div>

<%@ include file="footer.jsp"%>