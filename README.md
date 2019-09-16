
# Desenvolvimento Web II

Lista de exercícios e trabalhos de senvolvidos para a disciplina de Desenvolvimento Web II, toda em cima deJava EE.

Equipe:

- [André Antunes](https://github.com/andrekantunes)
- [Aurélio Matsunaga](https://github.com/aureliomatsunaga)
- [Cassiano VIdal](https://github.com/kruchelski)
- [Júlio Müller](https://github.com/juliolmuller)
- [Wesley Caetano](https://github.com/vvesleyc)

## [Exercício 01](./Exercicio01/Exercício%2001%20-%20Servlets_JSP%20Básico.pdf)

Construir uma página simples utilizando Servlets e outra usando JSP.

## [Exercício 02](./Exercicio02/Exercício%2002%20-%20Servlets%20%2B%20Formulário.pdf)

Usando Servlets, construir um sisteminha simples de login e cadastro de usuários.

## [Exercício 03](./Exercicio03/Exercício%2003%20-%20Servlets%20+%20Login%20+%20Redirecionamentos.pdf)

Adicione conexão a banco de dados ao projeto e redirecionamentos a fim de prevenir que o usuário acesse páginas restritas apenas a usuários logados.

## [Exercício 04](./Exercicio04/Exercício%2004%20-%20Servlets%20+%20Login%20+%20Redirecionamentos%20+%20Java%20Beans.pdf)

Incremente a aplicação do exercício utilizando-se de Java Beans e DAO.

**NOTA:** para executar a aplicação, é necessário configurar o banco de dados previamente. O SGBD PostgreSQL 11 foi escolhido como servidor de banco de dados para o desenvolvimento, portanto, crie um banco de dados e configure o esquema a partir do arquivo `db-setup.sql` (em [`src/java/br/ufpr/tads/web2/dao/`](./Exercicio04/src/java/br/ufpr/tads/web2/dao/db-setup.sql)). Se utilizar outro SGBD, talvez seja necessário adaptar o SQL que seja compatível (como, por exemplo, o uso de `SERIAL` para incrementação automática de ID dos registros) **IMPORTANTE:** para configurar o acesso ao banco de dados no seu ambiente de desenvolvimento, crie uma cópia do arquivo `db.properties.example` (em [`src/java/br/ufpr/tads/web2/dao/`](./Exercicio04/src/java/br/ufpr/tads/web2/dao/db.properties.example)), renomeando-o para `db.properties`.
