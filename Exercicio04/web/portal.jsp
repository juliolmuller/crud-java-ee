<%@page import="br.ufpr.tads.web2.beans.LoginBean" %>

<%
  // Validar se usuário está logado
  if (session == null || session.getAttribute("login") == null) {
      request.setAttribute("msg", "Autentique-se antes, Zé Orelha!");
      request.setAttribute("page", request.getContextPath() + "/");
      try {
          request.getRequestDispatcher("erro.jsp").forward(request, response);
          return;
      } catch (ServletException e) {
          e.getStackTrace();
      }
  }

  // Recuperar dados de sessão
  LoginBean usuario = (LoginBean) session.getAttribute("login");
%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lan="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>
      Web 2 :: Exercício 04
    </title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/login-styles.css" />
  </head>
  <body>

    <%-- Cabeçalho da página --%>
    <header>
      <div id="wrapper-out fade-in-down">
        <div class="fixed-top" id="fake-navbar">
          <div class="container">
            <div class="d-flex justify-content-between">
              <div >
                <h2 class="text-white mt-3">
                  Portal - Exercício 04
                </h2>
              </div>
              <div>
                <a href="<%= request.getContextPath() %>/logout" class="btn btn-danger rounded">
                  Logout
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>

    <%-- Janela de notificação --%>
    <div class="wrapper fade-in-down">
      <div id="form-content">
        <div class="fade-in first">
          <img src="img/check-icon.png" id="icon" alt="Ícone de erro" />
        </div>
        <h3 class="mb-5 fade-in third text-success">
          Bem-vindo, <%= usuario.getNomeUsuario() %>
        </h3>
        <a href="inserir.jsp" class="underline-hover mb-5">
          Gerenciador de Usuários &gt;&gt;&gt;
        </a>
        <div id="form-footer">
            Em caso de problemas, contate-nos:
            <jsp:useBean id="configuracao" class="br.ufpr.tads.web2.beans.ConfigBean" scope="application" />
            <a href="mailto:<jsp:getProperty name="configuracao" property="emailAdmin" />" class="underline-hover">
              <jsp:getProperty name="configuracao" property="emailAdmin" />
            </a>
        </div>
      </div>
    </div>
  </body>
</html>
