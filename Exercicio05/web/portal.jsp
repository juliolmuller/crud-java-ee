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
            Portal - Exercício 05
          </h1>
        </div>
      </div>
    </header>

    <%-- Janela de notificação --%>
    <div class="wrapper with-menu fade-in-down">
      <div id="form-content">
        <div class="my-5 fade-in then">
          <img src="img/avatar.svg" id="icon" alt="Avatar do usuário" />
        </div>
        <h3 class="mb-5 fade-in then text-primary">
          Bem-vindo, ${login.nomeUsuario}!
        </h3>
        <div class="list-group list-group-flush fade-in then ">
          <a href="usuarios.jsp" class="list-group-item underline-hover">Gerenciar Usuários</a>
          <a href="clientes" class="list-group-item underline-hover">Gerenciar Clientes</a>
        </div>
        <div id="form-footer fade-in then" style="margin-top:3rem;">
          Em caso de problemas, contate-nos:<br>
          <a href="mailto:${configuracao.emailAdmin}" class="underline-hover">
            ${configuracao.emailAdmin}
          </a>
        </div>
      </div>
    </div>
  </body>
</html>
