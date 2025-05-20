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

    if (!sexo) {
      alert("Por favor, selecione o sexo.");
      return;
    }

    const cadastrarInfPessoais = {
      nome,
      cpf,
      email,
      telefone,
      sexo,
      dataNascimento: dt_nascimento,
      senha
    };

    console.log("Dados para cadastro:", cadastrarInfPessoais);


    fetch('http://localhost:8080/autenticar/cadastrar', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(cadastrarInfPessoais)
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
        window.location.href = '../Front_End/HTML/adressSignUp.html';
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
