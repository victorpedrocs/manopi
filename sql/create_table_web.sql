create table cliente(
  codigo serial primary key,
  login varchar(20),
  senha varchar(50),
  nome varchar(80),
  telefone varchar(20),
  endereco varchar(150),
  
  constraint login_cliente_unique unique (login)
);

create table pizza(
  codigo serial primary key,
  nome varchar(50),
  ingredientes varchar(100),
  preco real
);

create table forma_de_pagamento(
  codigo serial primary key,
  nome varchar
);

create table pedido(
  codigo varchar primary key,
  cliente_fk integer,
  forma_de_pagamento_fk integer,
  total_pago float,
  data_hora timestamp,
  
  constraint fk_pedido_cliente foreign key (cliente_fk) references cliente(codigo),
  constraint fk_pedido_forma_de_pagamento foreign key (forma_de_pagamento_fk) references forma_de_pagamento (codigo)
);

create table pedido_pizza(
  pizza_fk integer,
  pedido_fk varchar,
  quantidade integer,

  constraint fk_pp_pedido foreign key (pedido_fk) references pedido (codigo),
  constraint fk_pp_pizza foreign key (pizza_fk) references pizza (codigo)
);

