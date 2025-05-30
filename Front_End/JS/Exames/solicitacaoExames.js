
function limparFormulario() {
  document.getElementById("exames-form").reset();
}


document.addEventListener("DOMContentLoaded", function () {
  document.getElementById("cpf-paciente").value = localStorage.getItem("cpf");
  document.getElementById("consulta-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const cpf = localStorage.getItem('cpf');
    const tipoExame = document.getElementById("tipo-exame");
    const convenio = document.getElementById("convenio");
    const telefone = document.getElementById("telefone");
    const email = document.getElementById("email");
    const observacoes = document.getElementById("observacoes");

    document.getElementById("cpf-paciente").values = localStorage.getItem("cpf");

    if (!nome || !cpf || !data || !telefone || !email || !especialidade || !horario) {
      alert("Por favor, preencha todos os campos obrigatórios.");
      return;
    }

    const dados = {
      pacienteCpf: cpf.value,
      tipoExame: tipoExame.value,
      tipoConvenio: convenio.value,
      telefone: telefone.value,
      email: email.value,
      observacoes: observacoes.value
    };

    let formData = new FormData();
    const docInput = document.getElementById("anexar-arquivo");
    const documento = docInput.files[0];


    formData.append("dados", new Blob([JSON.stringify(cadastrarInfPessoais)], { type: "application/json" }));
    formData.append("documento", documento);

    fetch('http://localhost:8080/consulta/agendar-consulta', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: formData
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

    const requisicaoExameDTO = {
      pacienteCpf: document.getElementById("cpf-paciente").value,
      tipoExame: document.getElementById("tipo-exame").value,
      tipoConvenio: document.getElementById("convenio").value,
      telefone: document.getElementById("telefone").value,
      email: document.getElementById("email").value,
      observacoes: document.getElementById("observacoes").value  // <-- nome correto
    };

    console.log(requisicaoExameDTO);

    const formData = new FormData();
    const guiaMedicaInput = document.getElementById("anexar-arquivo");
    const arquivo = guiaMedicaInput.files[0];

    formData.append(
      "requisicaoExameDTO",
      new Blob([JSON.stringify(requisicaoExameDTO)], { type: "application/json" })
    );
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
      });

  });

});


function mostrarModal() {
  document.getElementById("modal-confirmacao").classList.remove("hidden");
}

function fecharModal() {
  document.getElementById("modal-confirmacao").classList.add("hidden");
}
  document.getElementById("exames-form").addEventListener("submit", function (e) {
  e.preventDefault();
  mostrarModal(); 

});
