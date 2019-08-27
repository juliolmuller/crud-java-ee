
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

  $.post(apiUrl, dados).then(function(response) {
    adicionarLinha(response.data)
    $('input[name="nome"]').val('')
    $('input[name="login"]').val('')
    $('input[name="senha"]').val('')
    $('input[name="nome"]').focus()
  })
  
}

$(document).ready(function() {
  requisitarListaUsuarios()
  $('form').submit(enviarDados)
})
