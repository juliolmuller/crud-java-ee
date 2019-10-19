<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"  %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container">
    <h2 class="mb-4">
      Bem-vindo(a), <strong>[nome do cliente]</strong>
    </h2>
    <div class="row">
      <div class="card-deck">
        <div class="card border border-primary text-primary">
          <div class="card-body text-center">
            <p class="card-text display-3">
              <strong>2</strong>
            </p>
            <p class="card-text h6">
              atendimento(s)<br> em aberto
            </p>
            <a href="${baseUri}/atendimentos" class="stretched-link h-0"></a>
          </div>
        </div>
        <div class="card border border-primary text-primary">
          <div class="card-body text-center">
            <p class="card-text display-3">
              <i class="fas fa-plus"></i>
            </p>
            <p class="card-text h6">
              Solicitar Atendimento
            </p>
            <a href="${baseUri}/atendimentos/novo" class="stretched-link h-0"></a>
          </div>
        </div>
      </div>
    </div>
  </main>

</t:baseLayout>
