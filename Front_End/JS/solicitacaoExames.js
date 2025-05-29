

function limparFormulario() {
  document.getElementById("exames-form").reset();
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