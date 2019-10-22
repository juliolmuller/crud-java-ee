
// VErificar se formulário de signin está preenchido
$('#form-signin').submit(e => {
  const login = $(`input[name="login"]`).val();
  const pswd = $(`input[name="password"]`).val();
  if (!login || !pswd) {
    e.preventDefault();
    e.target.classList.add('was-validated');
  }
});

// Função para extração dos dados de usuário do formulpario
function extractUserData(formSelector) {
  const userData = {};
  $(`${formSelector} input`).each((i, el) => (userData[el.name] = el.value));
  return userData;
}

// Enviar dados de signup via AJAX e aguardar resposta
$('#form-signup').submit(e => {
  e.preventDefault();
  const userData = extractUserData('#form-signup');
  $.ajax({
    method: 'POST',
    url: e.target.action,
    data: userData,
    success() {
      let baseUrl = window.location.href.split('/');
      baseUrl[4] = 'cliente';
      window.location.href = baseUrl.join('/');
    },
    error(response) {
      console.log('Fail to register: review validation messages and try again');
      if (response.errors) {
        for (let err in response.errors) {
          $(`input[name="${err.field}"]`).addClass('is-invalid');
          $(`#invalid-${err.field}`).text(err.message);
        }
      }
    }
  })
});

// Adicionar recurso de consulta de CEP
$('#find-zip_code').click(() => {
  const zip = $('input[name="zip_code"]').val();
  const zipFormat = /^[0-9]{8}$/;
  if (zip && zipFormat.test(zip)) {
    $('input[name="zip_code"]').attr('disabled', true);
    $('input[name="street"]').val('');
    $('input[name="number"]').val('');
    $('input[name="complement"]').val('');
    $('input[name="city"]').val('');
    $('input[name="state"]').val('');
    $.ajax({
      method: 'GET',
      url: `https://viacep.com.br/ws/${zip}/json`,
      success(response) {
        $('input[name="street"]').val(response.logradouro);
        $('input[name="city"]').val(response.localidade);
        $('input[name="state"]').val(response.uf);
        $('input[name="number"]').focus();
      },
      complete() {
        $('input[name="zip_code"]').attr('disabled', false);
      }
    });
  }
});

// Adicionar recurso de busca de produto
$('#find-product').click(() => {
  const produto = $('#product-code').val();
  $('#product-details').show();
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
