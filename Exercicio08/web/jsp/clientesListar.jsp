<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/jsp/erro.jsp" %>

<!DOCTYPE html>
<html lan="pt-BR">
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
              Exercício 08
            </h1>
          </a>
        </div>
      </div>
    </header>

    <%-- Lista de clientes cadastrados --%>
    <main  class="wrapper with-menu fade-in-down">
      <div class="form-content form-content-large">
        <div class="row">
          <div class="col-4"></div>
          <div class="col-4">
            <h3 class="mt-4">Listar Clientes</h3>
          </div>
          <div class="col-4">
            <a href="${pageContext.request.contextPath}/clientes/novo" class="button" style="margin:1rem;">
              <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
                <path d="M352 240v32c0 6.6-5.4 12-12 12h-88v88c0 6.6-5.4 12-12 12h-32c-6.6 0-12-5.4-12-12v-88h-88c-6.6 0-12-5.4-12-12v-32c0-6.6 5.4-12 12-12h88v-88c0-6.6 5.4-12 12-12h32c6.6 0 12 5.4 12 12v88h88c6.6 0 12 5.4 12 12zm96-160v352c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V80c0-26.5 21.5-48 48-48h352c26.5 0 48 21.5 48 48zm-48 346V86c0-3.3-2.7-6-6-6H54c-3.3 0-6 2.7-6 6v340c0 3.3 2.7 6 6 6h340c3.3 0 6-2.7 6-6z"></path>
              </svg>
              Novo Cliente
            </a>
          </div>
        </div>
        <c:choose>
          <c:when test="${empty clientes}">
            <h4>Nenhum cliente cadastrado;</h4>
          </c:when>
          <c:otherwise>
            <table class="table table-stripped">
              <thead class="table-primary">
                <tr>
                  <th scope="col">CPF</th>
                  <th scope="col">Nome Completo</th>
                  <th scope="col">Email</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="cliente" items="${clientes}">
                  <tr>
                    <td><c:out value="${cliente.cpf}" /></td>
                    <td><c:out value="${cliente.nome}" /></td>
                    <td><c:out value="${cliente.email}" /></td>
                    <td>
                      <a
                        href="${pageContext.request.contextPath}/clientes/visualizar?id=${cliente.id}"
                        class="btn btn-sm btn-primary mx-1"
                        title="Visualizar"
                      >
                        <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
                          <path d="M288 144a110.94 110.94 0 0 0-31.24 5 55.4 55.4 0 0 1 7.24 27 56 56 0 0 1-56 56 55.4 55.4 0 0 1-27-7.24A111.71 111.71 0 1 0 288 144zm284.52 97.4C518.29 135.59 410.93 64 288 64S57.68 135.64 3.48 241.41a32.35 32.35 0 0 0 0 29.19C57.71 376.41 165.07 448 288 448s230.32-71.64 284.52-177.41a32.35 32.35 0 0 0 0-29.19zM288 400c-98.65 0-189.09-55-237.93-144C98.91 167 189.34 112 288 112s189.09 55 237.93 144C477.1 345 386.66 400 288 400z"></path>
                        </svg>
                      </a>
                      <a
                        href="${pageContext.request.contextPath}/clientes/alterar?id=${cliente.id}"
                        class="btn btn-sm btn-info mx-1"
                        title="Editar"
                      >
                        <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
                          <path d="M402.3 344.9l32-32c5-5 13.7-1.5 13.7 5.7V464c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V112c0-26.5 21.5-48 48-48h273.5c7.1 0 10.7 8.6 5.7 13.7l-32 32c-1.5 1.5-3.5 2.3-5.7 2.3H48v352h352V350.5c0-2.1.8-4.1 2.3-5.6zm156.6-201.8L296.3 405.7l-90.4 10c-26.2 2.9-48.5-19.2-45.6-45.6l10-90.4L432.9 17.1c22.9-22.9 59.9-22.9 82.7 0l43.2 43.2c22.9 22.9 22.9 60 .1 82.8zM460.1 174L402 115.9 216.2 301.8l-7.3 65.3 65.3-7.3L460.1 174zm64.8-79.7l-43.2-43.2c-4.1-4.1-10.8-4.1-14.8 0L436 82l58.1 58.1 30.9-30.9c4-4.2 4-10.8-.1-14.9z"/>
                        </svg>
                      </a>
                      <a
                        href="${pageContext.request.contextPath}/clientes/excluir?id=${cliente.id}"
                        class="btn btn-sm btn-danger mx-1"
                        title="Excluir"
                        onclick="if (!confirm('Tem certeza de que quer excluir o cliente #${cliente.id}?')) event.preventDefault();"
                      >
                        <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
                          <path d="M268 416h24a12 12 0 0 0 12-12V188a12 12 0 0 0-12-12h-24a12 12 0 0 0-12 12v216a12 12 0 0 0 12 12zM432 80h-82.41l-34-56.7A48 48 0 0 0 274.41 0H173.59a48 48 0 0 0-41.16 23.3L98.41 80H16A16 16 0 0 0 0 96v16a16 16 0 0 0 16 16h16v336a48 48 0 0 0 48 48h288a48 48 0 0 0 48-48V128h16a16 16 0 0 0 16-16V96a16 16 0 0 0-16-16zM171.84 50.91A6 6 0 0 1 177 48h94a6 6 0 0 1 5.15 2.91L293.61 80H154.39zM368 464H80V128h288zm-212-48h24a12 12 0 0 0 12-12V188a12 12 0 0 0-12-12h-24a12 12 0 0 0-12 12v216a12 12 0 0 0 12 12z"/>
                        </svg>
                      </a>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </c:otherwise>
        </c:choose>
      </div>
    </main>
  </body>
</html>
