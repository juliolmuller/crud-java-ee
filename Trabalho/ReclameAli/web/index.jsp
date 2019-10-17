<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error-page.jsp" %>

<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>
      RECLAME ALI :: [Trabalho Web 2 (parte 1) - ProtÃ³tipo]
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
  </head>
  <body>

    <!-- Corpo da pÃ¡gina -->
    <main class="container mb-5">
      <div class="text-center">
        <h1 class="text-primary display-3 c-title">
          <img src="${pageContext.request.contextPath}/img/reclame-ali-blue.png" class="c-logo-large" alt="Logo do Sistema" />
          Reclame Ali
        </h1>
        <span class="text-secondary c-subtitle">
          ServiÃ§o de Atendimento ao Cliente da <strong>Embuste</strong><sup>&copy;</sup>
        </span>
      </div>
      <div class="row my-3">
        <div class="col-6 p-3 d-flex align-items-stretch">

          <!-- FormulÃ¡rio de login -->
          <form id="form-login" action="#" method="POST" class="card">
            <h2 class="card-header">Entrar</h2>
            <img class="card-img-top" src="img/cover.jpg" alt="Capa">
            <div class="card-body">
              <a class="card-text mt-1">
                VocÃª estÃ¡ prestes a acessar o sistema SAC (ServiÃ§o de Atendimento ao Cliente) da empresa mais top
                que existe no mundo, a <strong>Embuste</strong><sup>&copy;</sup>. Tenha em mente todos os
                <a href="#">Termos de Uso</a> da plataforma e de que qualquer irregularidade pode resultar
                infraÃ§Ã£o dos termos do contrato.
              </p>
              <p class="card-text mb-5">
                Para acessar o sistema, Ã© necessÃ¡rio fornecer suas credenciais de acesso:
              </p>
              <div class="form-group">
                <label for="email">EndereÃ§o de email:</label>
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="fas fa-at"></i>
                    </span>
                  </div>
                  <input type="email" id="email" class="form-control" name="email" />
                </div>
              </div>
              <div class="form-group">
                <label for="senha">Senha de acesso:</label>
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="fas fa-unlock-alt"></i>
                    </span>
                  </div>
                  <input type="password" id="senha" class="form-control" name="senha" />
                </div>
              </div>
              <div class="text-center">
                <p class="mt-4 mb-0 h5">
                  Esqueceu a senha? <a href="mailto:example@email.com">Contate nosso suporte</a>
                </p>
              </div>
            </div>
            <div class="card-footer text-center">
              <button type="submit" class="btn btn-lg btn-primary w-50">
                Entrar
              </button>
            </div>
          </form>
        </div>

        <!-- FormulÃ¡rio de cadastro -->
        <div class="col-6 p-3 d-flex align-items-stretch">
          <form id="form-signup" action="#" method="POST" class="card">
            <h2 class="card-header">Cadastro</h2>
            <div class="card-body">
              <p class="card-text">Crie uma conta como cliente para acessar o sistema:</p>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fab fa-slack-hash"></i>
                  </span>
                </div>
                <input type="number" class="form-control" name="cpf" placeholder="CPF" step="1" />
              </div>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-user"></i>
                  </span>
                </div>
                <input type="text" class="form-control" name="nome" placeholder="Nome completo" />
              </div>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="far fa-calendar-alt"></i>
                  </span>
                </div>
                <input type="date" class="form-control" name="data_nasc" placeholder="Data de nascimento" />
              </div>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-at"></i>
                  </span>
                </div>
                <input type="email" class="form-control" name="email" placeholder="Email de acesso" />
              </div>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-mobile-alt"></i>
                  </span>
                </div>
                <input type="number" class="form-control" name="telefone" placeholder="Telefone (con DDD)" />
              </div>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-map-marker-alt"></i>
                  </span>
                </div>
                <input type="number" class="form-control" name="cep" placeholder="CEP" />
                <div class="input-group-append">
                  <button class="btn btn-outline-secondary" type="button" id="buscar-cep">
                    Buscar CEP
                  </button>
                </div>
              </div>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-map"></i>
                  </span>
                </div>
                <input type="text" class="form-control" name="rua" placeholder="EndereÃ§o (rua, avenida, alameda...)" disabled />
              </div>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-map-marked-alt"></i>
                  </span>
                </div>
                <input type="number" class="form-control" name="numero" placeholder="NÃºmero" />
                <input type="text" class="form-control" name="complemento" placeholder="Complemento" />
              </div>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-globe-americas"></i>
                  </span>
                </div>
                <input type="text" class="form-control" name="cidade" placeholder="Cidade" disabled />
                <input type="text" class="form-control" name="estado" placeholder="Estado" disabled />
              </div>
              <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-unlock-alt"></i>
                  </span>
                </div>
                <input type="password" class="form-control" name="senha1" placeholder="Senha de acesso" />
              </div>
              <div class="input-group mb-4">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fas fa-unlock-alt"></i>
                  </span>
                </div>
                <input type="password" class="form-control" name="senha2" placeholder="Repetir senha" />
              </div>
              <div class="custom-control custom-switch mb-2">
                <input type="checkbox" class="custom-control-input" id="termos-de-uso" name="termos" />
                <label class="custom-control-label" for="termos-de-uso">
                  Aceito os <a href="#">Termos de Uso</a> do serviÃ§o <strong>Reclame Ali<sup>&copy;</sup></strong>
                </label>
              </div>
            </div>
            <div class="card-footer text-center">
              <button type="click" class="btn btn-lg btn-primary w-50">
                Cadastrar
              </button>
            </div>
          </form>
        </div>
      </div>
    </main>

    <!-- Modal -->
    <div class="modal" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Acessar protÃ³tipo como...</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="list-group text-center w-100">
              <a href="cliente/index.html" class="text-primary list-group-item list-group-item-action list-group-item-light">Cliente</a>
              <a href="funcionario/index.html" class="text-primary list-group-item list-group-item-action list-group-item-light">FuncionÃ¡rio</a>
              <a href="gerente/index.html" class="text-primary list-group-item list-group-item-action list-group-item-light">Gerente</a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
  </body>
</html>
