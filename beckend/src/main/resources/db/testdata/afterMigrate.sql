set foreign_key_checks = 0;

delete from estado;
delete from categoria;
delete from marca;
delete from cidade;
delete from produto;

insert into estado (id, nome, sigla) values (1, 'Minas Gerais', 'MG');
insert into estado (id, nome, sigla) values (2, 'São Paulo', 'SP');
insert into estado (id, nome, sigla) values (3, 'Paraná', 'PR');

insert into categoria(id, nome) values (1, 'facas');
insert into categoria(id, nome) values (2, 'acessorios');
insert into categoria (id, nome) values (3, 'condimentos');

insert into marca(id, nome) values (1, 'tramontina');
insert into marca(id, nome) values (2, 'corte bom');
insert into marca(id, nome) values (3, 'tempero bom');

insert into produto(id, marca_id, categoria_id, nome, descricao, valor_venda,valor_custo) values ( 1, 1, 1, 'faca rambo', 'faca militar', 89.00, 56.00);
insert into produto(id, marca_id, categoria_id, nome, descricao, valor_venda,valor_custo) values ( 2, 1, 1, 'faca churrasco', 'faca para churrasco', 78.00, 46.00);
insert into produto(id, marca_id, categoria_id, nome, descricao, valor_venda,valor_custo) values ( 3, 2, 2, 'garra de urso', 'garra de urso para churrasco', 88.00, 57.00);
insert into produto(id, marca_id, categoria_id, nome, descricao, valor_venda,valor_custo) values ( 4, 3, 3, 'sal bom', 'sal para churrasco', 10.00, 5.00);