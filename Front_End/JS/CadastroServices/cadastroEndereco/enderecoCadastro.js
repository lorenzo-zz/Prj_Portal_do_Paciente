document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('cep').addEventListener('blur', function () {
        const cep = this.value.replace(/\D/g, '');

        if (cep.length !== 8) {
            alert('CEP inválido!');
            return;
        }

        fetch(`http://localhost:8080/cep/${cep}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('CEP não encontrado');
                }
                return response.json();
            })
            .then(data => {
                if(data.erro) {
                    throw new Error('CEP não encontrado');
                }
                document.getElementById('logradouro').value = data.logradouro || '';
                document.getElementById('bairro').value = data.bairro || '';
                document.getElementById('cidade').value = data.localidade || '';
                document.getElementById('estado').value = data.uf || '';
            })
            .catch(error => {
                console.error('Erro ao buscar CEP:', error);
                alert('Erro ao buscar o CEP. Verifique se está correto.');
            });
    });


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
            cep: cep,
            logradouro: logradouro,
            cidade: cidade,
            uf: uf,
            bairro: bairro,
            complemento: complemento,
            numero: numero
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
                window.location.href = 'http://127.0.0.1:5500/Front_End/HTML/cadastroAlergias.html';
            })
            .catch(error => {
                console.error(error.message);
                alert(error.message);
            });
    });
});