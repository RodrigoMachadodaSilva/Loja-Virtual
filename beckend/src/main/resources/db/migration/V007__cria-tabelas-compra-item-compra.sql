create table compra (
  id bigint not null auto_increment,
  codigo varchar (255) ,
  subtotal decimal(10,2),
  taxa_frete decimal(10,2),
  valor_total decimal(10,2),

  #restaurante_id bigint ,
  usuario_cliente_id bigint ,
  #forma_pagamento_id bigintl,
  
  endereco_cidade_id bigint(20),
  endereco_cep varchar(9),
  endereco_logradouro varchar(100),
  endereco_numero varchar(20),
  endereco_complemento varchar(60),
  endereco_bairro varchar(60),
  
  #status varchar(10),
  data_criacao datetime,
  #data_confirmacao datetime,
  #data_cancelamento datetime,
  #data_entrega datetime,

  primary key (id)

 /* constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id),
  constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario (id),
  constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id)*/
) engine=InnoDB default charset=utf8;

create table item_compra (
  id bigint not null auto_increment,
  quantidade smallint(6) ,
  preco_unitario decimal(10,2) ,
  preco_total decimal(10,2) ,
  observacao varchar(255) ,
  compra_id bigint ,
  produto_id bigint ,
  
  primary key (id)
  #unique key uk_item_compra_produto (compra_id, produto_id),

  #constraint fk_item_compra_compra foreign key (compra_id) references pedido (id),
  #constraint fk_item_compra_produto foreign key (produto_id) references produto (id)
) engine=InnoDB default charset=utf8;