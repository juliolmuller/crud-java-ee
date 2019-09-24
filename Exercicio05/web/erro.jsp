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
    <div class="wrapper fade-in-down">
      <div class="form-content">
        <div class="fade-in first">
          <img src="img/uncheck-icon.png" id="icon" alt="Ícone de erro" />
        </div>
        <h3 class="mb-5 fade-in third text-danger">
          ${msg}
        </h3>
        <div class="list-group list-group-flush fade-in then ">
          <a href="${page}" class="list-group-item underline-hover">
            Acessar o formulário de login
          </a>
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
