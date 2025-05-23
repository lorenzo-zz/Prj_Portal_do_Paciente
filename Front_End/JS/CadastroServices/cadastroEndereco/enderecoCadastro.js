
document.addEventListener('DOMContentLoaded', function () {  
    document.getElementById('enderecoCadastroForm').addEventListener('submit', function (event) {
        event.preventDefault();


        const cep = document.getElementById('cep').value;
        const uf = document.getElementById('estado').value;
        const cidade = document.getElementById('cidade').value;
        const bairro = document.getElementById('bairro').value;
        const logradouro = document.getElementById('logradouro').value;
        const numero = document.getElementById('numero').value;
        const complemento = document.getElementById('complemento').value;
        const cpfPaciente = localStorage.getItem('cpf');

        const cadastrarEndereco = {
            cpfPaciente: cpfPaciente,
            cep : cep,
            logradouro : logradouro,
            cidade : cidade,
            uf : uf,
            bairro : bairro,
            complemento : complemento,
            numero : numero
        };

        console.log("Dados para cadastro de endereço:", cadastrarEndereco);

        fetch('http://localhost:8080/autenticar/cadastrar/endereco', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cadastrarEndereco)
        })
            .then(async response => {
                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.erro || 'Erro desconhecido');
                }
                return response.text(); 
            })
            .then(data => {
                window.location.href = 'http://172.20.208.1:5500/Front_End/HTML/index.html';
            })
            .catch(error => {
                const msg = error.message;
            });
    });
});
