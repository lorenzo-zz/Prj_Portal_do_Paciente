
const alergias = new Set();

function adicionarAlergia() {
  const select = document.getElementById('alergiaSelect');
  const alergia = select.value;

  if (alergia && !alergias.has(alergia)) {
    alergias.add(alergia);
    atualizarLista();
  }
}

function removerAlergia(alergia) {
  alergias.delete(alergia);
  atualizarLista();
  remover(alergia);
}

function atualizarLista() {
  const container = document.getElementById('alergiasSelecionadas');
  container.innerHTML = '';

  alergias.forEach(alergia => {
    const div = document.createElement('div');
    div.className = 'alergia';
    div.innerHTML = `${alergia} <button onclick="removerAlergia('${alergia}')">X</button>`;
    container.appendChild(div);
  }
  );
}

function remover(alergia) {
  const cpf = localStorage.getItem('cpf');

  const dados = {
    nomeAlergia: alergia,
    paciente: {
      cpf: cpf
    }
  };

  fetch(`http://localhost:8080/paciente/alergias-remover`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(dados)
  })
    .then(async response => {
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.erro || 'Erro ao remover alergia');
      }
    })
    .catch(error => {
      console.error(error);
    });
}