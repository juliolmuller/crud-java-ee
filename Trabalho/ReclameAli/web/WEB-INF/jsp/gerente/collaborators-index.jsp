<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container">
    <h2 class="mb-4">
      Cadastro de Funcionários & Gerentes
    </h2>

    <%-- Botão para abertura de novo atendimento --%>
    <a href="colaboradores-form.html" class="btn btn-lg btn-primary">
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
              <a href="colaboradores-form.html" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="colaboradores-form.html" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <a href="#" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></a>
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
              <a href="colaboradores-form.html" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="colaboradores-form.html" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <a href="#" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></a>
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
              <a href="colaboradores-form.html" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="colaboradores-form.html" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <a href="#" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></a>
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
              <a href="colaboradores-form.html" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="colaboradores-form.html" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <a href="#" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></a>
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
              <a href="colaboradores-form.html" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="colaboradores-form.html" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <a href="#" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></a>
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
              <a href="colaboradores-form.html" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="colaboradores-form.html" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <a href="#" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></a>
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
              <a href="colaboradores-form.html" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="colaboradores-form.html" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <a href="#" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </main>

</t:baseLayout>
