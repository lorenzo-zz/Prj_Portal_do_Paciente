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
}

function atualizarLista() {
  const container = document.getElementById('alergiasSelecionadas');
  container.innerHTML = '';

  alergias.forEach(alergia => {
    const div = document.createElement('div');
    div.className = 'alergia';
    div.innerHTML = `${alergia} <button onclick="removerAlergia('${alergia}')">X</button>`;
    container.appendChild(div);
  });
}