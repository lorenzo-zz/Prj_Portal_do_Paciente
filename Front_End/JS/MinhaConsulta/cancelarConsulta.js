document.addEventListener('DOMContentLoaded', function () {
    document.getElementById("cancelar_consulta").addEventListener('submit', function (event) {
        event.preventDefault();

        const idConsulta = localStorage.getItem('consultaId');

        const dados = {
            idMinhaConsulta: idConsulta
        }

        fetch('http://localhost:8080/consulta/caneldar-consulta', {
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
                alert('consulta cancelada com sucesso! ( Teste )');
            })
            .catch(error => {
                alert('Erro ao cancelar a consulta: ' + error.message);
            });
    })
});