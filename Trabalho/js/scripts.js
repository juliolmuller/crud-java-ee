
// Adicionar eventos de submissão de formulário
$('#form-login').submit(e => {
  e.preventDefault();
  $('.modal').modal('show');
});
$('form[method="POST"]').submit(e => {
  e.preventDefault();
  alert('Recurso indisponível!');
});

// Adicionar eventos em formulário
$('#buscar-cep').click(() => {
  const cep = $('input[name="cep"]').val();
  const formatoCEP = /^[0-9]{8}$/;
  if (cep && formatoCEP.test(cep)) {
    $('input[name="cep"]').attr('disabled', true);
    $('input[name="rua"]').val('');
    $('input[name="numero"]').val('');
    $('input[name="complemento"]').val('');
    $('input[name="cidade"]').val('');
    $('input[name="estado"]').val('');
    $.ajax({
      method: 'GET',
      url: `https://viacep.com.br/ws/${cep}/json`,
      success(response) {
        console.log($('input[name="estado"]'))
        $('input[name="rua"]').val(response.logradouro);
        $('input[name="cidade"]').val(response.localidade);
        $('input[name="estado"]').val(response.uf);
        $('input[name="numero"]').focus();
      },
      complete() {
        $('input[name="cep"]').attr('disabled', false);
      }
    });
  }
});
$('#buscar-produto').click(() => {
  const produto = $('#codigo-produto').val();
  $('#detalhes-produto').show();
});

// Adicionar evento para linhas de tabelas
$('.c-clickable').click(function() {
  window.location = $(this).data('href');
});
$('a[title="Excluir"]').click(function(e) {
  e.preventDefault();
  if (confirm('Tem certeza que quer excluir este item?')) {
    $(this).parent().parent().remove();
  }
});

// Adicionar evento para filtro de tabelas
$('#filtro-atendimentos').change(function() {
  $('tr').show();
  switch ($(this).val()) {
    case 'abertos':
      $('td span.badge-success').parent().parent().hide();
      break;
    case 'vencidos':
      $('tbody tr').hide();
      $('tr.table-danger').show();
      break;
    case 'fechados':
      $('tbody tr').hide();
      $('td span.badge-success').parent().parent().show();
      break;
  }
});
