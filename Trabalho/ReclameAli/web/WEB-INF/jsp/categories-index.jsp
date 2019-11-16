<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container c-main">
    <div class="d-flex flex-wrap justify-content-between align-items-start">
      <div>
        <h1 class="mb-4">
          Categorias de Produtos
        </h1>
      </div>

      <%-- Botão para criação de nova categoria--%>
      <button type="button" class="btn btn-primary mt-1" onclick="createCategory()">
        <i class="fa fa-plus"></i>
        Criar Nova Categoria
      </button>
    </div>

    <%-- Tabela com atendimentos em aberto --%>
    <div class="mt-5">
      <table class="table table-hover">
        <thead class="c-thead">
          <tr class="text-center">
            <th scope="col">#</th>
            <th scope="col">Descrição da Categoria</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${empty categories}">
            <p class="h5">Nenhuma categoria cadastrada.</p>
            </c:when>
            <c:otherwise>
              <c:forEach var="category" items="${categories}">
                <tr>
                  <th scope="row" class="text-center"><c:out value="${category.id}" /></th>
                  <td><c:out value="${category.name}" /></td>
                  <td class="text-right">
                    <button type="button" class="btn btn-sm btn-info" title="Editar" onclick="editCategory(<c:out value="${category.id}" />)"><i class="fas fa-edit"></i></button>
                    <button type="submit" class="btn btn-sm btn-danger" title="Excluir" onclick="deleteCategory(<c:out value="${category.id}" />)"><i class="fas fa-trash-alt"></i></button>
                  </td>
                </tr>
              </c:forEach>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>
  </main>

  <%-- Rodapé da página --%>
  <t:footer />

</t:baseLayout>
