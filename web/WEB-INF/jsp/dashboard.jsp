<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"  %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container c-main">
    <h1 class="mb-4">
      Bem-vindo(a), <strong><c:out value="${userCredentials.firstName}" /></strong>
    </h1>

    <%-- Apresentar informações gerais ao usuário --%>
    <div class="mt-5">
      <div class="d-flex">
        <c:forEach var="f" items="${feed}">
          <div class="card border border-dark c-card">
            <div class="card-body text-center c-card-body">
              <p class="card-text display-2 text-${f.color}">
                ${f.icon}<strong>${f.main}</strong>
              </p>
              <p class="card-text h5 text-${f.color}">
                ${f.text}
              </p>
            </div>
            <a href="${baseUri}${f.link}" class="stretched-link h-0"></a>
          </div>
        </c:forEach>
      </div>
    </div>
  </main>

  <%-- Rodapé da página --%>
  <t:footer />

</t:baseLayout>
