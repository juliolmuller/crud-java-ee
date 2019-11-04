<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container c-main">
    <h2 class="mb-4">
      Categorias de Produtos
    </h2>

    <%-- Botão para abertura de novo atendimento --%>
    <a href="${baseUri}/categorias/nova" class="btn btn-lg btn-primary">
      <i class="fa fa-plus"></i>
      Criar Nova Categoria
    </a>

    <%-- Tabela com atendimentos em aberto --%>
    <div class="mt-5">
      <table class="table table-hover">
        <thead class="c-thead">
          <tr class="text-center">
            <th scope="col">#</th>
            <th scope="col">Descrição</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr class="text-center">
            <th scope="row">2001</th>
            <td>Sabonetes</td>
            <td>
              <a href="${baseUri}/categorias/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/categorias/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/categorias/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">2002</th>
            <td>Maquiagens</td>
            <td>
              <a href="${baseUri}/categorias/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/categorias/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/categorias/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">2003</th>
            <td>Batons</td>
            <td>
              <a href="${baseUri}/categorias/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/categorias/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/categorias/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">2004</th>
            <td>Xampus Femininos</td>
            <td>
              <a href="${baseUri}/categorias/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/categorias/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/categorias/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">2005</th>
            <td>Xampus Masculinos</td>
            <td>
              <a href="${baseUri}/categorias/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/categorias/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/categorias/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </main>

  <%-- Rodapé da página --%>
  <t:footer />

</t:baseLayout>
