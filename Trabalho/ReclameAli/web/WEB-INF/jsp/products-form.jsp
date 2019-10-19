<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<t:baseLayout>

  <%-- Cabeçalho da página --%>
  <t:header baseUrl="${baseUri}" hyperlinks="${headerLinks}" />

  <%-- Corpo da página --%>
  <main class="container">
    <h2 class="mb-4">
      Novo Produto
    </h2>

    <%-- Formulário para criação de produto --%>
    <form action="${baseUri}/produtos/novo" method="POST">
      <div class="row">
        <div class="col-12 jsutify-content-between">
          <button type="submit" class="btn btn-primary float-right w-25">
            <i class="far fa-save"></i>
            Salvar
          </button>
          <h3 class="mb-3 h4">Dados Cadastrais</h3>
        </div>
        <div class="col-12 col-md-6">
          <div class="form-group">
            <label for="produto-nome">Nome do produto:</label>
            <input type="text" id="produto-nome" class="form-control" name="nome" placeholder="Digite um nome entre 5 e 32 caracteres" minlengh="5" maxlengh="32" />
          </div>
          <div class="form-group">
            <label for="produto-desc">Descrição:</label>
            <textarea id="produto-desc" class="form-control" name="desc" rows="4"
              placeholder="Escreva informações que qualifiquem o produto (ex.: marca, cor, modelo...)"></textarea>
          </div>
        </div>
        <div class="col-12 col-md-6">
          <div class="form-group">
            <label for="produto-categoria">Categoria:</label>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="fas fa-puzzle-piece"></i>
                </span>
              </div>
              <select id="produto-categoria" class="form-control" name="categoria">
                <option>Selecione...</option>
                <option value="2003">Batons</option>
                <option value="2002">Maquiagens</option>
                <option value="2001">Sabonetes</option>
                <option value="2004">Xampus Femininos</option>
                <option value="2005">Xampus Masculinos</option>
              </select>
            </div>
          </div>
          <div class="row">
            <div class="col-12 col-md-6">
              <div class="form-group">
                <label for="produto-utc">Código UTC:</label>
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="fas fa-barcode"></i>
                    </span>
                  </div>
                  <input type="number" id="produto-utc" class="form-control" name="utc" placeholder="Exemplo: 789000000" />
                </div>
              </div>
            </div>
            <div class="col-12 col-md-6">
              <div class="form-group">
                <label for="produto-ean">Código EAN:</label>
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="fas fa-barcode"></i>
                    </span>
                  </div>
                  <input type="number" id="produto-ean" class="form-control" name="ean" placeholder="Exemplo: 789000000" />
                </div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label for="produto-peso">Peso bruto:</label>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="fas fa-weight"></i>
                </span>
              </div>
              <input type="number" id="produto-peso" class="form-control" name="peso" placeholder="Peso em gramas" />
            </div>
          </div>
        </div>
      </div>
    </form>
  </main>

</t:baseLayout>
