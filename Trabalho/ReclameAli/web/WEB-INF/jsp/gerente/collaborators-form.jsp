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
              <a class="nav-link" href="atendimentos.html">Atendimentos</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="colaboradores.html">Cadastro de Colaboradores</a>
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
        Funcionário/Gerente #809 (Josnei)
      </h2>

      <!-- Formulário dos dados do usuário -->
      <form action="colaboradores.html" method="POST" class="mt-5">
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
              <label for="cliente-nome">Nome completo:</label>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-user"></i>
                  </span>
                </div>
                <input type="text" id="cliente-nome" class="form-control" value="Josnei Ornitorrinco da Silva" />
              </div>
            </div>
            <div class="form-group">
              <label for="cliente-cpf">CPF:</label>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fab fa-slack-hash"></i>
                  </span>
                </div>
                <input type="text" id="cliente-cpf" class="form-control" value="123.456.789-10" readonly />
              </div>
            </div>
            <div class="form-group">
              <label for="cliente-data-nasc">Data de nascimento:</label>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="far fa-calendar-alt"></i>
                  </span>
                </div>
                <input type="date" id="cliente-data-nasc" class="form-control" name="data_nasc" value="2000-06-24" />
              </div>
            </div>
            <div class="form-group">
              <label for="cliente-email">Email de acesso:</label>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-at"></i>
                  </span>
                </div>
                <input type="email" id="cliente-email" class="form-control" value="josnei@email.com" />
              </div>
            </div>
            <div class="form-group">
              <label for="cliente-telefone">Telefone de contato:</label>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-mobile-alt"></i>
                  </span>
                </div>
                <input type="text" id="cliente-telefone" class="form-control" value="(41) 99988-8777" />
              </div>
            </div>
          </div>
          <div class="col-12 col-md-6">
            <div class="form-group">
              <label for="cliente-cep">CEP:</label>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-map-marker-alt"></i>
                  </span>
                </div>
                <input type="text" id="cliente-cep" class="form-control" name="cep" value="82510-100" />
                <div class="input-group-append">
                  <button class="btn btn-secondary" type="button" id="buscar-cep">
                    Buscar CEP
                  </button>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label for="cliente-rua">Endereço:</label>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-map"></i>
                  </span>
                </div>
                <input type="text" id="cliente-rua" class="form-control" name="rua" value="Rua Venezuela" readonly />
              </div>
            </div>
            <div class="row">
              <div class="col-6">
                <div class="form-group">
                  <label for="cliente-numero">Endereço:</label>
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text">
                        <i class="fas fa-map-marked-alt"></i>
                      </span>
                    </div>
                    <input type="number" id="cliente-numero" class="form-control" name="numero" value="220" />
                  </div>
                </div>
              </div>
              <div class="col-6">
                <div class="form-group">
                  <label for="cliente-complemento">Complemento:</label>
                  <input type="text" id="cliente-complemento" class="form-control" name="complemento" value="" />
                </div>
              </div>
            </div>
            <div class="form-group">
              <label for="cliente-cidade">Cidade:</label>
              <input type="text" id="cliente-cidade" class="form-control" name="cidade" value="Curitiba" readonly />
            </div>
            <div class="row">
              <div class="col-6">
                <div class="form-group">
                  <label for="cliente-estado">Estado:</label>
                  <input type="text" id="cliente-estado" class="form-control" name="estado" value="PR" readonly />
                </div>
              </div>
              <div class="col-6">
                <div class="form-group">
                  <label for="cliente-pais">País:</label>
                  <input type="text" id="cliente-pais" class="form-control" value="Brasil" readonly />
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>

      <!-- Formulário de alteração de senha -->
      <form action="#" method="POST" class="mt-5">
        <div class="row">
          <div class="col-12 jsutify-content-between">
            <button type="submit" class="btn btn-primary float-right w-25">
              <i class="far fa-save"></i>
              Alterar
            </button>
            <h3 class="mb-3 h4">Alterar Senha</h3>
          </div>
          <div class="col-12 col-md-6">
            <div class="form-group">
              <label for="cliente-senha-atual">Senha atual:</label>
              <input type="password" id="cliente-senha-atual" class="form-control" name="senha_atual" />
            </div>
            <div class="form-group">
              <label for="cliente-senha-nova1">Nova senha:</label>
              <input type="password" id="cliente-senha-nova1" class="form-control" name="senha_nova1" />
            </div>
            <div class="form-group">
              <label for="cliente-senha-nova2">Repetir nova senha:</label>
              <input type="password" id="cliente-senha-nova2" class="form-control" name="senha_nova2" />
            </div>
          </div>
        </div>
      </form>
    </main>

    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
  </body>
</html>
