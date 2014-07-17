<!DOCTYPE html>
<html lang="pt-br">

<%@page import="controller.CardapioControle"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.reflect.Array"%>
<%@ page import="model.*"%>
<%@ include file="header.jsp"%>

<head>
</head>

<body>

    <div class="container" id="principal" style="margin-top=10px;">

        <div class="row">

            <div class="col-md-3">
                <p class="lead">Pizzaria do Manolo</p>
                <div class="list-group">
                    <a href="pedidos.jsp" class="list-group-item">Fazer Pedido</a>
                    <a href="HistoricoPedido" class="list-group-item">Lista de Pedidos</a>
                </div>
            </div>

            <div class="col-md-9">

                <div class="row carousel-holder">

                    <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="image/carousel/slider1.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="image/carousel/slider2.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="image/carousel/slider3.jpg" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>

                </div>


                <!-- Pizzas -->
                <div class="row">
                
                <%
					CardapioControle cc = new CardapioControle();
					ArrayList<Pizza> cardapio = cc.listarPizzas();

					for (Pizza pizza : cardapio) {
				%>
				<div class="col-sm-4 col-lg-4 col-md-4">
                    <div class="thumbnail">
					<img src="image/pizzas/<%=pizza.getPreco().intValue() %>.jpg" alt="">
                            <div class="caption">
                                <h4 class="pull-right"><%=pizza.getPrecoFormatado()%></h4>
                                <h4><%=pizza.getNome() %></h4>
                                <p><%=pizza.getIngredientes() %></p>
                            </div>
                        </div>
                    </div>
		<%
			}
		%>

                                  
                    

                </div>

            </div>

        </div>

    </div>
    <!-- /.container -->

<%@ include file="footer.jsp"%>