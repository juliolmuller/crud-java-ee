
// Buscar CEP em API externa
function buscarCEP(e) {
  const cep = e.target.value;
  const formatoCEP = /^[0-9]{8}$/;
  if (cep != '' && formatoCEP.test(cep)) {
    $('#cep').attr('readonly', true);
    $('#rua').val('');
    $('#numero').val('');
    $('#cidade').val('');
    $('#estado').val('');
    $.ajax({
      method: 'GET',
      url: `https://viacep.com.br/ws/${cep}/json`,
      success(response) {
        $('#rua').val(response.logradouro);
        $('#cidade').val(response.localidade);
        $('#estado').val(response.uf);
        $('#numero').focus();
      },
      complete() {
        $('#cep').attr('readonly', false);
      }
    });
  }
}
