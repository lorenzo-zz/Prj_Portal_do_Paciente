document.addEventListener("DOMContentLoaded", function () {
  const cpf = localStorage.getItem("cpf");

  const dados = {
    cpf: cpf
  };

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
      const lista = document.getElementById('consultas-lista');
      lista.innerHTML = '';

      if (data.length === 0) {
        lista.innerHTML = '<p>Nenhuma consulta encontrada.</p>';
        return;
      }

      data.forEach((consulta) => {
        const card = `
        <div class="consulta-card">
        <h3>Nome Paciente: ${consulta.nomePaciente}</h3>
        <p><strong>Hora:</strong> ${consulta.hora}</p>
        <p><strong>Data:</strong> ${consulta.data}</p>
        <p><strong>Especialidade:</strong> ${consulta.especificacaoMedico}</p>
        <p><strong>Status:</strong> ${consulta.status}</p>
        </div>
         `;
        lista.insertAdjacentHTML('beforeend', card);
      });

      const cards = document.querySelectorAll('.consulta-card');
      cards.forEach((card, index) => {
        card.addEventListener('click', () => {
          localStorage.setItem('consultaId', data[index].id);
          window.location.href = 'http://172.20.208.1:5500/Front_End/HTML/dadosConsulta.html';
        });
      });
    })

    .catch(error => {
      console.error('Erro ao buscar consultas:', error.message);
    });
});
