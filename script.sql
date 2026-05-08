DROP DATABASE IF EXISTS dbpadaria;
CREATE DATABASE dbpadaria;
USE dbpadaria;

CREATE TABLE tbpessoa
(
    id_pessoa_PK INT          NOT NULL AUTO_INCREMENT,
    nome         VARCHAR(100) NOT NULL,
    cpf          CHAR(11)     NOT NULL UNIQUE,
    telefone     VARCHAR(20)  NOT NULL,
    endereco     VARCHAR(100),

    PRIMARY KEY (id_pessoa_PK)
);

CREATE TABLE tbcliente
(
    id_pessoa_PK_FK INT     NOT NULL,
    filiacao        TINYINT NOT NULL,

    PRIMARY KEY (id_pessoa_PK_FK),
    CONSTRAINT fk_cliente_pessoa FOREIGN KEY (id_pessoa_PK_FK)
        REFERENCES tbpessoa (id_pessoa_PK) ON DELETE CASCADE
);

CREATE TABLE tbfuncionario
(
    id_pessoa_PK_FK  INT            NOT NULL,
    horario_trabalho INT            NOT NULL,
    salario          DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY (id_pessoa_PK_FK),
    CONSTRAINT fk_funcionario_pessoa FOREIGN KEY (id_pessoa_PK_FK)
        REFERENCES tbpessoa (id_pessoa_PK) ON DELETE CASCADE
);

CREATE TABLE tbpagamento
(
    id_pagamento_PK INT NOT NULL AUTO_INCREMENT,
    metodo          ENUM('DINHEIRO', 'PIX', 'CARTAO_CREDITO', 'CARTAO_DEBITO') NOT NULL,

    PRIMARY KEY (id_pagamento_PK)
);

CREATE TABLE tbponto
(
    id_ponto_PK       INT      NOT NULL AUTO_INCREMENT,
    data_hora         DATETIME NOT NULL,
    tipo              ENUM('ENTRADA', 'SAIDA') NOT NULL,
    id_funcionario_FK INT      NOT NULL,

    PRIMARY KEY (id_ponto_PK),
    CONSTRAINT fk_ponto_funcionario FOREIGN KEY (id_funcionario_FK)
        REFERENCES tbfuncionario (id_pessoa_PK_FK) ON DELETE CASCADE
);

CREATE TABLE tbdados_entrega
(
    id_dados_entrega_PK INT           NOT NULL AUTO_INCREMENT,
    endereco            VARCHAR(100)  NOT NULL,
    taxa                DECIMAL(5, 2) NOT NULL,
    id_entregador_FK    INT,

    PRIMARY KEY (id_dados_entrega_PK),
    CONSTRAINT fk_entrega_funcionario FOREIGN KEY (id_entregador_FK)
        REFERENCES tbfuncionario (id_pessoa_PK_FK)
);

CREATE TABLE tbpedido
(
    id_pedido_PK        INT           NOT NULL AUTO_INCREMENT,
    data_hora           DATETIME      NOT NULL,
    conta_total         DECIMAL(8, 2) NOT NULL,
    id_cliente_FK       INT           NOT NULL,
    id_pagamento_FK     INT           NOT NULL,
    id_dados_entrega_FK INT,

    PRIMARY KEY (id_pedido_PK),
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (id_cliente_FK)
        REFERENCES tbcliente (id_pessoa_PK_FK),
    CONSTRAINT fk_pedido_pagamento FOREIGN KEY (id_pagamento_FK)
        REFERENCES tbpagamento (id_pagamento_PK),
    CONSTRAINT fk_pedido_entrega FOREIGN KEY (id_dados_entrega_FK)
        REFERENCES tbdados_entrega (id_dados_entrega_PK)
);

CREATE TABLE tbproduto
(
    id_produto_PK INT         NOT NULL AUTO_INCREMENT,
    nome          VARCHAR(50) NOT NULL,

    PRIMARY KEY (id_produto_PK)
);

CREATE TABLE tbsabor
(
    id_sabor_PK INT         NOT NULL AUTO_INCREMENT,
    sabor       VARCHAR(50) NOT NULL UNIQUE,

    PRIMARY KEY (id_sabor_PK)
);

CREATE TABLE tbproduto_sabor
(
    id_produto_PK_FK   INT           NOT NULL,
    id_sabor_PK_FK     INT           NOT NULL,
    quantidade_produto INT DEFAULT 0,
    preco              DECIMAL(7, 2) NOT NULL,

    PRIMARY KEY (id_produto_PK_FK, id_sabor_PK_FK),
    CONSTRAINT fk_produto FOREIGN KEY (id_produto_PK_FK)
        REFERENCES tbproduto (id_produto_PK) ON DELETE CASCADE,
    CONSTRAINT fk_sabor FOREIGN KEY (id_sabor_PK_FK)
        REFERENCES tbsabor (id_sabor_PK) ON DELETE CASCADE
);

CREATE TABLE tbitem_pedido
(
    id_pedido_PK_FK  INT NOT NULL,
    id_produto_PK_FK INT NOT NULL,
    id_sabor_PK_FK   INT NOT NULL,
    quantidade_itens INT NOT NULL,

    PRIMARY KEY (id_pedido_PK_FK, id_produto_PK_FK),
    CONSTRAINT fk_item_pedido FOREIGN KEY (id_pedido_PK_FK)
        REFERENCES tbpedido (id_pedido_PK) ON DELETE CASCADE,
    CONSTRAINT fk_item_produto_sabor FOREIGN KEY (id_produto_PK_FK, id_sabor_PK_FK)
        REFERENCES tbproduto_sabor (id_produto_PK_FK, id_sabor_PK_FK)
);

CREATE TABLE tbingrediente
(
    id_ingrediente_PK      INT           NOT NULL AUTO_INCREMENT,
    nome                   VARCHAR(50)   NOT NULL,
    unidade_medida         ENUM('KG', 'GRAMA', 'LITRO', 'MILILITRO', 'UNIDADE') NOT NULL,
    quantidade_ingrediente DECIMAL(8, 3) NOT NULL,

    PRIMARY KEY (id_ingrediente_PK)
);

CREATE TABLE tbreceita
(
    id_produto_PK_FK     INT           NOT NULL,
    id_sabor_PK_FK       INT           NOT NULL,
    id_ingrediente_PK_FK INT           NOT NULL,
    quantidade_receita   DECIMAL(8, 3) NOT NULL,

    PRIMARY KEY (id_produto_PK_FK, id_sabor_PK_FK, id_ingrediente_PK_FK),
    CONSTRAINT fk_produto_sabor_receita FOREIGN KEY (id_produto_PK_FK, id_sabor_PK_FK)
        REFERENCES tbproduto_sabor (id_produto_PK_FK, id_sabor_PK_FK),
    CONSTRAINT fk_ingrediente_receita FOREIGN KEY (id_ingrediente_PK_FK)
        REFERENCES tbingrediente (id_ingrediente_PK)
);
