document.addEventListener('DOMContentLoaded', () => {
  const cpf = localStorage.getItem('cpf');

    
  const consultasPorPagina = 6;
  let paginaAtual = 0;

  document.getElementById("filtro-status").addEventListener("change", function () {
    paginaAtual = 0; 
    renderizarConsultas();
  });

  function renderizarConsultas() {
    const lista = document.getElementById("consultas-lista");
    lista.innerHTML = "";

    const filtroStatus = document.getElementById("filtro-status").value;

    let consultasFiltradas = consultas;
    if (filtroStatus !== "todos") {
      consultasFiltradas = consultas.filter(consulta => consulta.status === filtroStatus);
    }

    const inicio = paginaAtual * consultasPorPagina;
    const fim = inicio + consultasPorPagina;
    const pagina = consultasFiltradas.slice(inicio, fim);

    pagina.forEach((consulta, index) => {
      const card = document.createElement("a");
      card.href = `dadosConsulta.html?id=${inicio + index + 1}`;
      card.className = "consulta-card-link";
      card.innerHTML = `
            <div class="consulta-card">
                <div class="consulta-box">
                    <div class="consulta-info">
                        <h2>Consulta com ${consulta.nome}</h2>
                        <p><strong>Data:</strong> ${consulta.data}</p>
                        <p><strong>Hora:</strong> ${consulta.hora}</p>
                        <p><strong>Especialidade:</strong> ${consulta.especialidade}</p>
                    </div>
                    <div class="consulta-acoes">
                        <span class="status status-${consulta.status}">${capitalize(consulta.status)}</span>
                    </div>
                </div>
            </div>
        `;
      lista.appendChild(card);
    });

    document.getElementById("anterior").disabled = paginaAtual === 0;
    document.getElementById("proximo").disabled = fim >= consultasFiltradas.length;
  }

  document.getElementById("anterior").addEventListener("click", () => {
    if (paginaAtual > 0) {
      paginaAtual--;
      renderizarConsultas();
    }
  });

  document.getElementById("proximo").addEventListener("click", () => {
    if ((paginaAtual + 1) * consultasPorPagina < consultas.length) {
      paginaAtual++;
      renderizarConsultas();
    }
  });

  function capitalize(text) {
    return text.charAt(0).toUpperCase() + text.slice(1);
  }

  renderizarConsultas();

});