 create database hoteldb;
 
 use hoteldb;
 
 create table estoquehotel (
	id_produto varchar(20) PRIMARY KEY,
	nome_produto varchar(30),
	descricao varchar(200),
	quantidade int(50),
	valor decimal(30,2)	
 );
 
 CREATE TABLE clientehotel(
    IDCLIENTE INT PRIMARY KEY AUTO_INCREMENT,
    CNOME VARCHAR(60) NOT NULL,
    SEXO CHAR(1) DEFAULT 'F',
    CTELEFONE VARCHAR(30),
    CENDERECO VARCHAR(40),
    CCPF VARCHAR(11)
);