<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <header class="container-fluid bg-dark mb-5">
    <nav class="navbar navbar-expand-lg navbar-dark" role="navigation">
      <a class="navbar-brand" href="index.html">
        <img src="${pageContext.request.contextPath}/img/reclame-ali-white.png" width="30" height="30" class="d-inline-block align-top" alt="Logo do sistema" />
        <span class="text-white-50 h4 c-title">Reclame Ali</span>
      </a>
      <div class="container">
        <ul class="navbar-nav text-white">
          <li class="nav-item">
            <a class="nav-link" href="${baseUri}">Início</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="${baseUri}/atendimentos">Meus Atendimentos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${baseUri}/dados-pessoais">Meus Dados</a>
          </li>
        </ul>
      </div>
      <div class="form-inline">
        <a href="${pageContext.request.contextPath}/" class="btn btn-sm btn-outline-danger text-white my-2 my-sm-0">
          <i class="fas fa-door-open"></i>
          Sair
        </a>
      </div>
    </nav>
  </header>

  <%-- Corpo da página --%>
  <main class="container">
    <h2 class="mb-4">
      Meus Atendimentos
    </h2>

    <%-- Botão para abertura de novo atendimento --%>
    <a href="${baseUri}/atendimentos/novo" class="btn btn-lg btn-primary">
      <i class="fa fa-plus"></i>
      Solicitar Atendimento
    </a>

    <%-- Tabela com atendimentos em aberto --%>
    <div class="mt-5">
      <h3 class="mb-3 h4">Atendimentos em aberto</h3>
      <table class="table table-hover">
        <thead class="c-thead">
          <tr class="text-center">
            <th scope="col">#</th>
            <th scope="col">Produto</th>
            <th scope="col">Categoria</th>
            <th scope="col">Data de Criação</th>
            <th scope="col">Status</th>
          </tr>
        </thead>
        <tbody>
          <tr class="c-clickable text-center" data-href="${baseUri}/atendimentos/editar">
            <th scope="row">100123</th>
            <td>Shampoo Ass-Hair (para cachos ruivos)</td>
            <td>Produto não recebido</td>
            <td>15-set-2019</td>
            <td><span class="badge badge-sm badge-info c-status">Recebido</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="${baseUri}/atendimentos/editar">
            <th scope="row">100123</th>
            <td>Sabonete SOAP (aroma lavanda)</td>
            <td>Produto com defeito</td>
            <td>8-set-2019</td>
            <td><span class="badge badge-sm badge-danger c-status">Contestado</span></td>
          </tr>
        </tbody>
      </table>
    </div>

    <%-- Tabela com atendimentos encerrados --%>
    <div class="mt-5">
      <h3 class="h4">Atendimentos encerrados</h3>
      <table class="table table-hover">
        <thead class="c-thead">
          <tr>
            <th scope="col" class="text-center">#</th>
            <th scope="col">Produto</th>
            <th scope="col" class="text-center">Categoria</th>
            <th scope="col" class="text-center">Data de Criação</th>
            <th scope="col" class="text-center">Status</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th colspan="5" class="py-3 h5 text-center">Nenhum atendimento encerrado</th>
          </tr>
        </tbody>
      </table>
    </div>
  </main>

</t:baseLayout>
