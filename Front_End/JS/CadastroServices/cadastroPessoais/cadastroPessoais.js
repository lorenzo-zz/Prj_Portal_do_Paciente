document.addEventListener('DOMContentLoaded', function () {
  document.getElementById('cadastroForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const cpf = document.getElementById('cpf').value;
    const telefone = document.getElementById('telefone').value;
    const email = document.getElementById('email').value;
    const sexoInput = document.querySelector('input[name="sexo"]:checked');
    const sexo = sexoInput ? sexoInput.value : '';
    const dt_nascimento = document.getElementById('dt_nascimento').value;
    const senha = document.getElementById('senha').value;
    const confirmarSenha = document.getElementById('confirmar_senha').value;

    const erroIdade = document.querySelector('.erroIdade');
    const cpfErro = document.querySelector('.erroCPF');
    const emailErro = document.querySelector('.erroEMAIL');
    const erroSenha = document.querySelector('.erroSENHA');
    const erroArquivo = document.querySelector('.erroDocumento');
    const erroSenhaInexistente = document.querySelector('.senhaVazia');


    cpfErro.style.display = 'none';
    emailErro.style.display = 'none';
    erroSenha.style.display = 'none';
    erroIdade.style.display = 'none';
    erroArquivo.style.display = 'none';
    erroSenhaInexistente.style.display = 'none';


    if (senha == null) {
      erroSenhaInexistente.style.display = 'block';
      return;
    }

    const cadastrarInfPessoais = {
      nome: nome,
      cpf: cpf,
      email: email,
      telefone: telefone,
      sexo: sexo,
      dataNascimento: dt_nascimento,
      senha: senha
    };

    const formData = new FormData();
    const arquivoInput = document.getElementById("arquivo");
    const arquivo = arquivoInput.files[0];



    formData.append("paciente", new Blob([JSON.stringify(cadastrarInfPessoais)], { type: "application/json" }));
    formData.append("arquivo", arquivo);

    fetch('http://localhost:8080/autenticar/cadastrar', {
      method: 'POST',
      body: formData
    })
      .then(async response => {
        if (!response.ok) {
          const errorText = await response.text();
          throw new Error(errorText);
        }
        return response.json();
      })
      .then(data => {


        localStorage.setItem('cpf', data.cpf);
        localStorage.setItem('nome', data.nome);
        localStorage.setItem('cpf', cpf);
        localStorage.setItem('nome', nome);
        window.location.href = 'http://127.0.0.1:5500/Front_End/HTML/adressSignUp.html';
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

        if (msg.includes("cpf") && msg.includes("cadastrado")) {
          cpfErro.style.display = 'block';
        } else if (msg.includes("email") && msg.includes("cadastrado")) {
          emailErro.style.display = 'block';
        } else if (msg.includes("idade") && msg.includes("18 anos")) {
          erroIdade.style.display = 'block';
        } else if (msg.includes("arquivo") && msg.includes("present")){
          erroArquivo.style.display = 'block';
        } else if (msg.includes("Erro com o banco de dados: Erro ao cadastrar paciente: ORA-20999: Erro inesperado: ORA-01400: n├úo ├® poss├¡vel inserir NULL em")){
          erroSenhaInexistente.style.display = 'block'
        }
      });
  });
});
