<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container c-main">
    <div class="d-flex flex-wrap justify-content-between align-items-start">
      <div>
        <h1 class="mb-4">
          Cadastro de Colaboradores
        </h1>
      </div>

      <%-- Botão para cadastro de novo funcionário --%>
      <button type="button" class="btn btn-primary mt-1" onclick="createUser()">
        <i class="fa fa-plus"></i>
        Novo Colaborador
      </button>
    </div>

    <%-- Tabela com usuários (funcionarios e gerentes) --%>
    <div class="mt-5">
      <table id="user-table" class="table table-hover">
        <thead class="c-thead">
          <tr class="text-center">
            <th scope="col">CPF</th>
            <th scope="col">Nome Completo</th>
            <th scope="col">Nascido em</th>
            <th scope="col">Telefone</th>
            <th scope="col">Gerente</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${empty users}">
              <tr><td colspan="3" class="h4 py-4">Nenhum colaborador cadastrado.</td></tr>
            </c:when>
            <c:otherwise>
              <c:forEach var="user" items="${users}">
                <tr>
                  <th scope="row" class="text-center">
                    <t:printCpf value="${user.cpf}" />
                  </th>
                  <td class="text-left">
                    <c:out value="${user.firstName} ${user.lastName}" />
                  </td>
                  <td class="text-center">
                    <fmt:setLocale value="pt_BR"/>
                    <fmt:parseDate value="${user.dateBirth}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
                    <fmt:formatDate value="${parsedDate}" type="date" pattern="dd-MMM-yyyy" />
                  </td>
                  <td class="text-center">
                    <t:printPhoneNumber value="${user.phone}" />
                  </td>
                  <td class="text-center">
                    <c:if test="${user.role == 'gerente'}">
                      <i class="fas fa-user-check"></i>
                    </c:if>
                  </td>
                  <td class="text-right">
                    <button type="button" class="btn btn-sm btn-info" title="Editar" onclick="editUser(<c:out value="${user.id}" />)"><i class="fas fa-edit"></i></button>
                    <button type="submit" class="btn btn-sm btn-danger" title="Excluir" onclick="deleteUser(<c:out value="${user.id}" />, event)"><i class="fas fa-trash-alt"></i></button>
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
