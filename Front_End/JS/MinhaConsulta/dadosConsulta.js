document.addEventListener("DOMContentLoaded", function () {

    const idConsulta = localStorage.getItem("consultaId");

    const dados = {
        idAgendamento: idConsulta
    };

    fetch('http://localhost:8080/consulta/dados-consulta', {
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
            data.array.forEach(dadosPaciente => {
                if (dadosPaciente.id = idConsulta)
                    document.getElementById('nomeMedico').value = dadosPaciente.medico.nome;
            });
        })
        .catch(error => {
            console.error('Erro ao buscar consultas:', error.message);
        });
});
