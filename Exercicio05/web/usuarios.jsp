<%
  // Validar se usuário está logado
  if (session.getAttribute("login") == null) {
    try {
      request.setAttribute("msg", "Faça-me o favor de logar antes!");
      request.setAttribute("cor", "danger");
      request.getRequestDispatcher("index.jsp").forward(request, response);
      return;
    } catch (ServletException e) {}
  }
%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lan="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>
      Web 2 :: Exercício 05
    </title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/window-down.css" />
  </head>
  <body>

    <%-- Cabeçalho da página --%>
    <header class="container-fluid fade-in" style="z-index:99;">
      <div id="fake-navbar" class="fixed-top fade-in.fourth" >
        <div class="float-left">
          <img src="img/tads-white.png" class="system-icon" alt="Ícone do sistema" />
        </div>
        <a href="<%= request.getContextPath() %>/logout" class="float-right btn btn-danger rounded">
          Sair
        </a>
        <div class="container text-center">
          <h1 class="text-white h3 align-bottom my-2">
            Cadastro de Usuários - Exercício 05
          </h1>
        </div>
      </div>
    </header>

    <%-- Inicio da main --%>
    <main  class="wrapper with-menu fade-in-down">
      <div class="container">
        <div class="alert alert-success w-100 text-center" style="display:none"></div>
        <div class="alert alert-danger w-100 text-center" style="display:none"></div>
      </div>

      <%-- Formulário --%>
      <div class="form-content">
        <h3 class="mt-3">
          Cadastro de Usuários
        </h3>
        <form>
          <input type="hidden" name="id" id="id" />
          <input
            type="text"
            id="nome"
            class="fade-in then"
            placeholder="Nome do Usuário"
            name="nome"
            required autofocus
          />
          <input
            type="text"
            id="login"
            class="fade-in then"
            placeholder="Login de Acesso"
            name="login"
            required
          />
          <input
            type="password"
            id="senha"
            class="fade-in then"
            placeholder="Senha de Acesso"
            name="senha"
            required
          />
          <div class="row">
            <div class="col-6">
              <button
                type="submit"
                id="btn-ok"
              >Cadastrar</button>
            </div>
            <div class="col-6">
              <button
                type="reset"
                id="btn-cancel"
              >Cancelar</button>
            </div>
          </div>
        </form>
      </div>

      <%-- Lista de usuários cadastrados --%>
      <div class="form-content form-content-large">
        <table class="table table-stripped">
          <thead class="table-primary">
            <tr>
              <th scope="col">#</th>
              <th scope="col">Nome do usuário</th>
              <th scope="col">Login de acesso</th>
              <th scope="col">Senha</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody></tbody>
        </table>
      </div>
    </main>

    <%-- Arquivos de scripts --%>
    <script src="js/jquery.min.js"></script>
    <script src="js/manage-users.js"></script>
  </body>
</html>
