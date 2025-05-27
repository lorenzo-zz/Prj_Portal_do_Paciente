document.addEventListener("DOMContentLoaded", function () {
  document.getElementById("consulta-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const nome = document.getElementById("nome-paciente");
    const cpf = document.getElementById("cpf-paciente");
    const data = document.getElementById("data");
    const telefone = document.getElementById("telefone");
    const email = document.getElementById("email");
    const especialidade = document.getElementById("especialidade");
    const horario = document.getElementById("horario");
    const form = document.getElementById('form-consulta');

    console.log(nome, cpf, data.value, telefone.value, email.value, especialidade.value, horario.value);
    if( !nome || !cpf || !data.value || !telefone.value || !email.value || !especialidade.value || !horario.value) {
      alert("Por favor, preencha todos os campos obrigatórios.");
      return;
    }

    const dados = {
      nomePaciente: nome,
      cpfPaciente: cpf,
      data: data.value,
      telefone: telefone.value,
      email: email.value,
      especificacaoMedico: especialidade.value,
      hora: horario.value + ":00" 
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
