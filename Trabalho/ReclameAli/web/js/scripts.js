
// Configurar toastr
toastr.options.showMethod = 'slideDown';
toastr.options.hideEasing = 'swing';
toastr.options.timeOut = 10000;

// Function to escape HTML
function escapeHTML(string) {
  const map = {
    '&': '&amp;',
    '<': '&lt;',
    '>': '&gt;',
    '"': '&quot;',
    "'": '&#039;'
  };
  return String(string).replace(/[&<>"']/g, m => map[m]);
}

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
$('#product-form [name="weight"]').mask('999990', { reverse: true });
$('#product-form [name="utc_code"]').mask('0000.0000.0000', { reverse: true });
$('#product-form [name="ean_code"]').mask('0.000.000.000.000', { reverse: true });

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
  $(`${formSelector} [name]`).each((i, el) => data[el.name] = el.value);
  return data;
}

// Limpar formulário
function cleanForm(formSelector) {
  $(`${formSelector} [name]`).each((i, el) => el.value = '');
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
  $('#form-signup .is-invalid').removeClass('is-invalid');
  $('#form-signup [type="submit"]').attr('disabled', true);
  const userData = extractDataForm('#form-signup');
  userData.terms = !!$('[name="terms"]').attr('checked');
  $.ajax({
    method: 'POST',
    url: e.target.action,
    data: userData,
    success() {
      let baseUrl = window.location.href.split('/');
      baseUrl[4] = 'cliente';
      window.location.href = baseUrl.join('/');
    },
    error({ responseJSON }) {
      console.error('Fail to register: review validation messages and try again');
      for (let err of responseJSON) {
        $(`#invalid-${err.field}`).text(err.message);
        $(`[name="${err.field}"]`).addClass('is-invalid');
      }
    },
    complete() {
      $('#form-signup [type="submit"]').removeAttr('disabled');
    }
  });
});

