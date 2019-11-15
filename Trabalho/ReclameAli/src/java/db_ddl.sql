
-- Excluir esquemas conflitantes
DROP TABLE IF EXISTS ticket_messages;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS ticket_status;
DROP TABLE IF EXISTS ticket_types;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS states;
DROP TABLE IF EXISTS users;

-- Tebela de Usuários
CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(150) NOT NULL,
  cpf CHAR(11) NOT NULL UNIQUE,
  date_birth DATE NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  phone VARCHAR(16),
  role VARCHAR(32) NOT NULL,
  password VARCHAR(255)
);

-- Tabela de Estados
CREATE TABLE states(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  abrev CHAR(2) NOT NULL
);

-- Tabela de Endereços
CREATE TABLE addresses(
  user_id SERIAL PRIMARY KEY REFERENCES users(id),
  zip_code CHAR(8),
  street VARCHAR(255),
  number INT,
  complement VARCHAR(30),
  neightborhood VARCHAR(80),
  city VARCHAR(80),
  state_id INT REFERENCES states(id)
);

-- Tabela de CAtegorias de Produtos
CREATE TABLE categories(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

-- Tabela de Produtos
CREATE TABLE products(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255) NOT NULL,
  weight FLOAT DEFAULT 0,
  category_id INT NOT NULL REFERENCES categories(id),
  utc_code CHAR(12) NOT NULL,
  ean_code CHAR(13)
);

-- Tabelas de atributos de Atendimentos
CREATE TABLE ticket_types(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);
CREATE TABLE ticket_status(
  id SERIAL PRIMARY KEY,
  name VARCHAR(30) NOT NULL
);

-- TAbela de Atendimentos
CREATE TABLE tickets(
  id SERIAL PRIMARY KEY,
  opening_date TIMESTAMP NOT NULL,
  closing_time TIMESTAMP,
  created_by INT NOT NULL REFERENCES users(id),
  type_id INT NOT NULL REFERENCES ticket_types(id),
  status_id INT NOT NULL REFERENCES ticket_status(id),
  product_id INT NOT NULL REFERENCES products(id)
);

-- Tabela de Mensagens de Atendimento
CREATE TABLE ticket_messages(
  id SERIAL PRIMARY KEY,
  message TEXT NOT NULL,
  user_id INT NOT NULL REFERENCES users(id),
  ticket_id INT NOT NULL REFERENCES tickets(id),
  send_date TIMESTAMP NOT NULL
);
