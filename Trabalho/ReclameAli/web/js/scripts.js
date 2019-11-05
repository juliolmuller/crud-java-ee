
// Colocar máscara nos formulários
const phoneMask = value => (value.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009');
const phoneConfig = {
  onKeyPress(value, event, field, options) {
    field.mask(phoneMask.apply({}, arguments), options);
  }
};
$('#form-signup [name="first_name"]').mask('S'.repeat(30), { translation: { S: { pattern: /[A-Za-zÀ-ÖØ-öø-ÿ ]/ } } });
$('#form-signup [name="last_name"]').mask('S'.repeat(150), { translation: { S: { pattern: /[A-Za-zÀ-ÖØ-öø-ÿ ]/ } } });
$('#form-signup [name="cpf"]').mask('000.000.000-00', { reverse: true });
$('#form-signup [name="date_birth"]').mask('00/00/0000');
$('#form-signup [name="email"]').mask('S'.repeat(255), { translation: { S: { pattern: /[\w\.@]/ } } });
$('#form-signup [name="phone"]').mask(phoneMask, phoneConfig);
$('#form-signup [name="zip_code"]').mask('00000-000');
$('#form-signup [name="street"]').mask('S'.repeat(255), { translation: { S: { pattern: /[A-Za-zÀ-ÖØ-öø-ÿ\.\,\d ]/ } } });
$('#form-signup [name="number"]').mask('999990', { reverse: true });
$('#form-signup [name="complement"]').mask('S'.repeat(30), { translation: { S: { pattern: /[A-Za-zÀ-ÖØ-öø-ÿ\.\,\d ]/ } } });
$('#form-signup [name="city"]').mask('S'.repeat(80), { translation: { S: { pattern: /[A-Za-zÀ-ÖØ-öø-ÿ ]/ } } });
$('#form-signup [name="state"]').mask('SS', { translation: { pattern: { S: /[A-Z]/ } } });

// Configurar DatePicker para campo de data
$('#form-signup [name="date_birth"]').datepicker({
  format: 'dd/mm/yyyy',
  days: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
  daysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
  daysMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
  months: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
  monthsShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
  startView: 2,
  autoHide: true,
  zIndex: 2048
});

// Adicionar recurso de consulta de CEP
$('#find-zip_code').click(() => {
  let zipFormat = /([0-9]{5})-([0-9]{3})/;
  let zip = $('[name="zip_code"]').val();
  if (zip && zipFormat.test(zip)) {
    zip = zip.replace(zipFormat, '$1$2');
    $('[name="zip_code"]').attr('disabled', true);
    $('[name="street"]').val('');
    $('[name="number"]').val('');
    $('[name="complement"]').val('');
    $('[name="city"]').val('');
    $('[name="state"]').val('');
    $.ajax({
      method: 'GET',
      url: `https://viacep.com.br/ws/${zip}/json`,
      success(response) {
        $('[name="street"]').val(response.logradouro);
        $('[name="city"]').val(response.localidade);
        $('select[name="state"] option')
          .filter((index, el) => el.text.substring(0, 2) === response.uf)
          .attr('selected', true);
        $('[name="number"]').focus();
      },
      complete() {
        $('[name="zip_code"]').attr('disabled', false);
      }
    });
  }
});

// Função para extração dos dados do formulpario
function extractDataForm(formSelector) {
  const data = {};
  $(`${formSelector} [name]`).each((i, el) => (data[el.name] = el.value));
  return data;
}

// VErificar se formulário de signin está preenchido
$('#form-signin').submit(e => {
  const login = $(`#form-signin [name="login"]`).val();
  const pswd = $(`#form-signin [name="password"]`).val();
  if (!login || !pswd) {
    e.preventDefault();
    e.target.classList.add('was-validated');
  }
});

// Enviar dados de signup via AJAX
$('#form-signup').submit(e => {
  e.preventDefault();
  const userData = extractDataForm('#form-signup');
  userData.cpf = userData.cpf.replace(/([0-9]{3})\.?([0-9]{3})\.?([0-9]{3})\-([0-9]{2})/, '$1$2$3$4');
  userData.phone = userData.phone.replace(/\(?([0-9]{2})\)?\s?([0-9]{4,5})\-?([0-9]{4})/, '$1$2$3');
  userData.date_birth = userData.date_birth.replace(/(\d{1,2})\/?(\d{1,2})\/?(\d{4})/, '$3-$2-$1');
  userData.zip_code = userData.zip_code.replace(/([0-9]{5})\-([0-9]{3})/, '$1$2');
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
          $(`[name="${err.field}"]`).addClass('is-invalid');
          $(`#invalid-${err.field}`).text(err.message);
        }
      }
    }
  });
});

// TODO:s Adicionar recurso de busca de produto
$('#find-product').click(() => {
  const produto = $('#product-code').val();
  $('#product-details').show();
});

// Adicionar evento para linhas de tabelas
$('.c-clickable').click(function() {
  window.location = $(this).data('href');
});

// TODO: Adicionar evento para filtro de tabelas
$('#filtro-atendimentos').change(function() {
  $('tr').show();
  switch ($(this).val()) {
    case 'abertos':
      $('td span.badge-success')
        .parent()
        .parent()
        .hide();
      break;
    case 'vencidos':
      $('tbody tr').hide();
      $('tr.table-danger').show();
      break;
    case 'fechados':
      $('tbody tr').hide();
      $('td span.badge-success')
        .parent()
        .parent()
        .show();
      break;
  }
});

// TODO: processar exclusão
$('a[title="Excluir"]').click(function(e) {
  e.preventDefault();
  if (confirm('Tem certeza que quer excluir este item?')) {
    $(this)
      .parent()
      .parent()
      .remove();
  }
});
