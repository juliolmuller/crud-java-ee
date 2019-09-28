
// Adicionar eventos de submissão de formulário
document.getElementById('form-login').addEventListener('submit', e => {
  e.preventDefault();
  $('.modal').modal('show');
});
document.getElementById('form-signup').addEventListener('submit', e => {
  e.preventDefault();
});
