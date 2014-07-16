<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta name="description" content="Trabalho de Computação III">
	    <meta name="author" content="Rafael Costa">
	    <meta name="author" content="Victor Pedro">

	    <title>Pizzaria do Manolo</title>

	    <!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet">

	    <!-- custom CSS -->
	    <link href="css/shop-homepage.css" rel="stylesheet">
	    <link href="css/bootstrap.min.css" rel="stylesheet">
	    <link href="css/signin.css" rel="stylesheet">

	    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
	    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
	    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

	    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>	
	</head>
	<body>
		<!-- HEADER -->
		<header>
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		        <div class="container">
		            <div class="navbar-header">
		                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
		                    <span class="sr-only">Toggle navigation</span>
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                </button>
		                <a class="navbar-brand" href="#home">Pizzaria do Manolo</a>
		            </div>

		            <!-- Collect the nav links, forms, and other content for toggling -->
		            <div class="collapse navbar-collapse navbar-ex1-collapse">
		                <ul class="nav navbar-nav">
		                    <li><a href="#about">Fazer Pedido</a>
		                </ul>
		                <!-- Login button with dropdown -->
		                <ul class="nav pull-right navbar-nav">
		                <%if(request.getSession().getAttribute("clienteLogado") != null){%>
							<font color= "white">Ol�, <%= request.getSession().getAttribute("nomeClienteLogado") %>
						<% }else{ %>
		                    <li> <a href="login.jsp">Login</a></li>
		                <% } %>
		                </ul>
		            </div>
		            <!-- /.navbar-collapse -->
		        </div>
		        <!-- /.container -->
		    </nav>
		</header>