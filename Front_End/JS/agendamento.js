document.addEventListener("DOMContentLoaded", function () {
  const especialidades = [
   "Acupuntura", "Alergologia", "Anestesiologia", "Angiologia", "Cardiologia", "Cirurgia Cardiovascular", "Cirurgia Geral", "Cirurgia Oncológica", "Cirurgia Pediátrica", "Cirurgia Plástica", "Cirurgia Torácica", "Cirurgia Vascular", "Cirurgia da Mão", "Cirurgia de Cabeça e Pescoço", "Cirurgia do Aparelho Digestivo", "Clínica Médica", "Dermatologia", "Medicina Geral", "Endocrinologia", "Endoscopia", "Gastroenterologia", "Genética Médica", "Geriatria", "Ginecologia e Obstetrícia", "Hematologia", "Hepatologia", "Homeopatia", "Infectologia", "Mastologia", "Medicina Aeroespacial", "Medicina Esportiva", "Medicina Física e Reabilitação", "Medicina Hiperbárica", "Medicina Intensiva", "Medicina Legal", "Medicina Nuclear", "Medicina Preventiva", "Medicina de Família e Comunidade", "Medicina do Trabalho", "Nefrologia", "Neurocirurgia", "Neurologia", "Nutrologia", "Oftalmologia", "Oncologia Clínica", "Ortopedia e Traumatologia", "Otorrinolaringologia", "Patologia", "Pediatria", "Pneumologia", "Psiquiatria", "Radiologia", "Radioterapia", "Reumatologia", "Sexologia", "Terapia Intensiva Pediátrica", "Toxicologia", "Urologia", "Vascular", 
  ];

  const horarios = [
    "08:00", "08:30", "09:00", "09:30", "10:00",
    "10:30", "11:00", "11:30", "13:00", "13:30",
    "14:00", "14:30", "15:00", "15:30", "16:00"
  ];

  const selectEspecialidade = document.getElementById("especialidade");
  const selectHorario = document.getElementById("horario");

  especialidades.forEach(especialidade => {
    const option = document.createElement("option");
    option.value = especialidade;
    option.textContent = especialidade;
    selectEspecialidade.appendChild(option);
  });

  horarios.forEach(horario => {
    const option = document.createElement("option");
    option.value = horario;
    option.textContent = horario;
    selectHorario.appendChild(option);
  });

  document.getElementById("consulta-form").addEventListener("submit", function (e) {
    e.preventDefault();
    document.getElementById("modal-alerta").classList.remove("hidden");

  });
});
function fecharModal() {
  document.getElementById("modal-alerta").classList.add("hidden");
}

function limparFormulario() {
  document.getElementById("consulta-form").reset();
}

