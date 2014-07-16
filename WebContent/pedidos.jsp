<%@page import="controller.PedidoControle"%>
<%@page import="controller.CardapioControle"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap, java.util.ArrayList, java.util.Collections" %>
<%@ include file="header.jsp"%>
<%@ page import="model.*"%>

<!--==============================content=================================-->
<section id="content">
	<div class="border-horiz"></div>
	<div class="container_12">
	
		<article class="side-bar">
			<h3>Novo pedido</h3>
			<table class="tabelaNovoPedido">
				<tbody>
					${detalhesPizza}
				</tbody>
				<tfoot>
					<tr>
						<% if(session.getAttribute("valorTotal") != null/*  && sessao.getAttribute("valorTotal") != (Object) 0.0 */) { %>
							<td>TOTAL: R$ ${valorTotal}</td>
						<% } %>
					</tr>
				</tfoot>
			</table>
			
			<%-- <p>${detalhesPizza}</p>
			
			<% if(sessao.getAttribute("valorTotal") != null) { %>
				<p>TOTAL: R$ ${valorTotal}</p>
			<% } %> --%>
			
			<a href="#modal" class="btn" id="linkAdicionarPizzas">Adicionar pizzas</a>
			
			<%
			//HttpSession sessao = request.getSession(true);
			
			//sessao.invalidate();
			
			if(session.getAttribute("detalhesPizza") != null) {
			%>
				<input type="submit" value="Forma de Pagamento" class="button" id="inputFecharPedido">
				
				<!-- <form action="FecharPedido" method="POST">
					<input type="submit" value="Fechar Pedido" class="button" id="inputFecharPedido">
				</form> -->
			<%
			}
			%>
			
			<form action="FecharPedido" id="formFecharPedido" method="POST">
				<select name="formaDePagamento" class="selectSaborPizza" id="selectFormaDePagamento">
					<%
						PedidoControle pc = new PedidoControle();
						ArrayList<Pagamento> formasDePagamento = pc.listarFormaDePagamento();

						for (Pagamento pagamento : formasDePagamento) {
					%>
							<option><%=pagamento.getFormaDePagamento() %></option>
					<%
						}
					%>
				</select>
				
				<div id="divValorTroco">
					Troco: R$ <input type="text" name="valorTroco" class="inputEscolherPizza" style="padding-top: 5px;">
				</div>
				
				<input type="submit" value="Finalizar Pedido" class="button">
			</form>
		</article>
		
		<article class="grid_8">
			<div class="padd-1"><h3>Pedidos anteriores</h3></div>
			
			<ul class="list-teachers">

				<%
					ArrayList<HistoricoPedido> pedidos = pc.listarPedidos(session);
					
					for (HistoricoPedido pedido : pedidos) {
				%>

				<li>
					<figure class="box-img">
						<img src="images/pizza-menu-pedidos.jpg " alt="" />
					</figure>
					<div class="overflow">
						<span>DATA:</span>
						<%=pedido.getData_formatada()%><br /> <span>TOTAL:</span> R$
						<%=pedido.getValor_total_formatado()%><br /> <span>TIPO PAGAMENTO:</span>
						<%=pedido.getPagamento()%><br />

						<%
							for (ItemPedido item : pedido.getItems()) {
						%>

						<p>SABOR: <%=item.getNome_pizza()%></p>
						<p>VALOR: R$ <%=item.getValor_pizza_formatado()%></p>
						<p>QUANTIDADE: <%=item.getQuantidade()%></p><br />

						<%
							}
						%>

					</div>
					
					<div class="clear"></div>
				</li>

				<% } %>

			</ul>
		</article>
		
		<div class="clear"></div>
		
	</div>
</section>

<div id="modal">
	<div class="modal-content">
		<form action="adicionaPizza">
			<div>
				<p>Sabor:</p>
				<!-- <input type="text" name="sabor" class="inputEscolherPizza" /> -->
				<select class="selectSaborPizza" name="sabor">
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
				<p>Quantidade:</p>
				<input type="text" name="quantidade" class="inputEscolherPizza" />
			</div>
			<div>
				<input type="submit" value="Adicionar" class="button" />
				<a href="#" class="button">Cancelar</a>
			</div>
		</form>
	</div>
</div>

<%@ include file="footer.jsp"%>