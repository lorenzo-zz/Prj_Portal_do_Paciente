
document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('alergiaCadastro').addEventListener('submit', function (event) {
        event.preventDefault();


        const alergia = document.getElementById('alergiaSelect').value;
        const cpf = localStorage.getItem('cpf');

        const cadastrarAlergia = {
            nomeAlergia: alergia,
            paciente: {
                cpf: cpf
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
            .then(data => {  
            })
            .catch(error => {
            });
    });
});
