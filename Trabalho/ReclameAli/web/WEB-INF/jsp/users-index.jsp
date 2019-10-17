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
            <a class="nav-link" href="${baseUri}">Início</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${baseUri}/atendimentos">Atendimentos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="${baseUri}/colaboradores">Cadastro de Colaboradores</a>
          </li>
        </ul>
      </div>
      <div class="form-inline">
        <a href="${pageContext.request.contextPath}/" class="btn btn-sm btn-outline-danger text-white my-2 my-sm-0">
          <i class="fas fa-door-open"></i>
          Sair
        </a>
      </div>
    </nav>
  </header>

  <%-- Corpo da página --%>
  <main class="container">
    <h2 class="mb-4">
      Cadastro de Funcionários & Gerentes
    </h2>

    <%-- Botão para abertura de novo atendimento --%>
    <a href="${baseUri}/colaboradores/novo" class="btn btn-lg btn-primary">
      <i class="fa fa-plus"></i>
      Novo Colaborador
    </a>

    <%-- Tabela com atendimentos em aberto --%>
    <div class="mt-5">
      <table class="table table-hover">
        <thead class="c-thead">
          <tr class="text-center">
            <th scope="col">#</th>
            <th scope="col">CPF</th>
            <th scope="col">Nome Completo</th>
            <th scope="col">Nascido em</th>
            <th scope="col">Telefone</th>
            <th scope="col">Gerente</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr class="text-center">
            <th scope="row">809</th>
            <td>123.456.789-10</td>
            <td class="text-left">Josnei da Silva Peixoto</td>
            <td>18-dez-1992</td>
            <td>99988-8777</td>
            <td><i class="fas fa-user-check"></i></td>
            <td>
              <a href="${baseUri}/colaboradores/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/colaboradores/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/colaboradores/exckuir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">820</th>
            <td>111.222.333-44</td>
            <td class="text-left">Josnete Claudete</td>
            <td>24-jun-1995</td>
            <td>99988-8777</td>
            <td></td>
            <td>
              <a href="${baseUri}/colaboradores/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/colaboradores/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/colaboradores/exckuir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">822</th>
            <td>444.555.666-77</td>
            <td class="text-left">Aurélio Dicionários</td>
            <td>18-dez-1992</td>
            <td>99988-8777</td>
            <td><i class="fas fa-user-check"></i></td>
            <td>
              <a href="${baseUri}/colaboradores/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/colaboradores/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/colaboradores/exckuir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">829</th>
            <td>777.888.999-00</td>
            <td class="text-left">Darti Veiderson</td>
            <td>1-jan-1990</td>
            <td>99988-8777</td>
            <td></td>
            <td>
              <a href="${baseUri}/colaboradores/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/colaboradores/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/colaboradores/exckuir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">830</th>
            <td>098.765.432-10</td>
            <td class="text-left">Milkicheickson Ostentação</td>
            <td>30-jun-1999</td>
            <td>99988-8777</td>
            <td></td>
            <td>
              <a href="${baseUri}/colaboradores/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/colaboradores/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/colaboradores/exckuir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">832</th>
            <td>090.871.219-01</td>
            <td class="text-left">Dilermano Júnior</td>
            <td>24-out-1997</td>
            <td>99988-8777</td>
            <td></td>
            <td>
              <a href="${baseUri}/colaboradores/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/colaboradores/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/colaboradores/exckuir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">834</th>
            <td>123.321.132-31</td>
            <td class="text-left">Samuela Fisher</td>
            <td>2-mai-1998</td>
            <td>99988-8777</td>
            <td></td>
            <td>
              <a href="${baseUri}/colaboradores/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/colaboradores/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/colaboradores/exckuir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </main>

</t:baseLayout>
