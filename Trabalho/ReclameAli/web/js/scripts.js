
// Colocar máscara nos formulários
const phoneMask = value => (value.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009');
const phoneConfig = {
  onKeyPress(value, event, field, options) {
    field.mask(phoneMask.apply({}, arguments), options);
  }
};
$('input[name="first_name"]').mask('S'.repeat(30), { translation: { S: { pattern: /[A-Za-zÀ-ÖØ-öø-ÿ ]/ } } });
$('input[name="last_name"]').mask('S'.repeat(150), { translation: { S: { pattern: /[A-Za-zÀ-ÖØ-öø-ÿ ]/ } } });
$('input[name="cpf"]').mask('000.000.000-00', { reverse: true });
$('input[name="date_birth"]').mask('00/00/0000');
$('input[name="email"]').mask('S'.repeat(255), { translation: { S: { pattern: /[\w\.@]/ } } });
$('input[name="phone"]').mask(phoneMask, phoneConfig);
$('input[name="zip_code"]').mask('00000-000');
$('input[name="street"]').mask('S'.repeat(255), { translation: { S: { pattern: /[A-Za-zÀ-ÖØ-öø-ÿ\.\,\d ]/ } } });
$('input[name="number"]').mask('999990', { reverse: true });
$('input[name="complement"]').mask('S'.repeat(30), { translation: { S: { pattern: /[A-Za-zÀ-ÖØ-öø-ÿ\.\,\d ]/ } } });
$('input[name="city"]').mask('S'.repeat(80), { translation: { S: { pattern: /[A-Za-zÀ-ÖØ-öø-ÿ ]/ } } });
$('input[name="state"]').mask('SS', { translation: { S: /[A-Z]/ } });

// Configurar DatePicker para campo de data
$('input[name="date_birth"]').datepicker({
  dateFormat: 'dd/mm/yy',
  dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado','Domingo'],
  dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
  dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
  monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
  monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
  maxDate: '-18Y',
  changeMonth: true,
  changeYear: true
});

// Adicionar recurso de consulta de CEP
$('#find-zip_code').click(() => {
  let zipFormat = /([0-9]{5})-([0-9]{3})/;
  let zip = $('input[name="zip_code"]').val();
  if (zip && zipFormat.test(zip)) {
    zip = zip.replace(zipFormat, '$1$2');
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
        $('select[name="state"] option')
          .filter((index, el) => el.text.substring(0, 2) === response.uf)
          .attr('selected', true);
        $('input[name="number"]').focus();
      },
      complete() {
        $('input[name="zip_code"]').attr('disabled', false);
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
  const login = $(`input[name="login"]`).val();
  const pswd = $(`input[name="password"]`).val();
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
          $(`input[name="${err.field}"]`).addClass('is-invalid');
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
