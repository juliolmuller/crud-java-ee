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

    <%-- Formulário de cadastro de clientes--%>
    <main  class="wrapper with-menu fade-in-down">
      <% if (request.getAttribute("erro") != null) { %>
        <div class="container">
          <div class="alert alert-danger w-100 text-center">${erro}</div>
        </div>
      <% } %>
      <div class="form-content">
        <h3 class="mt-3">
          <% if (request.getAttribute("cliente") != null && request.getAttribute("erro") == null) {
            if (request.getAttribute("readOnly") == null) { %>
              Alteração do Cliente #${cliente.id}
            <% } else { %>
              Cliente #${cliente.id}
            <% }
          } else { %>
              Novo Cliente
          <% } %>
        </h3>
        <form action="${pageContext.request.contextPath}/clientes-${(cliente != null && erro == null) ? "alterar" : "novo"}" method="POST">
          <input type="hidden" id="id" name="id" value="${cliente.id}" />
          <input
            type="number"
            id="cpf"
            class="fade-in then"
            placeholder="CPF do Cliente"
            name="cpf"
            value="${cliente.cpf}"
            step="1"
            min="1"
            max="99999999999"
            required
            ${readOnly ? "readonly" : "autofocus"}
          />
          <input
            type="text"
            id="nome"
            class="fade-in then"
            placeholder="Nome do Cliente"
            name="nome"
            value="${cliente.nome}"
            required
            ${readOnly ? "readonly" : ""}
          />
          <input
            type="email"
            id="email"
            class="fade-in then"
            placeholder="Email do Cliente"
            name="email"
            value="${cliente.email}"
            required
            ${readOnly ? "readonly" : ""}
          />
          <input
            type="date"
            id="nasc"
            class="fade-in then"
            placeholder="Data de Nascimento"
            name="nasc"
            value="${cliente.dataNasc}"
            required
            ${readOnly ? "readonly" : ""}
          />
          <input
            type="number"
            id="cep"
            class="fade-in then"
            placeholder="CEP"
            name="cep"
            value="${cliente.endereco.cep}"
            step="1"
            min="1000"
            max="99999999"
            required
            ${readOnly ? "readonly" : ""}
            onkeyup="buscarCEP(event)"
          />
          <input
            type="text"
            id="rua"
            class="fade-in then"
            placeholder="Endereço do cliente"
            name="rua"
            value="${cliente.endereco.rua}"
            required
            readonly
          />
          <input
            type="number"
            id="numero"
            class="fade-in then"
            placeholder="Logradouro"
            name="numero"
            value="${cliente.endereco.numero}"
            step="1"
            min="0"
            required
            ${readOnly ? "readonly" : ""}
          />
          <input
            type="text"
            id="cidade"
            class="fade-in then"
            placeholder="Cidade"
            name="cidade"
            value="${cliente.endereco.cidade}"
            required
            readonly
          />
          <input
            type="text"
            id="estado"
            class="fade-in then"
            placeholder="Unidade Federativa"
            name="estado"
            value="${cliente.endereco.uf}"
            required
            readonly
          />
          <div class="row">
            <div class="col-6">
              <% if (request.getAttribute("readOnly") == null) { %>
                <button
                  type="submit"
                  id="btn-ok"
                >Salvar</button>
              <% } else { %>
                <a
                  href="${pageContext.request.contextPath}/clientes-alterar?id=${cliente.id}"
                  id="btn-ok"
                  class="button"
                >Editar</a>
              <% } %>
            </div>
            <div class="col-6">
              <a
                href="${pageContext.request.contextPath}/clientes"
                id="btn-cancel"
                class="button"
              >${readOnly ? "Voltar" : "Cancelar"}</a>
            </div>
          </div>
        </form>
      </div>
    </main>

    <%-- Arquivos de scripts --%>
    <script src="js/jquery.min.js"></script>
    <script src="js/manage-customers.js"></script>
  </body>
</html>
