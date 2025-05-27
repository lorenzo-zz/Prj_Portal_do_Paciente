document.addEventListener("DOMContentLoaded", function () {
  document.getElementById("consulta-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const nome = document.getElementById("nome-paciente").value;
    const cpf = document.getElementById("cpf-paciente").value;
    const data = document.getElementById("data").value;
    const telefone = document.getElementById("telefone").value;
    const email = document.getElementById("email").value;
    const especialidade = document.getElementById("especialidade").value;
    const horario = document.getElementById("horario").value;

    if (!nome || !cpf || !data || !telefone || !email || !especialidade || !horario) {
      alert("Por favor, preencha todos os campos obrigatórios.");
      return;
    }

    const dados = {
      nomePaciente: nome,
      cpfPaciente: cpf,
      data: data,
      telefone: telefone,
      email: email,
      especificacaoMedico: especialidade,
      hora: horario + ":00"
    };

    fetch('http://localhost:8080/consulta/agendar-consulta', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(dados)
    })
      .then(async response => {
        if (!response.ok) {
          const errorData = await response.json();
          throw new Error(errorData.erro || 'Erro ao agendar consulta');
        }
        return response.text();
      }).then(data => {
        document.getElementById('modal-alerta').classList.remove('hidden');
        form.reset();
      }).catch(error => {
      })
  });
});

function limparFormulario() {
  const form = document.getElementById("consulta-form");
  form.reset();
}

function fecharModal() {
  const modal = document.getElementById('modal-alerta');
  modal.classList.add('hidden');
  limparFormulario();
}

// ------------------------------ Validação dos campos

document.addEventListener("DOMContentLoaded", function () {
  const campoCPF = document.getElementById("cpf-paciente");
  const campoTelefone = document.getElementById("telefone");
  const campoNome = document.getElementById("nome-paciente");

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
