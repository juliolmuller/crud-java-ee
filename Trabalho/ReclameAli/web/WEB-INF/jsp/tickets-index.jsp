<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

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
