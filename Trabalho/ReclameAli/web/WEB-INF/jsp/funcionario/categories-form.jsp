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
            <a class="nav-link" href="index.html">Início</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="atendimentos.html">Atendimentos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="categorias.html">Cadastro de Categorias</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="produtos.html">Cadastro de Produtos</a>
          </li>
        </ul>
      </div>
      <div class="form-inline">
        <a href="../index.html" class="btn btn-sm btn-outline-danger text-white my-2 my-sm-0">
          <i class="fas fa-door-open"></i>
          Sair
        </a>
      </div>
    </nav>
  </header>

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
