// Função para abrir o modal
function showModal() {
  const modal = document.getElementById("modal");
  modal.style.display = "block";
}

// Função para fechar o modal
function closeModal() {
  const modal = document.getElementById("modal");
  modal.style.display = "none";
}

// Função para carregar os dados do paciente
function carregarDadosPaciente() {
  const cpfPaciente = localStorage.getItem('cpf');

  fetch('http://localhost:8080/paciente/dados-paciente', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ cpf: cpfPaciente })
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Erro ao buscar os dados do paciente');
      }
      return response.json();
    })
    .then(dados => {
      console.log('Dados recebidos:', dados);

      document.getElementById('nomeCompleto').value = dados.nome;
      document.getElementById('telefone').value = dados.telefone;
      document.getElementById('email').value = dados.email;
      document.getElementById('dt_nascimento').value = dados.dataNascimento;
      document.getElementById('cep').value = dados.cep;
      document.getElementById('rua').value = dados.logradouro;
      document.getElementById('numero').value = dados.numero;
      document.getElementById('complemento').value = dados.complemento;
      document.getElementById('bairro').value = dados.bairro;
      document.getElementById('cidade').value = dados.cidade;
      document.getElementById('estado').value = dados.uf;
    })
    .catch(error => {
      console.error('Erro:', error);
      alert('Erro ao buscar dados do paciente');
    });
}

// Função para atualizar os dados do paciente
function atualizarDados() {
  const cpfStorage = localStorage.getItem('cpf');

  const dadosAtualizados = {
    cpf: cpfStorage,
    nomeCompleto: document.getElementById('nomeCompleto').value,
    telefone: document.getElementById('telefone').value,
    email: document.getElementById('email').value,
    dataNascimento: document.getElementById('dt_nascimento').value,
    cep: document.getElementById('cep').value,
    logradouro: document.getElementById('rua').value,
    numero: document.getElementById('numero').value,
    complemento: document.getElementById('complemento').value,
    bairro: document.getElementById('bairro').value,
    cidade: document.getElementById('cidade').value,
    uf: document.getElementById('estado').value
  };

  console.log('Dados enviados:', dadosAtualizados);

  fetch('http://localhost:8080/paciente/atualizar-dados', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(dadosAtualizados)
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Erro ao atualizar os dados do paciente');
      }
      return response.json();
    })
    .then(response => {
      console.log('Dados atualizados:', response);
      showModal();
      carregarDadosPaciente(); 
    })
    .catch(error => {
      console.error('Erro:', error);
      alert('Erro ao atualizar os dados.');
    });
}

document.addEventListener('DOMContentLoaded', function() {
  carregarDadosPaciente();

  document.getElementById('salvar-butao').addEventListener('click', function () {
    atualizarDados();
  });
});