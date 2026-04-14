DROP DATABASE IF EXISTS db_padaria;
CREATE DATABASE db_padaria;
USE db_padaria;

CREATE TABLE tb_pessoa
(
    id_pessoa_PK INT          NOT NULL AUTO_INCREMENT,
    nome         VARCHAR(100) NOT NULL,
    cpf          CHAR(11)     NOT NULL UNIQUE,
    telefone     VARCHAR(20)  NOT NULL,
    endereco     VARCHAR(100),

    PRIMARY KEY (id_pessoa_PK)
);

CREATE TABLE tb_cliente
(
    id_pessoa_PK_FK INT     NOT NULL,
    filiacao        TINYINT NOT NULL,

    PRIMARY KEY (id_pessoa_PK_FK),
    CONSTRAINT fk_cliente_pessoa FOREIGN KEY (id_pessoa_PK_FK)
        REFERENCES tb_pessoa (id_pessoa_PK) ON DELETE CASCADE
);

CREATE TABLE tb_funcionario
(
    id_pessoa_PK_FK  INT            NOT NULL,
    horario_trabalho INT            NOT NULL,
    salario          DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY (id_pessoa_PK_FK),
    CONSTRAINT fk_funcionario_pessoa FOREIGN KEY (id_pessoa_PK_FK)
        REFERENCES tb_pessoa (id_pessoa_PK) ON DELETE CASCADE
);

CREATE TABLE tb_pagamento
(
    id_pagamento_PK INT NOT NULL AUTO_INCREMENT,
    metodo          ENUM('DINHEIRO', 'PIX', 'CARTAO_CREDITO', 'CARTAO_DEBITO') NOT NULL,

    PRIMARY KEY (id_pagamento_PK)
);

CREATE TABLE tb_ponto
(
    id_ponto_PK       INT      NOT NULL AUTO_INCREMENT,
    data_hora         DATETIME NOT NULL,
    tipo              ENUM('ENTRADA', 'SAIDA') NOT NULL,
    id_funcionario_FK INT      NOT NULL,

    PRIMARY KEY (id_ponto_PK),
    CONSTRAINT fk_ponto_funcionario FOREIGN KEY (id_funcionario_FK)
        REFERENCES tb_funcionario (id_pessoa_PK_FK) ON DELETE CASCADE
);

CREATE TABLE tb_dados_entrega
(
    id_dados_entrega_PK INT           NOT NULL AUTO_INCREMENT,
    endereco            VARCHAR(100)  NOT NULL,
    taxa                DECIMAL(5, 2) NOT NULL,
    id_entregador_FK    INT,

    PRIMARY KEY (id_dados_entrega_PK),
    CONSTRAINT fk_entrega_funcionario FOREIGN KEY (id_entregador_FK)
        REFERENCES tb_funcionario (id_pessoa_PK_FK)
);

CREATE TABLE tb_pedido
(
    id_pedido_PK        INT           NOT NULL AUTO_INCREMENT,
    data_hora           DATETIME      NOT NULL,
    conta_total         DECIMAL(8, 2) NOT NULL,
    id_cliente_FK       INT           NOT NULL,
    id_pagamento_FK     INT           NOT NULL,
    id_dados_entrega_FK INT,

    PRIMARY KEY (id_pedido_PK),
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (id_cliente_FK)
        REFERENCES tb_cliente (id_pessoa_PK_FK),
    CONSTRAINT fk_pedido_pagamento FOREIGN KEY (id_pagamento_FK)
        REFERENCES tb_pagamento (id_pagamento_PK),
    CONSTRAINT fk_pedido_entrega FOREIGN KEY (id_dados_entrega_FK)
        REFERENCES tb_dados_entrega (id_dados_entrega_PK)
);

CREATE TABLE tb_item_pedido
(
    id_pedido_PK_FK  INT NOT NULL,
    id_produto_PK_FK INT NOT NULL,
    quantidade_itens INT NOT NULL,

    PRIMARY KEY (id_pedido_PK_FK, id_produto_PK_FK),
    CONSTRAINT fk_item_pedido FOREIGN KEY (id_pedido_PK_FK)
        REFERENCES tb_pedido (id_pedido_PK) ON DELETE CASCADE,
    CONSTRAINT fk_item_produto FOREIGN KEY (id_produto_PK_FK)
        REFERENCES tb_produto (id_produto_PK)
);

CREATE TABLE tb_produto
(
    id_produto_PK INT           NOT NULL AUTO_INCREMENT,
    nome          VARCHAR(50)   NOT NULL,
    sabor         VARCHAR(20),
    preco         DECIMAL(7, 2) NOT NULL,
    quantidade    INT           NOT NULL,

    PRIMARY KEY (id_produto_PK)
);

CREATE TABLE tb_ingrediente
(
    id_ingrediente_PK  INT           NOT NULL AUTO_INCREMENT,
    nome               VARCHAR(50)   NOT NULL,
    unidade_medida     ENUM('KG', 'GRAMA', 'LITRO', 'MILILITRO', 'UNIDADE') NOT NULL,
    quantidade_estoque DECIMAL(8, 3) NOT NULL,

    PRIMARY KEY (id_ingrediente_PK)
);

CREATE TABLE tb_receita
(
    id_produto_PK_FK     INT           NOT NULL,
    id_ingrediente_PK_FK INT           NOT NULL,
    quantidade_usada     DECIMAL(8, 3) NOT NULL,

    PRIMARY KEY (id_produto_PK_FK, id_ingrediente_PK_FK),
    CONSTRAINT fk_receita_produto FOREIGN KEY (id_produto_PK_FK)
        REFERENCES tb_produto (id_produto_PK) ON DELETE CASCADE,
    CONSTRAINT fk_receita_ingrediente FOREIGN KEY (id_ingrediente_PK_FK)
        REFERENCES tb_ingrediente (id_ingrediente_PK) ON DELETE CASCADE
);