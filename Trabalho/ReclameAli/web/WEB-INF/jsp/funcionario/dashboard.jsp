<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container c-main">
    <h2 class="mb-4">
      Bem-vindo(a), <strong>[nome do funcionário]</strong>
    </h2>
    <div class="row">
      <div class="card-deck">
        <div class="card border border-primary text-primary">
          <div class="card-body text-center">
            <p class="card-text display-3">
              <strong>48</strong>
            </p>
            <p class="card-text h6">
              atendimento(s)<br> em aberto
            </p>
            <a href="atendimentos.html" class="stretched-link h-0"></a>
          </div>
        </div>
        <div class="card border border-primary text-danger">
          <div class="card-body text-center">
            <p class="card-text display-3">
              <strong>3</strong>
            </p>
            <p class="card-text h6">
              atendimento(s) vencidos
            </p>
            <a href="atendimentos.html" class="stretched-link h-0"></a>
          </div>
        </div>
        <div class="card border border-primary text-primary">
          <div class="card-body text-center">
            <p class="card-text display-3">
              <i class="fas fa-plus"></i>
            </p>
            <p class="card-text h6">
              Criar Nova Categoria
            </p>
            <a href="categorias-form.html" class="stretched-link h-0"></a>
          </div>
        </div>
        <div class="card border border-primary text-primary">
          <div class="card-body text-center">
            <p class="card-text display-3">
              <i class="fas fa-plus"></i>
            </p>
            <p class="card-text h6">
              Criar Novo Produto
            </p>
            <a href="produtos-form.html" class="stretched-link h-0"></a>
          </div>
        </div>
      </div>
    </div>
  </main>

  <%-- Rodapé da página --%>
  <t:footer />

</t:baseLayout>
