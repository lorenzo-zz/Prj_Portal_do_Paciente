document.addEventListener("DOMContentLoaded", function () {
  const campoCPF = document.getElementById("cpf");

  if (campoCPF) {
    campoCPF.addEventListener("input", function () {
      this.value = this.value.replace(/\D/g, "");
    });
  }
}); 
