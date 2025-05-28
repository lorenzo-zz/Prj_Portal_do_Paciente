const atestados = [
  { Medico: 'Dra. Ana Paula Lima', Data: '07/04/2025', Hora: '09:00', Descricao: 'Consulta cardiológica' },
  { Medico: 'Dr. Bruno Costa', Data: '08/04/2025', Hora: '10:00', Descricao: 'Avaliação ortopédica' },
  { Medico: 'Dra. Camila Torres', Data: '09/04/2025', Hora: '11:00', Descricao: 'Acompanhamento pediátrico' },
  { Medico: 'Dr. Daniel Ribeiro', Data: '10/04/2025', Hora: '12:00', Descricao: 'Tratamento de acne' },
  { Medico: 'Dra. Elisa Martins', Data: '11/04/2025', Hora: '13:00', Descricao: 'Avaliação neurológica' },
  { Medico: 'Dr. Fernando Souza', Data: '12/04/2025', Hora: '14:00', Descricao: 'Exame de visão' },
  { Medico: 'Dra. Gabriela Nunes', Data: '13/04/2025', Hora: '15:00', Descricao: 'Consulta ginecológica' },
  { Medico: 'Dr. Henrique Duarte', Data: '14/04/2025', Hora: '16:00', Descricao: 'Exame de próstata' },
  { Medico: 'Dra. Isabela Castro', Data: '15/04/2025', Hora: '17:00', Descricao: 'Sessão de terapia' },
  { Medico: 'Dr. João Vitor Almeida', Data: '16/04/2025', Hora: '18:00', Descricao: 'Controle de diabetes' },
  { Medico: 'Dra. Ana Paula Lima', Data: '17/04/2025', Hora: '09:00', Descricao: 'Eletrocardiograma' },
  { Medico: 'Dr. Bruno Costa', Data: '18/04/2025', Hora: '10:00', Descricao: 'Revisão de lesão muscular' },
  { Medico: 'Dra. Camila Torres', Data: '19/04/2025', Hora: '11:00', Descricao: 'Vacinação infantil' },
  { Medico: 'Dr. Daniel Ribeiro', Data: '20/04/2025', Hora: '12:00', Descricao: 'Consulta dermatológica' },
  { Medico: 'Dra. Elisa Martins', Data: '21/04/2025', Hora: '13:00', Descricao: 'Análise de dores de cabeça' },
  { Medico: 'Dr. Fernando Souza', Data: '22/04/2025', Hora: '14:00', Descricao: 'Teste de acuidade visual' },
  { Medico: 'Dra. Gabriela Nunes', Data: '23/04/2025', Hora: '15:00', Descricao: 'Prevenção feminina anual' }
];

function gerarAtestadoPDF(atestado) {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();
    const frase = `Atestado médico: O paciente compareceu à consulta realizada no dia ${atestado.Data} às ${atestado.Hora} com o médico ${atestado.Medico}.`;
    doc.text('Atestado Médico', 10, 10);
    doc.text(frase, 10, 20);
    doc.text('Motivo: ' + atestado.Descricao, 10, 30);
    doc.save(`atestado_${atestado.Medico.replace(' ', '_')}_${atestado.Data}.pdf`);
}

window.onload = function() {
    const lista = document.getElementById('atestados-lista');
    atestados.forEach((atestado, index) => {
        const row = `<tr>
            <td>${atestado.Medico}</td>
            <td>${atestado.Data}</td>
            <td>${atestado.Hora}</td>
            <td>${atestado.Descricao}</td>
            <td><button onclick="gerarAtestadoPDF(atestados[${index}])">Baixar PDF</button></td>
        </tr>`;
        lista.insertAdjacentHTML('beforeend', row);
    });
}