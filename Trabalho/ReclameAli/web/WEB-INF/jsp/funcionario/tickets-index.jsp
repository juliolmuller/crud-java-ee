<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container">
    <h2 class="mb-4">
      Atendimentos
    </h2>

    <%-- Filtro de visualização de atendimentos --%>
    <div class="mt-3">
      <div class="form-inline p-2">
        <label for="filtro-atendimentos">
          <i class="fas fa-filter"></i>
          <span class="mx-2">Visualizar:</span>
        </label>
        <select id="filtro-atendimentos" class="form-control">
          <option value="todos">Todos</option>
          <option value="abertos">Abertos</option>
          <option value="vencidos">Vencidos</option>
          <option value="fechados">Fechados</option>
        </select>
      </div>

      <%-- Tabela de atendimentos --%>
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
          <tr class="c-clickable text-center table-danger" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Shampoo Ass-Hair (para cachos ruivos)</td>
            <td>Produto não recebido</td>
            <td>15-set-2019</td>
            <td><span class="badge badge-sm badge-info c-status">Recebido</span></td>
          </tr>
          <tr class="c-clickable text-center table-danger" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Sabonete SOAP (aroma lavanda)</td>
            <td>Produto com defeito</td>
            <td>8-set-2019</td>
            <td><span class="badge badge-sm badge-danger c-status">Contestado</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Shampoo Ass-Hair (para cachos ruivos)</td>
            <td>Produto não recebido</td>
            <td>15-set-2019</td>
            <td><span class="badge badge-sm badge-success c-status">Encerrado</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Sabonete SOAP (aroma lavanda)</td>
            <td>Produto com defeito</td>
            <td>8-set-2019</td>
            <td><span class="badge badge-sm badge-success c-status">Encerrado</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Shampoo Ass-Hair (para cachos ruivos)</td>
            <td>Produto não recebido</td>
            <td>15-set-2019</td>
            <td><span class="badge badge-sm badge-info c-status">Recebido</span></td>
          </tr>
          <tr class="c-clickable text-center table-danger" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Sabonete SOAP (aroma lavanda)</td>
            <td>Produto com defeito</td>
            <td>8-set-2019</td>
            <td><span class="badge badge-sm badge-danger c-status">Contestado</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Shampoo Ass-Hair (para cachos ruivos)</td>
            <td>Produto não recebido</td>
            <td>15-set-2019</td>
            <td><span class="badge badge-sm badge-success c-status">Encerrado</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Sabonete SOAP (aroma lavanda)</td>
            <td>Produto com defeito</td>
            <td>8-set-2019</td>
            <td><span class="badge badge-sm badge-success c-status">Encerrado</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Shampoo Ass-Hair (para cachos ruivos)</td>
            <td>Produto não recebido</td>
            <td>15-set-2019</td>
            <td><span class="badge badge-sm badge-info c-status">Recebido</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Sabonete SOAP (aroma lavanda)</td>
            <td>Produto com defeito</td>
            <td>8-set-2019</td>
            <td><span class="badge badge-sm badge-danger c-status">Contestado</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Shampoo Ass-Hair (para cachos ruivos)</td>
            <td>Produto não recebido</td>
            <td>15-set-2019</td>
            <td><span class="badge badge-sm badge-success c-status">Encerrado</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Sabonete SOAP (aroma lavanda)</td>
            <td>Produto com defeito</td>
            <td>8-set-2019</td>
            <td><span class="badge badge-sm badge-success c-status">Encerrado</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Shampoo Ass-Hair (para cachos ruivos)</td>
            <td>Produto não recebido</td>
            <td>15-set-2019</td>
            <td><span class="badge badge-sm badge-info c-status">Recebido</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Sabonete SOAP (aroma lavanda)</td>
            <td>Produto com defeito</td>
            <td>8-set-2019</td>
            <td><span class="badge badge-sm badge-danger c-status">Contestado</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Shampoo Ass-Hair (para cachos ruivos)</td>
            <td>Produto não recebido</td>
            <td>15-set-2019</td>
            <td><span class="badge badge-sm badge-success c-status">Encerrado</span></td>
          </tr>
          <tr class="c-clickable text-center" data-href="atendimentos-form.html">
            <th scope="row">100123</th>
            <td>Sabonete SOAP (aroma lavanda)</td>
            <td>Produto com defeito</td>
            <td>8-set-2019</td>
            <td><span class="badge badge-sm badge-success c-status">Encerrado</span></td>
          </tr>
        </tbody>
      </table>
    </div>
  </main>

</t:baseLayout>
