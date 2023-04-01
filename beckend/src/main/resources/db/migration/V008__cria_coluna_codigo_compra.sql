alter  table compra add codigo varchar ( 36 ) not null after id;
update compra set codigo = uuid();
alter  table compra add constraint uk_compra_codigo unique (codigo);