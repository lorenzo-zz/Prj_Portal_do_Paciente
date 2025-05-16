const consultas = [
    {
      nome: "Dr. João Silva",
      data: "20/05/2025",
      hora: "14:00",
      especialidade: "Cardiologia",
      status: "confirmada",
    },
    {
      nome: "Dra. Ana Juliana",
      data: "25/05/2025",
      hora: "09:30",
      especialidade: "Dermatologia",
      status: "pendente",
    },
    {
      nome: "Dr. Carlos Mendes",
      data: "28/05/2025",
      hora: "16:00",
      especialidade: "Ortopedia",
      status: "cancelada",
    },
    {
      nome: "Dr. Luis Inácio da Silva",
      data: "30/05/2025",
      hora: "11:00",
      especialidade: "Petrolão",
      status: "confirmada",
    },
    {
      nome: "Dr. Leo Lins",
      data: "01/06/2025",
      hora: "15:00",
      especialidade: "Thais Carla",
      status: "pendente",
    },
    {
      nome: "Dra. Carla Dias",
      data: "03/06/2025",
      hora: "10:00",
      especialidade: "Neurologia",
      status: "confirmada",
    },
    {
      nome: "Dr. Ricardo Torres",
      data: "05/06/2025",
      hora: "13:30",
      especialidade: "Pediatria",
      status: "pendente",
    },
    {
      nome: "Dra. Camila Rocha",
      data: "07/06/2025",
      hora: "11:15",
      especialidade: "Ginecologia",
      status: "cancelada",
    },
    {
      nome: "Dr. Eduardo Luz",
      data: "09/04/2025",
      hora: "09:00",
      especialidade: "Oftalmologia",
      status: "finalizada",
    }
  ];
   
  const consultasPorPagina = 6;
  let paginaAtual = 0;
   
  function renderizarConsultas() {
    const lista = document.getElementById("consultas-lista");
    lista.innerHTML = "";
   
    const inicio = paginaAtual * consultasPorPagina;
    const fim = inicio + consultasPorPagina;
    const pagina = consultas.slice(inicio, fim);
   
    pagina.forEach((consulta, index) => {
      const card = document.createElement("a");
      card.href = `consulta.html?id=${inicio + index + 1}`;
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
    document.getElementById("proximo").disabled = fim >= consultas.length;
  }
   
  function capitalize(text) {
    return text.charAt(0).toUpperCase() + text.slice(1);
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
   
  renderizarConsultas();