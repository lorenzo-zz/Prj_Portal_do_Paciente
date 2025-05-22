document.addEventListener('DOMContentLoaded', function () {
  document.getElementById('cadastroForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const cpf = document.getElementById('cpf').value;
    const telefone = document.getElementById('telefone').value;
    const email = document.getElementById('email').value;
    const sexoInput = document.querySelector('input[name="sexo"]:checked'); //verifica qual dos botões está selecionado masculino ou feminino
    const sexo = sexoInput ? sexoInput.value : '';
    const dt_nascimento = document.getElementById('dt_nascimento').value;
    const senha = document.getElementById('senha').value;

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
          const errorData = await response.json();
          throw new Error(errorData.erro || 'Erro desconhecido');
        }
        return response.text();
      })
      .then(data => {
        localStorage.setItem('nome', nome);
        window.location.href = '/Front_End/HTML/adressSingUp.html';
      })
      .catch(error => {
        const msg = error.message;
        erroCadastrar(msg);
      });
  });
});

function erroCadastrar(msg) {
  if (msg === 'Erro ao cadastrar as informações pessoais!') {
    document.querySelector('.erroCadastrar').style.display = 'block';
  }
}
