<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/jsp/erro.jsp" %>

<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>
      Web 2 :: Exercício 08
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/window-down.css" />
  </head>
  <body>

    <div class="wrapper fade-in-down">
      <div class="form-content">
        <div class="my-3 fade-in then">
          <img src="${pageContext.request.contextPath}/img/tads-blue.png" id="icon" alt="Ícone do sistema" />
        </div>
        <c:if test="${msg != null}">
          <div class="alert alert-${cor} border-${cor}">
            <c:out value="${msg}" />
          </div>
        </c:if>
        <form action="login" method="POST">
          <input
            type="text"
            id="user"
            class="fade-in then"
            placeholder="Nome do Usuário"
            name="login"
            required autofocus
          />
          <input
            type="password"
            id="password"
            class="fade-in then"
            placeholder="Senha de Acesso"
            name="senha"
            required
          />
          <button
            type="submit"
            class="fade-in then"
          >Entrar</button>
        </form>
        <div id="form-footer fade-in then">
          Em caso de problemas, contate-nos:<br>
          <a href="mailto:${configuracao.emailAdmin}" class="underline-hover">
            <c:out value="${configuracao.emailAdmin}" />
          </a>
        </div>
      </div>
    </div>
  </body>
</html>
