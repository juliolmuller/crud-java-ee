<%
  // Validar se usuário está logado
  if (session.getAttribute("login") == null) {
      request.setAttribute("msg", "Autentique-se antes, Zé Orelha!");
      request.setAttribute("page", request.getContextPath() + "/");
      try {
          request.getRequestDispatcher("erro.jsp").forward(request, response);
          return;
      } catch (ServletException e) {
          e.getStackTrace();
      }
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
    <link rel="stylesheet" href="css/portal-styles.css" />
  </head>
  <body>

    <%-- Cabeçalho --%>
    <header>
      <div id="wrapper-out">
        <div class="row justify-content-center fixed-top" id="fake-navbar">
          <div class="col-2">
            <p class="text-white text-center">
              Olá, <%= usuario.getNomeUsuario() %>
            </p>
          </div>
          <div class="col-8">
            <h2 class="text-center">
              Portal - Exercício 05
            </h2>
          </div>
          <div class="col-2">
            <a href="<%= request.getContextPath() %>/logout" class="btn btn-danger rounded">
              Logout
            </a>
          </div>
        </div>
      </div>
    </header>

    <%-- Inicio da main --%>
    <main>
      <div class="wrapper">
        <div class="container">
          <div class="alert alert-success w-100 text-center" style="display:none"></div>
          <div class="alert alert-danger w-100 text-center" style="display:none"></div>
        </div>

        <%-- Formulário --%>
        <div id="form-content">
          <div class="container text-center">
            <h3 class="mt-3">
              Cadastro de Usuários
            </h3>
            <form>
              <input type="hidden" name="id" id="id" />
              <div class="form-group">
                <label for="nome">Nome do usuário</label>
                <input
                  type="text"
                  id="nome"
                  class="form-control"
                  name="nome"
                  placeholder="Insira um nome"
                  autofocus
                />
              </div>
              <div class="form-group">
                <label for="login">Login de acesso</label>
                <input
                  type="text"
                  id="login"
                  class="form-control"
                  name="login"
                  placeholder="Insira um login"
                />
              </div>
              <div class="form-group">
                <label for="senha">Senha de acesso</label>
                <input
                  type="password"
                  id="senha"
                  class="form-control"
                  name="senha"
                  placeholder="Insira uma senha"
                />
              </div>
              <button type="submit" id="btn-ok">
                Cadastrar
              </button>
              <button type="reset" id="btn-cancel">
                Cancelar
              </button>
            </form>
          </div>
        </div>
      </div>

      <%-- Lista de usuários cadastrados --%>
      <div class="row justify-content-center">
        <div class="col-9">
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
      </div>
    </main>

    <%-- Arquivos de scripts --%>
    <script src="js/jquery.min.js"></script>
    <script src="js/portal-scripts.js"></script>
  </body>
</html>
