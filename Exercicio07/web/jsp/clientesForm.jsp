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

    <%-- Formulário de cadastro de clientes --%>
    <main  class="wrapper with-menu fade-in-down">
      <c:if test="${erro != null}">
        <div class="container">
          <div class="alert alert-danger w-100 text-center">
            <c:out value="${erro}" />
          </div>
        </div>
      </c:if>
      <div class="form-content">
        <h3 class="mt-3">
          <c:choose>
            <c:when test="${cliente != null && erro == null}">
              <c:choose>
                <c:when test="${readOnly == null}">
                  Alteração do Cliente #<c:out value="${cliente.id}" />
                </c:when>
                <c:otherwise>
                  Cliente #<c:out value="${cliente.id}" />
                </c:otherwise>
              </c:choose>
            </c:when>
            <c:otherwise>
              Novo Cliente
            </c:otherwise>
          </c:choose>
        </h3>
        <form action="${pageContext.request.contextPath}/clientes/${(cliente != null && erro == null) ? "alterar" : "novo"}" method="POST">
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
            ${cliente != null ? "readonly" : "autofocus"}
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
            ${cliente != null ? "readonly" : ""}
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
            ${readOnly ? "readonly" : ""}
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
              <c:choose>
                <c:when test="${readOnly == null}">
                  <button type="submit" id="btn-ok">
                    <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
                      <path fill="currentColor" d="M433.941 129.941l-83.882-83.882A48 48 0 0 0 316.118 32H48C21.49 32 0 53.49 0 80v352c0 26.51 21.49 48 48 48h352c26.51 0 48-21.49 48-48V163.882a48 48 0 0 0-14.059-33.941zM272 80v80H144V80h128zm122 352H54a6 6 0 0 1-6-6V86a6 6 0 0 1 6-6h42v104c0 13.255 10.745 24 24 24h176c13.255 0 24-10.745 24-24V83.882l78.243 78.243a6 6 0 0 1 1.757 4.243V426a6 6 0 0 1-6 6zM224 232c-48.523 0-88 39.477-88 88s39.477 88 88 88 88-39.477 88-88-39.477-88-88-88zm0 128c-22.056 0-40-17.944-40-40s17.944-40 40-40 40 17.944 40 40-17.944 40-40 40z"></path>
                    </svg>
                    Salvar
                  </button>
                </c:when>
                <c:otherwise>
                  <a href="${pageContext.request.contextPath}/clientes/alterar?id=${cliente.id}" id="btn-ok" class="button">
                    <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
                      <path d="M402.3 344.9l32-32c5-5 13.7-1.5 13.7 5.7V464c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V112c0-26.5 21.5-48 48-48h273.5c7.1 0 10.7 8.6 5.7 13.7l-32 32c-1.5 1.5-3.5 2.3-5.7 2.3H48v352h352V350.5c0-2.1.8-4.1 2.3-5.6zm156.6-201.8L296.3 405.7l-90.4 10c-26.2 2.9-48.5-19.2-45.6-45.6l10-90.4L432.9 17.1c22.9-22.9 59.9-22.9 82.7 0l43.2 43.2c22.9 22.9 22.9 60 .1 82.8zM460.1 174L402 115.9 216.2 301.8l-7.3 65.3 65.3-7.3L460.1 174zm64.8-79.7l-43.2-43.2c-4.1-4.1-10.8-4.1-14.8 0L436 82l58.1 58.1 30.9-30.9c4-4.2 4-10.8-.1-14.9z"/>
                    </svg>
                    Editar
                  </a>
                </c:otherwise>
              </c:choose>
            </div>
            <div class="col-6">
              <a href="${pageContext.request.contextPath}/clientes" id="btn-cancel" class="button">
                <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                  <path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8zm0 448c-110.5 0-200-89.5-200-200S145.5 56 256 56s200 89.5 200 200-89.5 200-200 200zm101.8-262.2L295.6 256l62.2 62.2c4.7 4.7 4.7 12.3 0 17l-22.6 22.6c-4.7 4.7-12.3 4.7-17 0L256 295.6l-62.2 62.2c-4.7 4.7-12.3 4.7-17 0l-22.6-22.6c-4.7-4.7-4.7-12.3 0-17l62.2-62.2-62.2-62.2c-4.7-4.7-4.7-12.3 0-17l22.6-22.6c4.7-4.7 12.3-4.7 17 0l62.2 62.2 62.2-62.2c4.7-4.7 12.3-4.7 17 0l22.6 22.6c4.7 4.7 4.7 12.3 0 17z"></path>
                </svg>
                Voltar
              </a>
            </div>
          </div>
        </form>
      </div>
    </main>

    <%-- Arquivos de scripts --%>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/manage-customers.js"></script>
  </body>
</html>
