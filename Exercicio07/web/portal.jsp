<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/jsp/erro.jsp" %>

<!DOCTYPE html>
<html lan="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>
      Web 2 :: Exercício 07
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/window-down.css" />
  </head>
  <body>

    <%-- Cabeçalho da página --%>
    <header class="container-fluid fade-in" style="z-index:99;">
      <div id="fake-navbar" class="fixed-top fade-in.fourth" >
        <div class="float-left">
          <img src="${pageContext.request.contextPath}/img/tads-white.png" class="system-icon" alt="Ícone do sistema" />
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
              Exercício 07
            </h1>
          </a>
        </div>
      </div>
    </header>

    <%-- Janela de notificação --%>
    <div class="wrapper with-menu fade-in-down">
      <div class="form-content">
        <div class="my-5 fade-in then">
          <img src="${pageContext.request.contextPath}/img/avatar.svg" id="icon" alt="Avatar do usuário" />
        </div>
        <h3 class="mb-5 fade-in then text-primary">
          Bem-vindo, <c:out value="${login.nomeUsuario}" />!
        </h3>
        <div class="list-group list-group-flush fade-in then ">
          <a href="${pageContext.request.contextPath}/usuarios" class="list-group-item underline-hover">Gerenciar Usuários</a>
          <a href="${pageContext.request.contextPath}/clientes" class="list-group-item underline-hover">Gerenciar Clientes</a>
        </div>
        <div id="form-footer fade-in then" style="margin-top:3rem;">
          Em caso de problemas, contate-nos:<br>
          <a href="mailto:${configuracao.emailAdmin}" class="underline-hover">
            <c:out value="${configuracao.emailAdmin}" />
          </a>
        </div>
      </div>
    </div>
  </body>
</html>
