document.addEventListener("DOMContentLoaded", function () {
  const cpf = localStorage.getItem("cpf");
  const filtroStatus = document.getElementById("filtro-status");
  const lista = document.getElementById("consultas-lista");
  const btnAnterior = document.getElementById("anterior");
  const btnProximo = document.getElementById("proximo");

  let todasConsultas = [];
  let consultasFiltradas = [];
  let paginaAtual = 0;
  const consultasPorPagina = 6;

  const dados = { cpf };

  fetch('http://localhost:8080/consulta/listar-consultas', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(dados)
  })
    .then(async response => {
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.erro || 'Erro ao buscar consultas');
      }
      return response.json();
    })
    .then(data => {
      todasConsultas = data;
      aplicarFiltro(); // inicia com tudo exibido
    })
    .catch(error => {
      console.error('Erro ao buscar consultas:', error.message);
    });

  function aplicarFiltro() {
    const statusSelecionado = filtroStatus.value;

    if (statusSelecionado === "todos") {
      consultasFiltradas = [...todasConsultas];
    } else if (statusSelecionado === "mais-recente") {
      consultasFiltradas = [...todasConsultas].sort((a, b) => {
        const dataA = new Date(`${a.data} ${a.hora}`);
        const dataB = new Date(`${b.data} ${b.hora}`);
        return dataB - dataA;
      });
    } else {
      consultasFiltradas = todasConsultas.filter(c => c.status.toLowerCase() === statusSelecionado);
    }

    paginaAtual = 0;
    renderizarPagina();
  }

  function renderizarPagina() {
    lista.innerHTML = '';

    if (consultasFiltradas.length === 0) {
      lista.innerHTML = '<p>Nenhuma consulta encontrada.</p>';
      btnAnterior.disabled = true;
      btnProximo.disabled = true;
      return;
    }

    const inicio = paginaAtual * consultasPorPagina;
    
    const fim = inicio + consultasPorPagina;
    const pagina = consultasFiltradas.slice(inicio, fim);

    pagina.forEach((consulta, index) => {
      const card = `
        <div class="consulta-card">
          <div class="consulta-info">
            <p><strong>Hora:</strong> ${consulta.hora}</p>
            <p><strong>Data:</strong> ${consulta.data}</p>
            <p><strong>Especialidade:</strong> ${consulta.especificacaoMedico}</p>
          </div>
          <div class="consulta-acoes">
            <span class="status status-${consulta.status.toLowerCase()}">${consulta.status}</span>
          </div>
        </div>
      `;
      lista.insertAdjacentHTML('beforeend', card);
    });

    // Evento de clique nos cards
    const cards = document.querySelectorAll('.consulta-card');
    cards.forEach((card, i) => {
      card.addEventListener('click', () => {
        const indexConsulta = paginaAtual * consultasPorPagina + i;
        localStorage.setItem('consultaId', consultasFiltradas[indexConsulta].id);
        window.location.href = 'http://172.20.208.1:5500/Front_End/HTML/dadosConsulta.html';
      });
    });

    // Controle de botões
    btnAnterior.disabled = paginaAtual === 0;
    btnProximo.disabled = fim >= consultasFiltradas.length;
  }

  // Evento no filtro
  filtroStatus.addEventListener("change", aplicarFiltro);

  // Eventos dos botões de paginação
  btnAnterior.addEventListener("click", () => {
    if (paginaAtual > 0) {
      paginaAtual--;
      renderizarPagina();
    }
  });

  btnProximo.addEventListener("click", () => {
    if ((paginaAtual + 1) * consultasPorPagina < consultasFiltradas.length) {
      paginaAtual++;
      renderizarPagina();
    }
  });
});
