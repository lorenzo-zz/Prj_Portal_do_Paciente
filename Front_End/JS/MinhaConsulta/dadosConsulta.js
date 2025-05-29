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
            data.forEach(paciente => {
                if (paciente.id == idConsulta) {
                    document.getElementById('nomeMedico').value = paciente.medico?.nome || '';
                    document.getElementById('especialidadeMedico').value = paciente.medico?.especificacaoMedico || '';
                    document.getElementById('crmMedico').value = paciente.medico?.crm || '';
                    document.getElementById('nomePaciente').value = paciente.paciente?.nome || '';
                    document.getElementById('cpfPaciente').value = paciente.paciente?.cpf || '';
                    document.getElementById('dataNascimentoPaciente').value = paciente.paciente?.dataNascimento || '';
                    document.getElementById('data-consulta').value = paciente.data || '';
                    document.getElementById('motivo_consulta').value = paciente.descricao || '';
                    document.getElementById('frequencia_cardiaca').value = paciente.frequencia || '';
                    document.getElementById('pressao_arterial').value = paciente.pressao_arterial || '';
                    document.getElementById('temperatura').value = paciente.temperatura || '';
                    document.getElementById('diagnostico_texto').value = paciente.resultado || '';
                }
            });
        })
        .catch(error => {
            console.error('Erro ao buscar consultas:', error.message);
        });
});
