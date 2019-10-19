<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container">
    <h2 class="mb-4">
      Produtos
    </h2>

    <%-- Botão para abertura de novo atendimento --%>
    <a href="${baseUri}/produtos/novo" class="btn btn-lg btn-primary">
      <i class="fa fa-plus"></i>
      Criar Novo Produto
    </a>

    <%-- Tabela com atendimentos em aberto --%>
    <div class="mt-5">
      <table class="table table-hover">
        <thead class="c-thead">
          <tr class="text-center">
            <th scope="col">#</th>
            <th scope="col">Nome</th>
            <th scope="col">Categoria</th>
            <th scope="col">Peso</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr class="text-center">
            <th scope="row">50001</th>
            <td class="text-left">Sabonete (sabor lavanda)</td>
            <td class="text-left">Sabonetes</td>
            <td class="text-left">50g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50002</th>
            <td class="text-left">Sabonete (sabor erva-doce)</td>
            <td class="text-left">Sabonetes</td>
            <td class="text-left">50g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50003</th>
            <td class="text-left">Base (sabor cereja)</td>
            <td class="text-left">Maquiagens</td>
            <td class="text-left">80g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50004</th>
            <td class="text-left">Batom Cacatua Raivosa</td>
            <td class="text-left">Batons</td>
            <td class="text-left">15g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50005</th>
            <td class="text-left">Creme Hidratante Ar & Baba</td>
            <td class="text-left">Hidratantes</td>
            <td class="text-left">180g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50006</th>
            <td class="text-left">Sabonete (sabor lavanda)</td>
            <td class="text-left">Sabonetes</td>
            <td class="text-left">50g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50007</th>
            <td class="text-left">Sabonete (sabor erva-doce)</td>
            <td class="text-left">Sabonetes</td>
            <td class="text-left">50g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50008</th>
            <td class="text-left">Base (sabor cereja)</td>
            <td class="text-left">Maquiagens</td>
            <td class="text-left">80g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50009</th>
            <td class="text-left">Batom Cacatua Raivosa</td>
            <td class="text-left">Batons</td>
            <td class="text-left">15g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50010</th>
            <td class="text-left">Creme Hidratante Ar & Baba</td>
            <td class="text-left">Hidratantes</td>
            <td class="text-left">180g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50011</th>
            <td class="text-left">Sabonete (sabor lavanda)</td>
            <td class="text-left">Sabonetes</td>
            <td class="text-left">50g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50012</th>
            <td class="text-left">Sabonete (sabor erva-doce)</td>
            <td class="text-left">Sabonetes</td>
            <td class="text-left">50g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50013</th>
            <td class="text-left">Base (sabor cereja)</td>
            <td class="text-left">Maquiagens</td>
            <td class="text-left">80g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50014</th>
            <td class="text-left">Batom Cacatua Raivosa</td>
            <td class="text-left">Batons</td>
            <td class="text-left">15g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50015</th>
            <td class="text-left">Creme Hidratante Ar & Baba</td>
            <td class="text-left">Hidratantes</td>
            <td class="text-left">180g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50016</th>
            <td class="text-left">Sabonete (sabor lavanda)</td>
            <td class="text-left">Sabonetes</td>
            <td class="text-left">50g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50017</th>
            <td class="text-left">Sabonete (sabor erva-doce)</td>
            <td class="text-left">Sabonetes</td>
            <td class="text-left">50g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50018</th>
            <td class="text-left">Base (sabor cereja)</td>
            <td class="text-left">Maquiagens</td>
            <td class="text-left">80g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50019</th>
            <td class="text-left">Batom Cacatua Raivosa</td>
            <td class="text-left">Batons</td>
            <td class="text-left">15g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
          <tr class="text-center">
            <th scope="row">50020</th>
            <td class="text-left">Creme Hidratante Ar & Baba</td>
            <td class="text-left">Hidratantes</td>
            <td class="text-left">180g</td>
            <td>
              <a href="${baseUri}/produtos/visualizar" class="btn btn-sm btn-success" title="Visualizar"><i class="fas fa-eye"></i></a>
              <a href="${baseUri}/produtos/editar" class="btn btn-sm btn-info" title="Editar"><i class="fas fa-edit"></i></a>
              <form action="${baseUri}/produtos/excluir">
                <button type="submit" class="btn btn-sm btn-danger" title="Excluir"><i class="fas fa-trash-alt"></i></button>
              </form>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </main>

</t:baseLayout>
