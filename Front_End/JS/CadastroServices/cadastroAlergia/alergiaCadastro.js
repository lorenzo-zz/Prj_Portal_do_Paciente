document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('alergiaCadastro').addEventListener('submit', function (event) {
        event.preventDefault();

        const alergia = document.getElementById('alergiaSelect').value;
        const cpf = localStorage.getItem('cpf');

        const cadastrarAlergia = {
            pacienteCpf: cpf,                 // ✅ OBRIGATÓRIO na raiz
            descricao: "Descrição padrão",    // ✅ Adicione uma descrição, pode ser dinâmica se quiser
            nomeAlergia: alergia,             // ✅ Nome da alergia
            paciente: {                       // ✅ Objeto paciente com CPF
                pacienteCpf: cpf
            }
        };

        fetch('http://localhost:8080/paciente/alergias', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cadastrarAlergia)
        })
        .then(async response => {
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.erro || 'Erro desconhecido');
            }
            return response.text();
        })
        .catch(error => {
            alert('Erro ao cadastrar alergia: ' + error.message);
        });
    });
});
