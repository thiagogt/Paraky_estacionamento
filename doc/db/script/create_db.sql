CREATE TABLE versao_bd ( id INTEGER IDENTITY, versao VARCHAR(256));

CREATE TABLE cliente ( 
id_cliente INTEGER IDENTITY, 
nome VARCHAR(256) not null,
sobrenome VARCHAR(256), 
cpf VARCHAR(256) UNIQUE not null, 
data_nascimento date,
tel_1 VARCHAR(256),
tel_2 VARCHAR(256),
email VARCHAR(256),
id_titular_vaga INTEGER);
