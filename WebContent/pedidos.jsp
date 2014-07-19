<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="pedido.controller.PedidoControle"%>
<%@page import="cardapio.controller.CardapioControle"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap, java.util.ArrayList, java.util.Collections" %>
<%@ include file="header.jsp"%>
<%@ page import="pedido.model.PedidoPizza"%>
<%@ page import="pedido.model.Pedido"%>
<%@ page import="pedido.model.Pagamento"%>
<%@ page import="cardapio.model.Pizza"%>

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
			
			
			<% if(session.getAttribute("itens") != null) {%>
				<table class="table table-hover half-page-table">
					<thead>
						<th>Sabor</th>
						<th>Quantidade</th>
						<th>Valor</th>
						<th></th>
					</thead>
					<tbody>
					<%ArrayList<PedidoPizza> itensPedido = (ArrayList<PedidoPizza>) session.getAttribute("itens"); %>
					<%for(int i = 0 ; i < itensPedido.size() ; i++){
						PedidoPizza itemPedido = itensPedido.get(i);%>
							<tr>
								<td><%= itemPedido.getPizza().getNome() %></td>
								<td><%= itemPedido.getQuantidade() %></td>
								<td><%= itemPedido.calculaTotal() %></td>
								<td><form method="post" action="removerItem">
									<input type="hidden" name="indiceItem" value="<%=i%>"><input type="submit" class="btn btn-sm btn-danger" value="Remover"/> 
									</form></td>
							</tr>
						<%}%>
					</tbody>
				</table>
			<%
			}
			%>
			
			<form action="FecharPedidoServlet" id="formFecharPedido" method="POST">
				<div class="form-group col-sm-*">
					<label for="selectFormaDePagamento">Forma de Pagamento</label> 
					<select class="inline" name="formaDePagamento" id="selectFormaDePagamento">
						<%
							PedidoControle pc = new PedidoControle();
							ArrayList<Pagamento> formasDePagamento = pc.listarFormaDePagamento();
	
							for (Pagamento pagamento : formasDePagamento) {
						%>
								<option value="<%=pagamento.getCodigo()%>"><%=pagamento.getFormaDePagamento() %></option>
						<%
							}
						%>
					</select>
				</div>
				<div class="form-group" id="divValorTroco">
					<label for="valor_troco">Valor de Pagamento</label>
					<input type="text" placeholder="R$" id="valor_troco" name="valorTroco" style="padding-top: 5px;">
				</div>
				
				<div class="container">
					<button type="button" class="btn" data-toggle="modal" data-target="#myModal">Adicionar Pizza</button>
					<input type="submit" class="btn btn-primary" value="Finalizar Pedido"/>
				</div>
			</form>		
		
		<div class="clear"></div>
		</div>
	</body>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="AdicionaPizzaServlet" method="post">
				<div class="modal-content">
					<div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title" id="myModalLabel">Adicionar Pizza</h4>
				      </div>	
				      <div class="modal-body">
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
							<div>
								<p>Quantidade:</p><input type="text" name="quantidade"/>
							</div>
			      	</div>
			      	<div class="modal-footer">
			      		<button type="button" class="btn" data-dismiss="modal">Cancelar</button>
						<input type="submit" class="btn btn-default" value="Adicionar" />
					</div>
						
				</div>
				
				
			</form>
		</div>
	</div>

<%@ include file="footer.jsp"%>