// Adicionar evento para checkbox
$('input[type="checkbox"]').on('change', function() {
  $(this).attr('checked', !$(this).attr('checked'));
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

// Contador de caracteres
function charCounter(e, selector, max) {
  $(selector).text(`Caracteres digitados: ${e.target.value.length}/${max}`);
}

// Constantes do módulo de categorias
const CATEGORY_TABLE = '#category-table';
const CATEGORY_FORM = '#category-form';
const CATEGORY_MODAL = '#category-modal';

// Excluir categoria
function deleteCategory(id, e) {
  if (confirm(`Tem certeza de que deseja excluir a categoria #${id}?`)) {
    const idStr = String(id).padStart(3, '0');
    $.ajax({
      method: 'POST',
      url: `${$(CATEGORY_FORM).attr('action')}?action=delete`,
      data: { id },
      success(response) {
        toastr.success(response.message);
        $(e.target).closest('tr').remove();
      },
      error(error) {
        const { status, responseJSON } = error;
        if (status == 422) {
          toastr.error(responseJSON.message);
        } else {
          console.error(error);
        }
      }
    });
  }
}

// Abrir modal/form para criação de categoria
function createCategory() {
  $(`${CATEGORY_FORM}-title`).text('Nova Categoria');
  $(CATEGORY_MODAL).modal('show');
  cleanForm(CATEGORY_FORM);
  $('#category-name').focus();
}

// Abrir modal/form para edição de categoria
function editCategory(id) {
  const idStr = String(id).padStart(3, '0');
  $(`${CATEGORY_FORM}-title`).text(`Editando Categoria #${idStr}`);
  $(CATEGORY_MODAL).modal('show');
  $.ajax({
    method: 'GET',
    url: `${$(CATEGORY_FORM).attr('action')}?id=${id}`,
    success(response) {
      cleanForm(CATEGORY_FORM);
      $(`${CATEGORY_FORM} [name="id"]`).val(response.id);
      $(`${CATEGORY_FORM} [name="name"]`).val(response.name).focus();
    },
    error(error) {
      console.error(error);
      $(CATEGORY_MODAL).modal('hide');
      toastr.error(`Falha ao tentar recuperar os dados da categoria #${idStr}`);
    }
  });
}

// Montar linha da tabela de categoria
function categoryRow(category = {}) {
  const escapedId = escapeHTML(category.id);
  return `
    <tr>
      <th scope="row" class="text-center">${escapedId.padStart(3, '0')}</th>
      <td>${escapeHTML(category.name)}</td>
      <td class="text-right">
        <button type="button" class="btn btn-sm btn-info" title="Editar" onclick="editCategory(${escapedId})"><i class="fas fa-edit"></i></button>
        <button type="submit" class="btn btn-sm btn-danger" title="Excluir" onclick="deleteCategory(${escapedId}, event)"><i class="fas fa-trash-alt"></i></button>
      </td>
    </tr>
  `;
}

// Configurar evento de submissão de formulário de categoria
$(CATEGORY_FORM).submit(e => {
  e.preventDefault();
  const category = extractDataForm(CATEGORY_FORM);
  $.ajax({
    method: 'POST',
    url: `${$(CATEGORY_FORM).attr('action')}?action=${!category.id ? 'new' : 'update'}`,
    data: category,
    success(response) {
      if (!category.id) {
        toastr.success('Categoria criada com sucesso');
        $(`${CATEGORY_TABLE} tbody`).append(categoryRow(response));
      } else {
        toastr.success('Categoria atualizada com sucesso');
        $(`${CATEGORY_TABLE} tbody tr`).filter((i, el) => Number($(el).children().first().text()) == category.id).replaceWith(categoryRow(response));
      }
      $(CATEGORY_MODAL).modal('hide');
    },
    error(error) {
      const { status, responseJSON } = error;
      if (status == 422) {
        responseJSON.forEach(err => toastr.error(err.message));
      } else {
        console.error(error);
      }
    }
  });
});

// Constantes do módulo de produtos
const PRODUCT_TABLE = '#product-table';
const PRODUCT_FORM = '#product-form';
const PRODUCT_MODAL = '#product-modal';

// Excluir produto
function deleteProduct(id, e) {
  if (confirm(`Tem certeza de que deseja excluir o produto #${id}?`)) {
    $.ajax({
      method: 'POST',
      url: `${$(PRODUCT_FORM).attr('action')}?action=delete`,
      data: { id },
      success(response) {
        toastr.success(response.message);
        $(e.target).closest('tr').remove();
      },
      error(error) {
        const { status, responseJSON } = error;
        if (status == 422) {
          toastr.error(responseJSON.message);
        } else {
          console.error(error);
        }
      }
    });
  }
}

// Abrir modal/form para criação de produto
function createProduct() {
  $(`${PRODUCT_FORM}-title`).text('Novo Produto');
  $(PRODUCT_MODAL).modal('show');
  cleanForm(PRODUCT_FORM);
  $('#category-name').focus();
}

// Abrir modal/form para edição de categoria
function editProduct(id) {
  const idStr = String(id).padStart(6, '0');
  $(`${PRODUCT_FORM}-title`).text(`Editando Produto #${idStr}`);
  $(PRODUCT_MODAL).modal('show');
  $.ajax({
    method: 'GET',
    url: `${$(PRODUCT_FORM).attr('action')}?id=${id}`,
    success(response) {
      cleanForm(PRODUCT_FORM);
      $(`${PRODUCT_FORM} [name="id"]`).val(response.id);
      $(`${PRODUCT_FORM} [name="name"]`).val(response.name).focus();
      $(`${PRODUCT_FORM} [name="weight"]`).val(response.weight);
      $(`${PRODUCT_FORM} [name="category"]`).val(response.category.id);
      $(`${PRODUCT_FORM} [name="utc_code"]`).val(response.utc);
      $(`${PRODUCT_FORM} [name="ean_code"]`).val(response.ean);
      $(`${PRODUCT_FORM} [name="description"]`).val(response.description);
    },
    error(error) {
      console.error(error);
      $(PRODUCT_MODAL).modal('hide');
      toastr.error(`Falha ao tentar recuperar os dados do produto #${idStr}`);
    }
  });
}

// Montar linha da tabela de produtos
function productRow(product = {}) {
  const escapedId = escapeHTML(product.id);
  const escapedWeight = String(Number(escapeHTML(product.weight)).toFixed(1)).replace('.', ',');
  const escapedUtc = escapeHTML(product.utc).replace(/(\d{4})(\d{4})(\d{4})/, '$1.$2.$3');
  return `
    <tr>
      <th scope="row" class="text-center">${escapedId.padStart(6, '0')}</th>
      <td class="text-left">${escapeHTML(product.name)}</td>
      <td class="text-right">${escapedWeight}</td>
      <td class="text-center">${escapeHTML(product.category.name)}</td>
      <td class="text-center">${escapedUtc}</td>
      <td class="text-right">
        <button type="button" class="btn btn-sm btn-info" title="Editar" onclick="editProduct(${escapedId})"><i class="fas fa-edit"></i></button>
        <button type="submit" class="btn btn-sm btn-danger" title="Excluir" onclick="deleteProduct(${escapedId}, event)"><i class="fas fa-trash-alt"></i></button>
      </td>
    </tr>
  `;
}

// Configurar evento de submissão de formulário de produto
$(PRODUCT_FORM).submit(e => {
  e.preventDefault();
  const product = extractDataForm(PRODUCT_FORM);
  $.ajax({
    method: 'POST',
    url: `${$(PRODUCT_FORM).attr('action')}?action=${!product.id ? 'new' : 'update'}`,
    data: product,
    success(response) {
      if (!product.id) {
        toastr.success('Produto criado com sucesso');
        $(`${PRODUCT_TABLE} tbody`).append(productRow(response));
      } else {
        toastr.success('Produto atualizado com sucesso');
        $(`${PRODUCT_TABLE} tbody tr`).filter((i, el) => Number($(el).children().first().text()) == product.id).replaceWith(productRow(response));
      }
      $(PRODUCT_MODAL).modal('hide');
    },
    error(error) {
      const { status, responseJSON } = error;
      if (status == 422) {
        responseJSON.forEach(err => toastr.error(err.message));
      } else {
        console.error(error);
      }
    }
  });
});
