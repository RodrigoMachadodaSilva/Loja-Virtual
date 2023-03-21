create table permissao (

	id bigint not null auto_increment,
    nome varchar(50) not null,
    
    primary key (id)
) engine=InnoDB default charset=utf8mb4;


create table pessoa(
	id bigint not null auto_increment,
    nome varchar(80) not null,
    cpf varchar(80) not null,
    email varchar(80) not null,
	
	endereco_cidade_id bigint,
	endereco_cep varchar(9),
	endereco_logradouro varchar(100),
	endereco_numero varchar(20),
	endereco_complemento varchar(60),
	endereco_bairro varchar(60),
	
	primary key (id)
) engine=InnoDB default charset=utf8mb4;