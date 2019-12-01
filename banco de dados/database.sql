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
    cnome VARCHAR(60) NOT NULL,
	cemail varchar(60),
    CSEXO CHAR(1) DEFAULT 'F',
    CTELEFONE VARCHAR(30),
    CENDERECO VARCHAR(40),
    CCPF VARCHAR(11)
);

 CREATE TABLE quartohotel(
    IDQUARTO INT PRIMARY KEY AUTO_INCREMENT,
    cnumero VARCHAR(60) NOT NULL,
    candar VARCHAR(3),
    calugado VARCHAR(60),
    ctipo VARCHAR(60)
);