alter  table produto add quantidade_Estoque bigint not null;
update produto set quantidade_Estoque = 0;
