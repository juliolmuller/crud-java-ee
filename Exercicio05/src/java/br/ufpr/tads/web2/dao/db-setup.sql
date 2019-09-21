
----------------------------------------------------------
-- Setup do esquema de banco de dados para o PostgreSQL --
----------------------------------------------------------


-- Criar tabela de usuários
CREATE TABLE tb_usuario(
  id_usuario SERIAL PRIMARY KEY,
  nome_usuario VARCHAR(100) NOT NULL,
  login_usuario VARCHAR(50) NOT NULL,
  senha_usuario VARCHAR(50) NOT NULL
);


-- Inserir dados padrões
INSERT INTO tb_usuario(nome_usuario, login_usuario, senha_usuario) VALUES
  ('André Antunes', 'AANTUNES', 'senha123'),
  ('Aurélio Shizuo', 'ASHIZUO', 'qwerty'),
  ('Cassiano Vidal', 'CVIDAL', '123456789'),
  ('Júlio Müller', 'JMULLER', 'password'),
  ('Wesley Caetano', 'VVESLEY', 'futebol');


-- Criar tabela de clientes
CREATE TABLE tb_cliente(
  id_cliente SERIAL PRIMARY KEY,
  cpf_cliente CHAR(11) NOT NULL UNIQUE,
  nome_cliente VARCHAR(100),
  email_cliente VARCHAR(100),
  data_cliente DATE,
  rua_cliente VARCHAR(100),
  nr_cliente INT,
  cep_cliente CHAR(8),
  cidade_cliente VARCHAR(50),
  uf_cliente CHAR(2)
);

INSERT INTO tb_cliente(
  cpf_cliente, nome_cliente, data_cliente,
  email_cliente, cidade_cliente, uf_cliente,
  cep_cliente, rua_cliente, nr_cliente
) VALUES
  ('63837430073', 'Davi Teixeira Ducati', '1984-04-10', 'davi.ducati@globo.com', 'Manaus', 'AM', '69029285', 'Rua Anita Garibald', 722),
  ('46513906091', 'Pedro Gomes Amaral', '1960-03-20', 'pedro.amaral@uol.com.br', 'Campinas', 'SP', '13015180', 'Praça Anita Garibaldi', 486),
  ('22649526793', 'Lara Ducati Teixeira', '1950-11-25', 'lara.teixeira@uol.com.br', 'Campinas', 'SP', '13015180', 'Praça Anita Garibaldi', 288),
  ('83128933006', 'Matheus Teixeira Ducati', '1991-10-11', 'matheus.ducati@hotmail.com', 'Salvador', 'BA', '40725074', 'Rua Anita Cajado', 898),
  ('08054962622', 'Mariana Machado Gomes', '1954-11-09', 'mariana.gomes@yahoo.com', 'Belo Horizonte', 'MG', '31210440', 'Rua Anfibólios', 399),
  ('90498121208', 'Giovana Gomes Ducati', '1969-10-15', 'giovana.ducati@globo.com', 'Campinas', 'SP', '13015180', 'Praça Anita Garibaldi', 997),
  ('73812059479', 'Gabriela Amaral Machado', '1954-04-21', 'gabriela.machado@yahoo.com', 'Piracicaba', 'SP', '13405247', 'Travessa Ângelo Valler', 670),
  ('20165379812', 'Davi Machado Gomes', '1994-01-27', 'davi.gomes@icloud.com', 'Campinas', 'SP', '13015180', 'Praça Anita Garibaldi', 992),
  ('75932242108', 'Mariana Machado Teixeira', '1986-04-27', 'mariana.teixeira@hotmail.com', 'Piracicaba', 'SP', '13405247', 'Travessa Ângelo Valler', 298),
  ('90534089240', 'Alice Ducati Machado', '1951-02-22', 'alice.machado@gmail.com', 'Brasília', 'DF', '70342500', 'Quadra CLS 103', 650),
  ('54466773076', 'Gabriel Teixeira Machado', '1961-03-12', 'gabriel.machado@hotmail.com', 'Maceió', 'AL', '57055265', 'Rua Antenor Gomes de Oliveira', 239),
  ('81160961506', 'Lucas Ducati Teixeira', '1970-08-19', 'lucas.teixeira@yahoo.com', 'Salvador', 'BA', '57055265', 'Rua Anita Cajado', 715),
  ('09434959209', 'Pedro Teixeira Machado', '1953-02-13', 'pedro.machado@uol.com.br', 'Curitiba', 'PR', '80610150', 'Rua Morretes', 753),
  ('88931040083', 'Mariana Machado Ducati', '1989-04-21', 'mariana.ducati@uol.com.br', 'Campinas', 'SP', '13015180', 'Praça Anita Garibaldi', 611),
  ('59851539597', 'Mauro Gomes Machado', '1980-01-25', 'mauro.machado@gmail.com', 'Curitiba', 'PR', '82510100', 'Rua Venezuela', 189);
