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
    <div class="wrapper fade-in-down">
      <div id="form-content">
        <div class="fade-in first">
          <img src="img/uncheck-icon.png" id="icon" alt="Ícone de erro" />
        </div>
        <h3 class="mb-5 fade-in third text-danger">
          <%= request.getAttribute("msg") %>
        </h3>
        <div id="form-footer">
            <a href="<%= request.getAttribute("page") %>" class="underline-hover">
              Acessar o formulário de login
            </a>
        </div>
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
