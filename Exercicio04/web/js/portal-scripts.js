// URL de requisições AJAX
var apiUrl = 'cadastro-usuario';

// Monta e adiciona a linha no corpo da tabela
function adicionarLinha(dados) {
  var linha = '<tr>';
  for (var campo in dados) {
    linha += '<td>' + dados[campo] + '</td>';
  }
  linha += '<td>';
  linha += '<button type="button" class="btn btn-sm btn-info mx-1" title="Editar" onclick="editar(' + dados.id + ')">';
  linha += '<svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path d="M402.3 344.9l32-32c5-5 13.7-1.5 13.7 5.7V464c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V112c0-26.5 21.5-48 48-48h273.5c7.1 0 10.7 8.6 5.7 13.7l-32 32c-1.5 1.5-3.5 2.3-5.7 2.3H48v352h352V350.5c0-2.1.8-4.1 2.3-5.6zm156.6-201.8L296.3 405.7l-90.4 10c-26.2 2.9-48.5-19.2-45.6-45.6l10-90.4L432.9 17.1c22.9-22.9 59.9-22.9 82.7 0l43.2 43.2c22.9 22.9 22.9 60 .1 82.8zM460.1 174L402 115.9 216.2 301.8l-7.3 65.3 65.3-7.3L460.1 174zm64.8-79.7l-43.2-43.2c-4.1-4.1-10.8-4.1-14.8 0L436 82l58.1 58.1 30.9-30.9c4-4.2 4-10.8-.1-14.9z"/></svg>';
  linha += '</button>';
  linha += '<button type="button" class="btn btn-sm btn-danger mx-1" title="Excluir" onclick="exluir(' + dados.id + ')">';
  linha += '<svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M268 416h24a12 12 0 0 0 12-12V188a12 12 0 0 0-12-12h-24a12 12 0 0 0-12 12v216a12 12 0 0 0 12 12zM432 80h-82.41l-34-56.7A48 48 0 0 0 274.41 0H173.59a48 48 0 0 0-41.16 23.3L98.41 80H16A16 16 0 0 0 0 96v16a16 16 0 0 0 16 16h16v336a48 48 0 0 0 48 48h288a48 48 0 0 0 48-48V128h16a16 16 0 0 0 16-16V96a16 16 0 0 0-16-16zM171.84 50.91A6 6 0 0 1 177 48h94a6 6 0 0 1 5.15 2.91L293.61 80H154.39zM368 464H80V128h288zm-212-48h24a12 12 0 0 0 12-12V188a12 12 0 0 0-12-12h-24a12 12 0 0 0-12 12v216a12 12 0 0 0 12 12z"/></svg>';
  linha += '</button>';
  linha += '</td>';
  linha += '</tr>';
  $('tbody').append(linha);
}

// Requisita para "/cadastro-usuario" a lista de usuários
function requisitarListaUsuarios() {
  $.ajax({
    method: 'GET',
    url: apiUrl,
    success: function(response) {
      for (var usuario of response) {
        adicionarLinha(usuario);
      }
    }
  });
}

// Envia os dados do formulário para "/cadastro-usuario" para criação de novo usuário
function enviarDados(e) {
  e.preventDefault();
  $('.alert-success').hide();
  $('.alert-danger').hide();
  var dados = {
    nome: $('input[name="nome"]').val(),
    login: $('input[name="login"]').val(),
    senha: $('input[name="senha"]').val()
  };
  $.ajax({
    method: 'POST',
    url: apiUrl,
    data: dados,
    success: function(response) {
      adicionarLinha(response.data);
      $('input[name="nome"]').val('');
      $('input[name="login"]').val('');
      $('input[name="senha"]').val('');
      $('input[name="nome"]').focus();
      $('.alert-success')
        .fadeTo(2000, 500)
        .slideUp(500, function() {
          $('.alert-success').slideUp(500);
        });
    },
    error: function(response) {
      var erros = response.responseJSON.messages;
      var alert = $('.alert-danger');
      alert.html('');
      for (var er of erros) {
        alert.html(alert.html() + er + '<br/>');
      }
      alert.fadeTo(2000, 500).slideUp(500, function() {
        alert.slideUp(500);
      });
    }
  });
}

// Realiza requisição assíncrona de usuários quando a página é carregada
$(document).ready(function() {
  requisitarListaUsuarios();
  $('form').submit(enviarDados);
});
