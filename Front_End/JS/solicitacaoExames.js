
function limparFormulario() {
  document.getElementById("exames-form").reset();
}


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
    document.getElementById('cpf-paciente').value = cpf;

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







// ------------------------------- Validação dos campos 

document.addEventListener("DOMContentLoaded", function () {

  document.getElementById("exames-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const campoCPF = document.getElementById("cpf-paciente").value;
    const campoTelefone = document.getElementById("telefone").value;
    const campoTipoExame = document.getElementById("tipo-exame").value;
    const campoConvenio = document.getElementById("convenio").value;
    const campoEmail = document.getElementById("email").value;
    const campoObservacoes = document.getElementById("observacoes").value;

    
    const requisicaoExameDTO = {
      pacienteCpf: campoCPF,
      telefone: campoTelefone,
      tipoExame: campoTipoExame,
      tipoConvenio: campoConvenio,
      email: campoEmail,
      observacoes: campoObservacoes
    }

    console.log(requisicaoExameDTO);
    
    const formData = new FormData();
    const guiaMedicaInput = document.getElementById("anexar-arquivo");
    const arquivo = guiaMedicaInput.files[0];

    formData.append("requisicaoExameDTO", new Blob([JSON.stringify(requisicaoExameDTO)], { type: "application/json" }));
    formData.append("arquivo", arquivo);

    fetch('http://localhost:8080/exame/prescricao/requisicao-exame', {
      method: 'POST',
      body: formData
    })
      .then(async response => {
        if (!response.ok) {
          const errorText = await response.text();

          let errorData;
          try {
            errorData = JSON.parse(errorText);
          } catch {
            errorData = { message: errorText };
          }
          throw {
            status: response.status,
            data: errorData
          };

        }

      })
      .catch(async error => {
        let errorMsg = '';

        try {
          const errJson = await error.response.json();
          errorMsg = errJson.message || errJson.erro || JSON.stringify(errJson);
        } catch (e) {
          errorMsg = error.message || "Erro desconhecido";
        }

        const msg = errorMsg.toLowerCase();

        if (msg.includes("descrição") && msg.includes("500")) {
          descricaoErro.style.display = 'block';
        } else if (msg.includes("tipo") && msg.includes("200")) {
          tipoExameErro.style.display = 'block';
        }
      })
  })
})
  ; 