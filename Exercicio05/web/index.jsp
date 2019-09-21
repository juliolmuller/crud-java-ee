
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
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
      <div id="form-content">
        <div class="my-3 fade-in first">
          <img src="img/03ads.png" id="icon" alt="Ícone do sistema" />
        </div>
        <% if (request.getAttribute("msg") != null) { %>
          <div class="alert alert-danger border-danger">
            ${msg}
          </div>
        <% } %>
        <form action="login" method="POST">
          <input
            type="text"
            id="user"
            class="fade-in second"
            placeholder="Nome do Usuário"
            name="login"
            required autofocus
          />
          <input
            type="password"
            id="password"
            class="fade-in third"
            placeholder="Senha de Acesso"
            name="senha"
            required
          />
          <button
            type="submit"
            class="fade-in fourth"
          >Entrar</button>
        </form>
        <div id="form-footer">
          Em caso de problemas, contate-nos:
          <a href="mailto:${configuracao.emailAdmin}" class="underline-hover">
            ${configuracao.emailAdmin}
          </a>
        </div>
      </div>
    </div>
  </body>
</html>
