<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>

<!DOCTYPE html>
<html lan="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>
      Web 2 :: Exercício 06
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/window-down.css" />
  </head>
  <body>
    <div class="wrapper fade-in-down">
      <div class="form-content">
        <div class="fade-in first">
          <img src="${pageContext.request.contextPath}/img/uncheck-icon.png" id="icon" alt="Ícone de erro" />
        </div>
        <h3 class="mb-5 fade-in third text-danger">
          <c:out value="${pageContext.exception.message}" />
        </h3>
        <div class="text-danger fade-in then ">
          ${pageContext.out.flush()}
          ${pageContext.exception.printStackTrace(pageContext.response.writer)}
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
