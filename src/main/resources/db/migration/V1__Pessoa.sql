CREATE TABLE PESSOA
(
    id         INT AUTO_INCREMENT,
    nome       VARCHAR(100) NOT NULL,
    cpf        VARCHAR(11)  NOT NULL UNIQUE,
    nascimento date,
    PRIMARY KEY (id)
);

CREATE TABLE CONTATO
(
    id       INT AUTO_INCREMENT,
    email    varchar(255) not null,
    nome     varchar(100) not null,
    telefone varchar(13)  not null,
    PRIMARY KEY (id)
);

create table pessoa_contato
(
    pessoa_fk  INT not null,
    contato_fk INT not null
);