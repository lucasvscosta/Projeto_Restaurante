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

CREATE TABLE FechamentoNf(
	idFechamentoNf 	MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
    data			TIMESTAMP NOT NULL DEFAULT NOW(),
    numero			MEDIUMINT UNSIGNED,
    cnpj 			VARCHAR(12),
    valorTotalNf 	DECIMAL(10,2),
    icms			DECIMAL(10,2),
    CONSTRAINT pk_FechamentoNf PRIMARY KEY(idFechamentoNf)
)Engine = InnoDB;

CREATE TABLE Nf(
	idNf 			MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
    cpf 			VARCHAR(11) NOT NULL,
    id_pedido 		MEDIUMINT UNSIGNED NOT NULL,
    id_FechamentoNf MEDIUMINT UNSIGNED NOT NULL,
    CONSTRAINT pk_Nf PRIMARY KEY(idNf),
     CONSTRAINT fk_fechamento_nf 
        FOREIGN KEY(id_FechamentoNf) 
        REFERENCES FechamentoNf(idFechamentoNf)
    
)Engine = InnoDB;

CREATE TABLE Pagamento(
	idPagamento MEDIUMINT UNSIGNED AUTO_INCREMENT  NOT NULL,
	valor		DECIMAL(10,2) NOT NULL,
    horario		TIME,
    data		TIMESTAMP NOT NULL DEFAULT NOW(),
    tipo		CHAR(10) NOT NULL,
    numCartao	INT NOT NULL,
    numMesa		INT NOT NULL,
    id_nf 		MEDIUMINT UNSIGNED NOT NULL,
    id_Pedido 	MEDIUMINT UNSIGNED NOT NULL,
    
    CONSTRAINT pk_Pagamento  PRIMARY KEY(idPagamento),
    CONSTRAINT fk_Nf_Pagamento
		FOREIGN KEY (id_nf)
        REFERENCES Nf(idNf),
        
	CONSTRAINT fk_idPedido_Pagamento
		FOREIGN KEY(id_Pedido)
        REFERENCES Pedido (idPedido)

)Engine = InnoDB;

INSERT INTO USUARIO VALUE (1, 'admin', 'admin', '825569953', '38.564.546.00', '565.231.146-90', 1); 