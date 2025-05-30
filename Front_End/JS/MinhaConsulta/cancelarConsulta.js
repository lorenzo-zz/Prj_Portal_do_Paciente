document.addEventListener('DOMContentLoaded', function () {
    document.getElementById("btnCancelarConsulta").addEventListener('click', function (event) {
        event.preventDefault();

        const idConsulta = localStorage.getItem('consulta');
        const idAgendamento = localStorage.getItem('consultaId');

        const dados = {
            idMinhaConsulta: idConsulta,
            idAgendamento: idAgendamento
        };

        fetch('http://localhost:8080/consulta/cancelar-consulta', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados)
        })
        .then(async response => {
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.erro || 'Erro desconhecido');
            }
            return response.text();
        })
        .then(data => {
            alert('Consulta cancelada com sucesso!');
        })
        .catch(error => {
            alert('Erro ao cancelar a consulta: ' + error.message);
        });
    });
});
