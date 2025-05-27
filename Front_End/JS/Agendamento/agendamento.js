document.addEventListener("DOMContentLoaded", function () {
  document.getElementById("consulta-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const nome = localStorage.getItem("nome");
    const cpf = localStorage.getItem("cpf");
    const data = document.getElementById("data");
    const telefone = document.getElementById("telefone");
    const email = document.getElementById("email");
    const especialidade = document.getElementById("especialidade");
    const horario = document.getElementById("horario");

    if( !nome || !cpf || !data.value || !telefone.value || !email.value || !especialidade.value || !horario.value) {
      alert("Por favor, preencha todos os campos obrigatórios.");
      return;
    }

    const dados = {
      nomePaciente: nome,
      cpfPaciente: cpf,
      data: data.value,
      telefone: telefone.value,
      email: email.value,
      especificacaoMedico: especialidade.value,
      hora: horario.value + ":00" 
    };

    fetch('http://localhost:8080/consulta/agendar-consulta', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(dados)
    })
      .then(async response => {
        if (!response.ok) {
          const errorData = await response.json();
          throw new Error(errorData.erro || 'Erro ao agendar consulta');
        }
        return response.text();
      }).then(data => {
        
      }).catch(error => {
      })
  });
});