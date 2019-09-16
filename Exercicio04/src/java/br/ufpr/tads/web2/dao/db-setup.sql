
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
