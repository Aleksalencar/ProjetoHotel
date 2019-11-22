 create database hoteldb;
 
 use hoteldb;
 
 create table estoquehotel (
	id_produto varchar(20) PRIMARY KEY,
	nome_produto varchar(30),
	descricao varchar(200),
	quantidade int(50),
	valor decimal(30,2)	
 );
 