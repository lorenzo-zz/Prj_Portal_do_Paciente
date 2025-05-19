const atestados = [
    { Medico: 'Dr. Jeremias', Data: '20/05/2025', Hora: '14:00', Descricao: 'Consulta de rotina' },
    { Medico: 'Dra. Ana', Data: '22/05/2025', Hora: '10:30', Descricao: 'Retorno pós-operatório' }
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