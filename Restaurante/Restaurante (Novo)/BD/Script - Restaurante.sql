-- DROP DATABASE Restaurante;
CREATE DATABASE Restaurante;

USE Restaurante;

CREATE TABLE USUARIO(
	idUsuario 	MEDIUMINT UNSIGNED NOT NULL, 
	nome		CHAR(50) NOT NULL,
    login		CHAR(20) NOT NULL,
    senha		INT(10) NOT NULL,
    rg			VARCHAR(15),
    cpf			VARCHAR(15),
    tipo    	INT(1) NOT NULL,
    
    CONSTRAINT pk_Usuario PRIMARY KEY(idUsuario)    
)Engine = InnoDB;

CREATE TABLE CARDAPIO(
	idProduto					MEDIUMINT UNSIGNED NOT NULL,
    nomeProduto					VARCHAR(10),
    descricaoProduto			VARCHAR(100),
    valorUnitarioProduto		DECIMAL(10,2),
    disponibilidadeProduto		BOOLEAN,
    tipoProduto					int(1),
	CONSTRAINT pk_Produto PRIMARY KEY(idProduto)
)Engine = InnoDB;

 CREATE TABLE PEDIDO(
	idPedido		MEDIUMINT UNSIGNED AUTO_INCREMENT  NOT NULL,
    horaEntrada		TIME,
    horaSaida		TIME,
    dataPedido		TIMESTAMP NOT NULL DEFAULT NOW(),
    numMesa			INT NOT NULL,
    id_usuario		MEDIUMINT UNSIGNED NOT NULL,
    
	CONSTRAINT pk_Pedido PRIMARY KEY(idPedido),
    CONSTRAINT fk_Usuario_Pedido
		FOREIGN KEY(id_Usuario)
        REFERENCES Usuario (idUsuario)
    
)Engine = InnoDB;

CREATE TABLE ITEMPEDIDO(
	idItemPedido		MEDIUMINT UNSIGNED AUTO_INCREMENT NOT NULL ,
    quantidadeItem		SMALLINT(10),
    id_Pedido			MEDIUMINT UNSIGNED NOT NULL,
    id_Produto			MEDIUMINT UNSIGNED NOT NULL,
    
    CONSTRAINT pk_ItemPedido PRIMARY KEY(idItemPedido),
    CONSTRAINT fk_idPedido_Item
		FOREIGN KEY(id_Pedido)
        REFERENCES Pedido (idPedido),
        
	CONSTRAINT fk_idProduto_Item
		FOREIGN KEY(id_Produto)
        REFERENCES Cardapio (idProduto)
)Engine = InnoDB;



-- DROP TABLE USUARIO;
-- DROP TABLE CARDAPIO;
-- DROP TABLE PEDIDO;

/*SELECT * FROM USUARIO;
SELECT * FROM CARDAPIO;
SELECT * FROM PEDIDO;
SELECT * FROM USUARIO WHERE nome = 'jean';
SELECT max(idPedido), horaEntrada, horaSaida, dataPedido, numMesa, id_usuario FROM PEDIDO WHERE numMesa = 3;
SELECT idPedido FROM PEDIDO WHERE (horaEntrada IS NOT NULL AND horaSaida IS NULL) AND numMesa = 3*/

INSERT INTO USUARIO VALUE (1, 'admin', 'admin', '825569953', '38.564.546.00', '565.231.146-90', 1); 
INSERT INTO CARDAPIO VALUE (1, 'Macararo', 'Macarronada', 50.00, true, 1); 





