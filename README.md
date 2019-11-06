
# Desenvolvimento Web II

Lista de exercícios e trabalhos de senvolvidos para a disciplina de Desenvolvimento Web II, toda em cima deJava EE.

Equipe:

- [André Antunes](https://github.com/andrekantunes)
- ~~ [Aurélio Matsunaga](https://github.com/aureliomatsunaga)~~ *DROPPED*
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

Incremente a aplicação do exercício 03 utilizando-se de Java Beans e DAO.

**NOTA:** para executar a aplicação, é necessário configurar o banco de dados previamente. O SGBD PostgreSQL 11 foi escolhido como servidor de banco de dados para o desenvolvimento, portanto, crie um banco de dados e configure o esquema a partir do arquivo `db-setup.sql` (em [`src/java/br/ufpr/tads/web2/dao/`](./Exercicio04/src/java/br/ufpr/tads/web2/dao/db-setup.sql)). Se utilizar outro SGBD, talvez seja necessário adaptar o SQL que seja compatível (como, por exemplo, o uso de `SERIAL` para incrementação automática de ID dos registros) **IMPORTANTE:** para configurar o acesso ao banco de dados no seu ambiente de desenvolvimento, crie uma cópia do arquivo `db.properties.example` (em [`src/java/br/ufpr/tads/web2/dao/`](./Exercicio04/src/java/br/ufpr/tads/web2/dao/db.properties.example)), renomeando-o para `db.properties`.

## [Exercício 05](./Exercicio05/Exercício%2005%20-%20CRUD.pdf)

Incremente a aplicação do exercício 04 elaborando um CRUD completo com a **tabela de clientes**.

**NOTA:** as orientações para se configurar o banco de dados são as mesmas do **Exercício 04**. Contudo, além da `tb_usuario`, será necessária a criação da tabela `tb_cliente`. [Acesse o script SQL aqui](./Exercicio05/src/java/br/ufpr/tads/web2/dao/db-setup.sql).

## [Exercício 06](./Exercicio06/Exercício%2006%20-%20MVC.pdf)

Incremente a aplicação do exercício 05 implementando alguns design patters, conforme o enunciado.

- Arquivo de configuração do banco de dados: [`src/java/br/ufpr/tads/web2/dao/db.properties.example`](./Exercicio06/src/java/br/ufpr/tads/web2/dao/db.properties.example) (fazer uma cópia e renomeá-la como `db.properties`)
- Scripts de criação de tabelas e inserção de dados: [`src/java/br/ufpr/tads/web2/dao/db-setup.sql`](./Exercicio06/src/java/br/ufpr/tads/web2/dao/db-setup.sql)

## [Exercício 07](./Exercicio07/Exercício%2007%20-%20JSTL_EL.pdf)

- Implementar bibliotecas dJavaScript de máscaras e date-picker;
- Implementar validações de entrada de dados;
- Criar e desmembrar entidades **Cidade** e **Estado**;
- Implementar requisição de cidades via AJAX a partir do estado selecionado;
- Configuração do banco de dados:
  - Arquivo de configuração do banco de dados: [`src/java/br/ufpr/tads/web2/dao/db.properties.example`](./Exercicio07/src/java/br/ufpr/tads/web2/dao/db.properties.example (fazer uma cópia e renomeá-la como `db.properties`)
  - Scripts de criação de tabelas e inserção de dados: [`src/java/br/ufpr/tads/web2/dao/db-setup.sql`](./Exercicio07/src/java/br/ufpr/tads/web2/dao/db-setup.sql)

**Rotas da aplicação:**

| URL                    | Método   | Restrita           | JSP/Servlet         | Ação                                                                                 |
| ---------------------- | -------- | ------------------ | ------------------- | ------------------------------------------------------------------------------------ |
| `/` ou `/index.jsp`    | *GET*    | :x:                | `/index.jsp`        | Exibe formulário de acesso ao sistema.                                               |
| `/login`               | *POST*   | :x:                | `LoginServlet`      | Valida os parâmetros **login** e **senha** e guarda dados na sessão do usuário.      |
| `/logout`              | *GET*    | :heavy_check_mark: | `LogoutServlet`     | Invalida a sessão existente.                                                         |
| `/portal.jsp`          | *GET*    | :heavy_check_mark: | `/portal.jsp`       | Exibe a página inicial do sistema, com menu para formulários de usuários e clientes. |
| `/usuarios`            | *GET*    | :heavy_check_mark: | `ApiUsuarioServlet` | Exibe a SPA para gerenciamento de usuários.                                          |
| `/api/usuarios`        | *GET*    | :heavy_check_mark: | `ApiUsuarioServlet` | Retorna todos os registros de usuários no formato JSON.                              |
| `/api/usuarios`        | *POST*   | :heavy_check_mark: | `ApiUsuarioServlet` | Salva um novo registro de usuário.                                                   |
| `/api/usuarios`        | *PUT*    | :heavy_check_mark: | `ApiUsuarioServlet` | Atualiza um registro de usuário existente.                                           |
| `/api/usuarios`        | *DELETE* | :heavy_check_mark: | `ApiUsuarioServlet` | Exclui um registro de usuário.                                                       |
| `/clientes`            | *GET*    | :heavy_check_mark: | `ClienteServlet`    | Exibe uma lista de todos os clientes cadastrados.                                    |
| `/clientes/novo`       | *GET*    | :heavy_check_mark: | `ClienteServlet`    | Exibe o formulário para criação de novo cliente.                                     |
| `/clientes/novo`       | *POST*   | :heavy_check_mark: | `ClienteServlet`    | Lê os parâmetros recebidos e insere um novo registro de cliente.                     |
| `/clientes/visualizar` | *GET*    | :heavy_check_mark: | `ClienteServlet`    | Exibe os dados de um cliente em modo leitura.                                        |
| `/clientes/atualizar`  | *GET*    | :heavy_check_mark: | `ClienteServlet`    | Exibe o formulário para edição de cliente existente.                                 |
| `/clientes/atualizar`  | *POST*   | :heavy_check_mark: | `ClienteServlet`    | Lê os parâmetros recebidos e atualiza o registro existente.                          |
| `/clientes/excluir`    | *GET*    | :heavy_check_mark: | `ClienteServlet`    | Executa a exclusão de um registro de cliente a partir de um parâmetro **ID**..       |
