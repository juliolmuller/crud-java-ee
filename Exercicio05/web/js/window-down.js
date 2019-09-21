(function() {
  'use strict';

  function forgotPassword(e) {
    e.preventDefault();
    alert(
      'Vish, amigo! Mancada sua... \n' +
      'Esta aplicação não possui sistema de recuperação automática de senhas. Contate o administrador.'
    );
  }

  document
    .getElementById('forgotPassword')
    .addEventListener('click', forgotPassword);

})();
