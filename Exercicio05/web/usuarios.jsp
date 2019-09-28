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
        <a href="${pageContext.request.contextPath}/logout" class="float-right btn btn-danger rounded">
          <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512">
            <path fill="currentColor" d="M624 448h-80V113.45C544 86.19 522.47 64 496 64H384v64h96v384h144c8.84 0 16-7.16 16-16v-32c0-8.84-7.16-16-16-16zM312.24 1.01l-192 49.74C105.99 54.44 96 67.7 96 82.92V448H16c-8.84 0-16 7.16-16 16v32c0 8.84 7.16 16 16 16h336V33.18c0-21.58-19.56-37.41-39.76-32.17zM264 288c-13.25 0-24-14.33-24-32s10.75-32 24-32 24 14.33 24 32-10.75 32-24 32z"></path>
          </svg>
          Sair
        </a>
        <div class="container text-center">
          <a href="${pageContext.request.contextPath}/portal.jsp">
            <h1 class="text-white h3 align-bottom my-2">
              Exercício 05
            </h1>
          </a>
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
