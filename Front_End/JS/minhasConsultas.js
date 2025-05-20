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
      status: "confirmada",
    },
    {
      nome: "Dr. Carlos Mendes",
      data: "28/05/2025",
      hora: "16:00",
      especialidade: "Ortopedia",
      status: "cancelada",
    },
    {
      nome: "Dr. Pedro Henrique de Borba",
      data: "30/05/2025",
      hora: "11:00",
      especialidade: "fazer coisa errada no github",
      status: "confirmada",
    },
    {
      nome: "Dr. P2",
      data: "01/06/2025",
      hora: "15:00",
      especialidade: "p2 para de mexer no github",
      status: "confirmada",
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
      status: "confirmada",
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

document.getElementById("filtro-status").addEventListener("change", function () {
    paginaAtual = 0; // Resetando a página ao mudar o filtro
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

// Inicializa a renderização das consultas
renderizarConsultas();