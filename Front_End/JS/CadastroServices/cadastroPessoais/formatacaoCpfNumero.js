document.addEventListener("DOMContentLoaded", function () {
  const campoCPF = document.getElementById("cpf");
  const campoTelefone = document.getElementById("telefone");
  const campoNome = document.getElementById("nome");

  if (campoCPF) {
    campoCPF.addEventListener("input", function () {
      this.value = this.value.replace(/\D/g, "");
    });
  }

  if (campoTelefone) {
    campoTelefone.addEventListener("input", function () {
      this.value = this.value.replace(/\D/g, "");
    });
  }

  if (campoNome) {
    campoNome.addEventListener("input", function () {
      this.value = this.value.replace(/[^a-zA-ZÀ-ÿ\s]/g, "");
    });
  }
});
