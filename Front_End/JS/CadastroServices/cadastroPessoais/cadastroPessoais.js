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
    const erroNulo = document.querySelector('.erroNulo');
    const cpfErro = document.querySelector('.erroCPF');
    const emailErro = document.querySelector('.erroEMAIL');
    const erroSenha = document.querySelector('.erroSENHA');

    erroNulo.style.display = 'none';
    cpfErro.style.display = 'none';
    emailErro.style.display = 'none';
    erroSenha.style.display = 'none';
    erroIdade.style.display = 'none';

    if (nome === "" || cpf === "" || telefone === "" || email === "" || sexo === "" || dt_nascimento === "" || senha === "") {
      erroNulo.style.display = 'block';
      return;
    }

    if (senha !== confirmarSenha) {
      erroSenha.style.display = 'block';
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

    if (!arquivo) {
      erroNulo.style.display = 'block';
      return;
    }

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
        localStorage.setItem('cpf', cpf);
        localStorage.setItem('nome', nome);
        window.location.href = 'http://172.20.208.1:5500/Front_End/HTML/adressSignUp.html';
      })
      .catch(async error => {
        let errorMsg = '';

        try {
          const errJson = await error.response.json(); // tenta pegar erro do corpo
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
        } else {
          alert("Erro: " + errorMsg);
        }
      });
  });
});
