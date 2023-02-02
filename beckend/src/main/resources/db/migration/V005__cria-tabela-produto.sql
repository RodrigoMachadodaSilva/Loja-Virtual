create table produto (
	id bigint not null auto_increment,
	marca_id bigint not null,
    categoria_id bigint not null,
	nome varchar(80) not null,
	descricao text not null,
	valor_venda decimal(10,2) not null,
	valor_custo  decimal(10,2) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8mb4;