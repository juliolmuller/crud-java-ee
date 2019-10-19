<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container">
    <h2 class="mb-4">
      Nova Categoria
    </h2>

    <!-- Formulário para criação de categoria -->
    <form action="categorias.html" method="POST">
      <div class="row">
        <div class="col-12 jsutify-content-between">
          <button type="submit" class="btn btn-primary float-right w-25">
            <i class="far fa-save"></i>
            Salvar
          </button>
          <h3 class="mb-3 h4">Dados Cadastrais</h3>
        </div>
        <div class="col-12 col-md-8">
          <div class="form-group">
            <label for="categoria-nome">Nome da categoria:</label>
            <input type="text" id="categoria-nome" class="form-control" name="nome" placeholder="Digite um nome entre 5 e 32 caracteres" minlengh="5" maxlengh="32" />
          </div>
        </div>
      </div>
    </form>
  </main>

</t:baseLayout>
