<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>
      RECLAME ALI :: [Trabalho Web 2 (parte 1) - Protótipo]
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
  </head>
  <body>

    <!-- Cabeçalho da página -->
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
              <a class="nav-link active" href="atendimentos.html">Atendimentos</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="colaboradores.html">Cadastro de Colaboradores</a>
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

    <!-- Corpo da página -->
    <main class="container">
      <h2 class="mb-4">
        Atendimentos
      </h2>

      <!-- Filtro de visualização de atendimentos -->
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

        <!-- Tabela de atendimentos -->
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

    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
  </body>
</html>
