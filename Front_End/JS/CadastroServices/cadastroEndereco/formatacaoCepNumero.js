document.addEventListener("DOMContentLoaded", function () {
  const campoCEP = document.getElementById("cep");
  const campoNumero = document.getElementById("numero");

  // Só números no campo CEP
  campoCEP.addEventListener("input", function () {
    this.value = this.value.replace(/\D/g, '');
  });

  // Só números no campo número da casa
  campoNumero.addEventListener("input", function () {
    this.value = this.value.replace(/\D/g, '');
  });
});
