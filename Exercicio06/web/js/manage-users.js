
// URL de requisições AJAX
const apiUrl = './usuarios';

// Monta e adiciona a linha no corpo da tabela
function montarLinha(dados) {
  let linha = `<tr id="${dados.id}">`;
  for (let campo in dados) {
    linha += `<td>${dados[campo]}</td>`;
  }
  linha += `
    <td>
      <a href="#" class="btn btn-sm btn-info mx-1" title="Editar" onclick="editar(event)">
        <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
          <path d="M402.3 344.9l32-32c5-5 13.7-1.5 13.7 5.7V464c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V112c0-26.5 21.5-48 48-48h273.5c7.1 0 10.7 8.6 5.7 13.7l-32 32c-1.5 1.5-3.5 2.3-5.7 2.3H48v352h352V350.5c0-2.1.8-4.1 2.3-5.6zm156.6-201.8L296.3 405.7l-90.4 10c-26.2 2.9-48.5-19.2-45.6-45.6l10-90.4L432.9 17.1c22.9-22.9 59.9-22.9 82.7 0l43.2 43.2c22.9 22.9 22.9 60 .1 82.8zM460.1 174L402 115.9 216.2 301.8l-7.3 65.3 65.3-7.3L460.1 174zm64.8-79.7l-43.2-43.2c-4.1-4.1-10.8-4.1-14.8 0L436 82l58.1 58.1 30.9-30.9c4-4.2 4-10.8-.1-14.9z"/>
        </svg>
      </a>
      <a href="#" class="btn btn-sm btn-danger mx-1" title="Excluir" onclick="excluir(event)">
        <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
          <path d="M268 416h24a12 12 0 0 0 12-12V188a12 12 0 0 0-12-12h-24a12 12 0 0 0-12 12v216a12 12 0 0 0 12 12zM432 80h-82.41l-34-56.7A48 48 0 0 0 274.41 0H173.59a48 48 0 0 0-41.16 23.3L98.41 80H16A16 16 0 0 0 0 96v16a16 16 0 0 0 16 16h16v336a48 48 0 0 0 48 48h288a48 48 0 0 0 48-48V128h16a16 16 0 0 0 16-16V96a16 16 0 0 0-16-16zM171.84 50.91A6 6 0 0 1 177 48h94a6 6 0 0 1 5.15 2.91L293.61 80H154.39zM368 464H80V128h288zm-212-48h24a12 12 0 0 0 12-12V188a12 12 0 0 0-12-12h-24a12 12 0 0 0-12 12v216a12 12 0 0 0 12 12z"/>
        </svg>
      </a>
    </td></tr>
  `;
  return linha;
}

// Coletar dados de formulário
function coletarDados(linha) {
  if (linha) {
    return {
      id: linha.cells[0].textContent,
      nome: linha.cells[1].textContent,
      login: linha.cells[2].textContent,
      senha: linha.cells[3].textContent
    };
  }
  return {
    id: $('input[name="id"]').val(),
    nome: $('input[name="nome"]').val(),
    login: $('input[name="login"]').val(),
    senha: $('input[name="senha"]').val()
  };
}

// Limpar formulário
function limparForm() {
  $('input[name="id"]').val(''),
  $('input[name="nome"]').val('');
  $('input[name="login"]').val('');
  $('input[name="senha"]').val('');
  $('input[name="nome"]').focus();
  $('#btn-ok').html('Cadastrar');
}

// Exibir notificações de operação
function exibirMensagem(alerta, mensagens) {
  alerta.html('');
  for (var msg of mensagens) {
    alerta.html(alerta.html() + msg + '<br/>');
  }
  alerta.fadeTo(5000, 500).slideUp(500, function() {
    alerta.slideUp(500);
  });
}

// Coletar dados da tabela e colocá=los no formulário
function editar(e) {
  e.preventDefault();
  window.linha = e.target;
  while (linha.tagName != 'TR') {
    linha = linha.parentNode;
  }
  const dados = coletarDados(linha);
  for (let campo in dados) {
    $(`input[name="${campo}"]`).val(dados[campo]);
  }
  $('input[name="nome"]').focus();
  $('#btn-ok').html('Atualizar');
}

// Requisita para "/cadastro-usuario" a lista de usuários
function requisitarListaUsuarios() {
  $.ajax({
    method: 'GET',
    url: apiUrl,
    success: function(response) {
      for (let usuario of response) {
        $('tbody').append(montarLinha(usuario));
      }
    }
  });
}

// Envia os dados do formulário para "/cadastro-usuario" para criação de novo usuário
function enviarDados(e) {
  e.preventDefault();
  const dados = coletarDados();
  if (!dados.id) {
    $.ajax({
      method: 'POST',
      url: apiUrl,
      data: dados,
      success: function(response) {
        limparForm();
        $('tbody').append(montarLinha(response.data));
        exibirMensagem($('.alert-success'), ['Usuário cadastrado com sucesso']);
      },
      error: function(response) {
        exibirMensagem($('.alert-danger'), response.responseJSON.messages);
      }
    });
  } else {
    $.ajax({
      method: 'PUT',
      url: `${apiUrl}?id=${dados.id}&nome=${dados.nome}&login=${dados.login}&senha=${dados.senha}`,
      success: function(response) {
        limparForm();
        $('tbody > tr').filter(function(i, el) {
          return el.id == response.data.id;
        }).replaceWith(montarLinha(response.data));
        exibirMensagem($('.alert-success'), ['Usuário atualizado com sucesso']);
      },
      error: function(response) {
        exibirMensagem($('.alert-danger'), response.responseJSON.messages);
      }
    });
  }
}

// Enviar arequisição para exclusão de registro
function excluir(e) {
  e.preventDefault();
  let linha = e.target;
  while (linha.tagName != 'TR') {
    linha = linha.parentNode;
  }
  const id = linha.id;
  if (confirm('Você tem certeza de que quer excluir o usuário #' + id + '?')) {
    $.ajax({
      method: 'DELETE',
      url: `${apiUrl}?id=${id}`,
      success: function() {
        linha.parentNode.removeChild(linha);
        exibirMensagem($('.alert-success'), ['Usuário excluído com sucesso']);
      },
      error: function(response) {
        exibirMensagem($('.alert-danger'), response.responseJSON.messages);
      }
    });
  }
}

// Realiza requisição assíncrona de usuários quando a página é carregada
$(document).ready(function() {
  requisitarListaUsuarios();
  $('form').submit(enviarDados);
  $('#btn-cancel').click(limparForm)
});
