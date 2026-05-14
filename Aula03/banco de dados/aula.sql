CREATE TABLE cliente (
    id_cliente      BIGSERIAL PRIMARY KEY,
    nome            VARCHAR(60) NOT NULL,
    cpf             VARCHAR(11) UNIQUE,
    email           VARCHAR(50),
    data_nascimento DATE
);

TRUNCATE TABLE cliente, cliente_vip RESTART IDENTITY;