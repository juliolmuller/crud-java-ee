
// URL de requisições AJAX
var apiUrl = 'cadastrar-usuario'

// Monta e adiciona a linha no corpo da tabela
function adicionarLinha(dados) {
  var linha = '<tr>'
  for (var campo in dados) {
    linha += '<td>' + dados[campo] + '</td>'
  }
  linha += '</tr>'
  $('tbody').append(linha)
}

// Requisita para "http://localhost/Exercicio02/cadastrar-usuario" a lista de usuários
function requisitarListaUsuarios() {
  $.getJSON(apiUrl).then(function(response) {
    for (var usuario of response) {
      adicionarLinha(usuario)
    }
  })
}

function enviarDados(e) {
  e.preventDefault()
  var dados = {
    nome: $('input[name="nome"]').val(),
    login: $('input[name="login"]').val().toUpperCase(),
    senha: $('input[name="senha"]').val()
  }
  $.post(apiUrl, dados)
    .then(function(response){
      adicionarLinha(response.data)
      $('.alert-success').show()
      $('input').val('')
      $('input[name="nome"]').focus()
    })
    .catch(function(response) {
      var alerta = $('.alert-danger')
      alerta.show()
      alerta.html('')
      for (var e of response.responseJSON.messages) {
        alerta.html(alerta.html() + e + '<br/>')
      }
  })

}

$(document).ready(function() {
  requisitarListaUsuarios()
  $('form').submit(enviarDados)
  $('input').keypress(function() {
    $('.alert').hide()
  })
})
